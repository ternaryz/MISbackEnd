package com.tongyuan.model.domain.result;

import com.tongyuan.model.domain.enums.ExceptionMsg;

/**
 * Created by Y470 on 2017/6/28.
 */
public class ResponseData extends Response{
    private Object data;
    public ResponseData(String rspCode,String rspMsg,Object data){
        super(rspCode,rspMsg);
        this.data = data;
    }
    public ResponseData(String rspCode,Object data){
        super(rspCode);
        this.data = data;
    }
    public ResponseData(ExceptionMsg exceptionMsg,Object data){
        super(exceptionMsg);
        this.data = data;
    }
    //只有一个data构造，默认成功
    public ResponseData(Object data){
        super();
        this.data = data;
    }

    public ResponseData(ExceptionMsg exceptionMsg){
        super(exceptionMsg);
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "data=" + data +
                '}'+super.toString();
    }
}
