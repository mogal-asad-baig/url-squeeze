package com.asadbaig.urlsqueeze.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime generatedTime;
    private String errorCode;
    private String errorDescription;
}
