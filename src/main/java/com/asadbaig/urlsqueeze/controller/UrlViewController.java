package com.asadbaig.urlsqueeze.controller;

import com.asadbaig.urlsqueeze.service.UrlViewService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlViewController {

    @Autowired
    UrlViewService urlViewService;


    @GetMapping("/{urlId}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable(name = "urlId") @NotBlank String urlId) {
        return urlViewService.getOriginalUrl(urlId);
    }
}
