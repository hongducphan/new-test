package com.ducph.newtest.config;

import com.ducph.newtest.external.TodoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final TodoConfig todoConfig;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(todoConfig.getBaseUrl())
                .build();
    }

    @Bean
    public TodoClient todoClient() {
        var factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient())).build();
        return factory.createClient(TodoClient.class);
    }
}
