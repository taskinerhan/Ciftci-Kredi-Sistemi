package com.cks.ciftcikredisistemi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Configuration
public class WebClientConfig {
    public static final String X_SECURE_KEY = "x-secure-key";
    public static final String HEADER_VALUE = "6RcVRuwxUr07F54B7a9IPomjh";
    @Value("${custom.service.url}")
    private String customServiceUrl;
    @Value("${custom.service.memorySizeMB}")
    private int maxSizeMB;

    private final WebClient.Builder webClientBuilder;

    @Bean
    public WebClient customWebClient() {
        final int size = maxSizeMB * 1024 * 1024;
        return webClientBuilder
                .baseUrl(customServiceUrl)
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(size))
                        .build()).defaultHeader(X_SECURE_KEY, HEADER_VALUE)
                .build();
    }
}
