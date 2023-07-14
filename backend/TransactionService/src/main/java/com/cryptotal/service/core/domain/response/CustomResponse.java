package com.cryptotal.service.core.domain.response;

public interface CustomResponse {
    ResponseStatus status = null;
    Object body = null;

    ResponseStatus getStatus();
    Object getBody();
}

