package com.moon.likelion.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")  // 어떤 상황에서 어떤 빈을 사용할 지 정의
public class ProfileComponent {
    private static final Logger logger = LoggerFactory.getLogger(ProfileComponent.class);
    public ProfileComponent() {
        logger.info("profile component profile: local");;
    }
}
