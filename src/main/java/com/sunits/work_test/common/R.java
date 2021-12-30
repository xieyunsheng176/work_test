package com.sunits.work_test.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class R<T> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(R.class);

    /*** HTTP Status-Code 200: OK. */
    public static final String HTTP_OK = "200";
    private static final String HTTP_OK_MSG = "成功！";
    /*** HTTP Status-Code 500: Internal Server Error. */
    public static final String HTTP_INTERNAL_ERROR = "500";
    public static final String HTTP_INTERNAL_ERROR_MSG = "服务器正忙，请稍后再试!";

    /*** 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private String code;
    private String msg;
    private T data;
    private String timestamp;
    public R(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN));
    }

    public R(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN));
        this.data = data;
    }

    public boolean isOk() {
        return this.code.equals(HTTP_OK);
    }

    public static R<Void> ok() {
        return new R<>(HTTP_OK, HTTP_OK_MSG);
    }

    public static R<Void> ok(String msg) {
        return new R<>(HTTP_OK, msg);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(HTTP_OK, msg, data);
    }

    public static R<Void> ok(String code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> ok(String code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(HTTP_OK, HTTP_OK_MSG, data);
    }

    public static R<Void> fail(String code, String msg) {
        R<Void> result = new R<>(code, msg);
        LOGGER.info("响应结果：{}", result);
        return result;
    }

    public static <T> R<T> fail(String code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> error() {
        return new R<>(HTTP_INTERNAL_ERROR, HTTP_INTERNAL_ERROR_MSG);
    }

    public static <T> R<T> error(String msg) {
        return new R<>(HTTP_INTERNAL_ERROR, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                '}';
    }
}
