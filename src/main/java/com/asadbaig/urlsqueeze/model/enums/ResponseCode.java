package com.asadbaig.urlsqueeze.model.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    US200("Url Squeezed Successfully"),
    MF400("Request Data is Not as Expected"),
    US500("Internal Error During Url Squeezing"),
    UD500("Internal Error While Deleting Url");

    private String description;
    ResponseCode(String description) {
        this.description = description;
    }
}
