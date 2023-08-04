package com.cks.ciftcikredisistemi.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AsyncService {
    @Async("customTaskExecutor")
    public CompletableFuture<Void> runAsync(int index) {
        CompletableFuture<Void> feature = new CompletableFuture<>();

        log.info("Calisan process index'i: {}", index);

        feature.complete(null);

        return feature;
    }
}