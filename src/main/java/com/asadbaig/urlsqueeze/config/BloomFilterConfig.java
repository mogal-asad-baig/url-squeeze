package com.asadbaig.urlsqueeze.config;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class BloomFilterConfig {

    @Value("${url.hash.bloom-filter.insertions}")
    private Long expectedInsertions;

    @Bean
    public BloomFilter<String> urlHashBloomFilter() {
        return BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedInsertions,
                0.01);
    }
}
