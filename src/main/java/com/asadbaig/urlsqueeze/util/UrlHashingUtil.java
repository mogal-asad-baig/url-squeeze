package com.asadbaig.urlsqueeze.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class UrlHashingUtil {

    private static final String COLLISION_RESOLUTION_CONSTANT = "CRC_US#01";

    @Autowired
    private BloomFilter<String> urlHashBloomFilter;

    @Value("${squeezed.url.id.size}")
    private int urlIdSize;


    public String getUrlHash(String url) {
        if(StringUtils.isNotBlank(url)) {
            String initialHash = computeHash(url);
            String trimmedHash = StringUtils.substring(initialHash, 0, urlIdSize);
            if(!urlHashBloomFilter.mightContain(trimmedHash)) {
                urlHashBloomFilter.put(trimmedHash);
                return trimmedHash;
            } else {
                String newHash = StringUtils.join(url, COLLISION_RESOLUTION_CONSTANT);
                getUrlHash(newHash);
            }
        }
        return url;
    }

    private String computeHash(String input) {
        return Hashing.murmur3_32_fixed()
                .hashString(input, StandardCharsets.UTF_8).toString();
    }

}
