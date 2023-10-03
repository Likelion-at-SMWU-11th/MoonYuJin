package com.moon.likelion.crud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // pointcut
    @Around(value = "@annotation(LogExecutionTime)")

    // advice
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long execTime = System.currentTimeMillis() - startTime;
        logger.trace("method executed in {}", execTime);
        return proceed;
    }

    @Before(value = "@annotation(LogArguments)")
    public void logArguments(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.trace("method description: [{}]", methodSignature.getMethod());
        logger.trace("method name: [{}]", methodSignature.getName());
        logger.trace("declaring class: [{}]", methodSignature.getDeclaringType());

        Object[] arguments = joinPoint.getArgs();

        if (arguments.length == 0) {
            logger.trace("noo arguments");
        }

        for (Object argument: arguments) {
            logger.trace("argument: [{}]", argument);
        }
    }

    @AfterReturning(value = "@annotation(LogReturn)", returning = "returnValue")
    public void logResults(JoinPoint joinPoint, Object returnValue) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.trace("method name: [{}]", methodSignature.getName());
        logger.trace("return tyoe: [{}]", methodSignature.getReturnType());
        logger.trace("return value: [{}]", returnValue);
    }
}
