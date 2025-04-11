package com.ducph.newtest.service.impl;

import com.ducph.newtest.config.TodoConfig;
import com.ducph.newtest.config.VaultProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaultService {

    private final VaultTemplate vaultTemplate;
    private final VaultProperties vaultProperties;

    public TodoConfig getTodoConfig() {
        try {
            VaultResponse response = vaultTemplate.read(vaultProperties.getPath());
            Map<String, Object> data = (Map<String, Object>) response.getData().get("data");

            TodoConfig secrets = new TodoConfig();
            secrets.setBaseUrl((String) data.get("todo-service.base-url"));
            return secrets;
        } catch (Exception e) {
            // Có thể log lỗi bằng logger (SLF4J, Logback, v.v.)
            log.error("Error while fetching data from Vault: {}", e.getMessage(), e);
            return new TodoConfig();
        }
    }
}
