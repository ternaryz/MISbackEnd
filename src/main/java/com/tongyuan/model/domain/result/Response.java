package com.tongyuan.model.domain.result;

import com.tongyuan.model.domain.enums.ExceptionMsg;

/**
 * Created by Y470 on 2017/6/28.
 */
public class Response {
    private String rspCode="000000";
    private String rspMsg="操作成功！";

    public Response(){
    }
    public Response(ExceptionMsg exceptionMsg){
        this.rspCode = exceptionMsg.getCode();
        this.rspMsg = exceptionMsg.getMsg();
    }
    public Response(String rspCode,String rspMsg){
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }
    public Response(String rspCode){
        this.rspCode = rspCode;
        rspCode = "";
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public String getRspCode() {
        return rspCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                '}';
    }
}
