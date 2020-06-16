package com.zr.corningrey.entity;

/**
 * 返回数据结果集合
 *
 * @author RuXuanWo on 2019/02/21
 */
public class ResponseMsgUtil {
    public ResponseMsgUtil() {
    }

    public static <T> Result<T> builderResponse(int code, String msg, T data) {
        Result<T> res = new Result();
        res.setCode(code);
        res.setResMsg(msg);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> success(String msg) {
        return builderResponse(0, msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return builderResponse(0, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return builderResponse(0, "Success", data);
    }

    public static <T> Result<T> success() {
        return builderResponse(0, "Success", null);
    }

    public static <T> Result<T> failure() {
        return builderResponse(1, "Failure", null);
    }

    public static <T> Result<T> failure(String msg) {
        return builderResponse(1, msg, null);
    }

    public static <T> Result<T> failure(T date) {
        return builderResponse(-1, "Failure", date);
    }

    public static <T> Result<T> illegalRequest() {
        return builderResponse(1008, "Illegal request", (T) null);
    }

    public static <T> Result<T> exception() {
        return builderResponse(1002, "request exception", (T) null);
    }

    public static <T> Result<T> paramsEmpty() {
        return builderResponse(1009, "the input parameter is null", (T) null);
    }
}