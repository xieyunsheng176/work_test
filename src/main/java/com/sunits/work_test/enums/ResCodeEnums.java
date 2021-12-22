package com.sunits.work_test.enums;

/**
 * @author xieys
 * @description:
 * @create 2021/6/11 16:41
 */
public enum  ResCodeEnums {
    SUCCESS(200,"成功"),
    ERROR(500,"失败");
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResCodeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
