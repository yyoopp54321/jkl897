package com.example.jkl.common;



/**
 * 服务端状态码以及状态码描述枚举类
 */
public enum ResponseCode {
    //成功
    SUCCESS(0,"SUCCESS"),
    //错误
    ERROR(1,"ERROR"),
    //illegal非法argument言论
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    //需要 // 登陆
    NEED_LOGIN(10,"NEED_LOGIN"),
    //异常
    Exception(-1,"Exception");

    private int code;
    private String desc;
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
