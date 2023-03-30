package com.example.cucumberstart.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author oaiqiy
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Result<T> {
    private final Integer code;
    private final String msg;

    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "success");
    }

    public static <T> Result<T> failed(T data) {
        return new Result<>(400, "invalid", data);
    }

    public static <T> Result<T> failed(String msg){
        return new Result<>(400, msg);
    }

    public static <T> Result<T> failed(String msg, T data){
        return new Result<>(400, msg, data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(400, "invalid");
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(500, "server error", data);
    }

    public static <T> Result<T> error() {
        return new Result<>(500, "server error");
    }
}
