package com.asadbaig.urlsqueeze.controller;

import com.asadbaig.urlsqueeze.model.UrlSqueezeRequest;
import com.asadbaig.urlsqueeze.model.UrlSqueezeResponse;
import com.asadbaig.urlsqueeze.service.UrlSqueezeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
@Slf4j
public class UrlSqueezeController {

    @Autowired
    UrlSqueezeService urlSqueezeService;


    @PostMapping("/squeeze")
    public ResponseEntity<UrlSqueezeResponse> squeezeUrl(@RequestBody @Valid UrlSqueezeRequest urlSqueezeRequest) {
        return urlSqueezeService.squeezeUrl(urlSqueezeRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUrl(@RequestBody @NotBlank String squeezedUrl) {
        return urlSqueezeService.deleteUrl(squeezedUrl);
    }
}
