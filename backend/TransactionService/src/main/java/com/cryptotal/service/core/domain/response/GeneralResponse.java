package com.cryptotal.service.core.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GeneralResponse<T> implements CustomResponse {
    ResponseStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    public static GeneralResponse buildGeneralResponse(String msg, Object object) {
        ResponseStatus status = new ResponseStatus(true, msg);
        return new GeneralResponse(status, object);
    }
    public static GeneralResponse buildGeneralResponse(String msg) {
        ResponseStatus status = new ResponseStatus(true, msg);
        return new GeneralResponse(status, null);
    }
}
