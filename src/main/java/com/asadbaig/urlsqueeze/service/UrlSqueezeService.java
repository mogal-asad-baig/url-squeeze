package com.asadbaig.urlsqueeze.service;

import com.asadbaig.urlsqueeze.model.UrlSqueezeRequest;
import com.asadbaig.urlsqueeze.model.UrlSqueezeResponse;
import org.springframework.http.ResponseEntity;

public interface UrlSqueezeService {

    ResponseEntity<UrlSqueezeResponse> squeezeUrl(UrlSqueezeRequest urlSqueezeRequest);

    ResponseEntity<String> deleteUrl(String squeezedUrl);
}
