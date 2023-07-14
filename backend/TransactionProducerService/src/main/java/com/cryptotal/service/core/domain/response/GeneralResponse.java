package com.cryptotal.service.core.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

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
        return new GeneralResponse<>(status, object);
    }
    public static GeneralResponse buildGeneralResponse(String msg) {
        ResponseStatus status = new ResponseStatus(true, msg);
        return new GeneralResponse<>(status, null);
    }

    public static GeneralResponse buildErrorResponse(String msg) {
        ResponseStatus status = new ResponseStatus(false, msg);
        return new GeneralResponse(status, null);
    }
    public static GeneralResponse buildErrorResponse(List<FieldError> errors) {
        StringBuilder s = new StringBuilder();
        errors.forEach(error -> {
            s.append(error.getObjectName() + ": " + error.getDefaultMessage() + " ");
        });
        ResponseStatus status = new ResponseStatus(false, s.toString());
        return new GeneralResponse(status, null);
    }
}
