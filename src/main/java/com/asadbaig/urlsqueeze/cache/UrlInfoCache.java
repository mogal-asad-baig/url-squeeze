package com.asadbaig.urlsqueeze.cache;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Optional;

@Configuration
public class UrlInfoCache {

    @Value("${in-memory.url.info.cache.size}")
    private Long inMemoryCacheSize;

    @Value("${in-memory.url.info.cache.expiry.ms}")
    private Long expiryTime;

    @Bean
    public Cache<String, Optional<UrlInfoEntity>> inMemoryUrlInfoCache(){
        return Caffeine.newBuilder()
                .maximumSize(inMemoryCacheSize)
                .expireAfterAccess(Duration.ofMillis(expiryTime))
                .build();
    }
}
