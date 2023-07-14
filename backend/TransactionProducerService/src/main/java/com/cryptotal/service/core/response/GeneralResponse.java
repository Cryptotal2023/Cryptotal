package com.cryptotal.service.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GeneralResponse<T> {
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
