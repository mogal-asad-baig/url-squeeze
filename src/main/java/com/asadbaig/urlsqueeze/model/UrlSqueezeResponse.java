package com.asadbaig.urlsqueeze.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlSqueezeResponse {

    private String responseCode;
    private String responseDescription;
    private String originalUrl;
    private String squeezedUrl;
}
