package com.upfor.grover.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "grover")
public class AppConfig {

    @Value("${spring.application.name}")
    private String appName;

    /**
     * Author of project
     */
    private String author = "jioby";

}
