package com.asadbaig.urlsqueeze.service;

import org.springframework.http.ResponseEntity;

public interface UrlViewService {

    ResponseEntity<?> getOriginalUrl(String urlId);
}
