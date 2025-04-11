package com.ducph.newtest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

@Configuration
@RequiredArgsConstructor
public class VaultConfig {

    private final VaultProperties vaultProperties;

    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultProperties.getHost(), vaultProperties.getPort());
        vaultEndpoint.setScheme(vaultProperties.getScheme());

        TokenAuthentication tokenAuthentication = new TokenAuthentication(vaultProperties.getToken());

        return new VaultTemplate(vaultEndpoint, tokenAuthentication);
    }
}
