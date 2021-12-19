package com.upfor.grover.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "grover")
@Data
public class AppConfig {

    /**
     * Author of project
     */
    private String author = "jioby";

}
