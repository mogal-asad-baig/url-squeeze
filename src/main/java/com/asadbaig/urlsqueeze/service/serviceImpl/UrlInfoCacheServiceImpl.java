package com.asadbaig.urlsqueeze.service.serviceImpl;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;
import com.asadbaig.urlsqueeze.repository.UrlInfoRepository;
import com.asadbaig.urlsqueeze.service.UrlInfoCacheService;
import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlInfoCacheServiceImpl implements UrlInfoCacheService {

    @Autowired
    Cache<String, Optional<UrlInfoEntity>> inMemoryUrlInfoCache;

    @Autowired
    UrlInfoRepository urlInfoRepository;


    @Override
    public Optional<UrlInfoEntity> getUrlInfo(String urlId) {
        Optional<UrlInfoEntity> urlInfo = inMemoryUrlInfoCache.getIfPresent(urlId);
        if(urlInfo == null) {
            urlInfo = urlInfoRepository.findByUrlId(urlId);
            inMemoryUrlInfoCache.put(urlId, urlInfo);
        }
        return urlInfo;
    }
}
