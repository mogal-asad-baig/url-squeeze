package com.asadbaig.urlsqueeze.service;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;

import java.util.Optional;

public interface UrlInfoCacheService {

    Optional<UrlInfoEntity> getUrlInfo(String urlId);
}
