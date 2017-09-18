package com.tongyuan.model.domain.enums;

/**
 * Created by Y470 on 2017/6/28.
 */
public enum ExceptionMsg {
    SUCCESS("000000","操作成功！"),
    FAILED("999999","操作失败！"),
    ParamError("000001","参数错误！");

    private String msg;
    private String code;

    private ExceptionMsg(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

}
