package com.asadbaig.urlsqueeze.service.serviceImpl;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;
import com.asadbaig.urlsqueeze.service.UrlInfoCacheService;
import com.asadbaig.urlsqueeze.service.UrlViewService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlViewServiceImpl implements UrlViewService {


    private final String ORIGINAL_URL_HEADER_NAME = "Location";
    @Autowired
    UrlInfoCacheService urlInfoCacheService;

    @Override
    public ResponseEntity<?> getOriginalUrl(String urlId) {
        Optional<UrlInfoEntity> urlInfo = urlInfoCacheService.getUrlInfo(urlId);
        if(urlInfo.isPresent() && StringUtils.isNotBlank(urlInfo.get().getOriginalUrl())) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(ORIGINAL_URL_HEADER_NAME, urlInfo.get().getOriginalUrl());
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
