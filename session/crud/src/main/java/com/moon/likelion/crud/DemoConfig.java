package com.moon.likelion.crud;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class DemoConfig {
    public static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    @Value("${custom.property.single}")
    private String customProperty;

    @Value("${custom.property.comlist}")
    private List<String> customCommaList;

    @PostConstruct
    public void init() {
        logger.info("custom property: {}", customProperty);

        for (String commaListItem: customCommaList) {
            logger.info("comma list item: {}", commaListItem);
        }
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
