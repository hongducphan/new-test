package com.ducph.newtest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "todo-service")
@Getter
@Setter
public class TodoConfig {

    private String baseUrl;
}
