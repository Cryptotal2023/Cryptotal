package com.cryptotal.service.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    //Create Update Delete success response
    public static Result success() {
        return new Result(1, "success", null);
    }

    //Read success response
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    //failed response
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}
