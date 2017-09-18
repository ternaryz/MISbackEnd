package com.tongyuan.model.controller;

import com.tongyuan.model.domain.User;
import com.tongyuan.model.domain.enums.ExceptionMsg;
import com.tongyuan.model.domain.result.Response;
import com.tongyuan.model.domain.result.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Y470 on 2017/6/27.
 */
@Controller
public class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    protected HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    protected User getUser(){
        return (User) getSession().getAttribute("user");
    }

    protected Long getUserId(){
        Long id = 0L;
        User user = getUser();
        if(user!=null){
            id = user.getId();
        }
        return id;
    }
    protected String getUserName(){
        String userName = "";
        User user = getUser();
        if(user!=null){
            userName = user.getUserName();
        }
        return userName;
    }

    protected Response result(){
        return new Response();
    }

    protected Response result(ExceptionMsg exceptionMsg){
        return new Response(exceptionMsg);
    }

    protected ResponseData resultData(Object data){
        return new ResponseData(data);
    }
}
