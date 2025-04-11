package com.ducph.newtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vault")
@Data
public class VaultProperties {

    private String host;
    private int port;
    private String scheme;
    private String token;
    private String path;
}
