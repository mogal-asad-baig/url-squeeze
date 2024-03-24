package com.asadbaig.urlsqueeze.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UrlSqueezeRequest {

    private String requestId;
    @NotBlank(message = "url is mandatory")
    private String url;
    private String isModifiable;
}
