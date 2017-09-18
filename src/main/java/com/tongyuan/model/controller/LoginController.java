package com.tongyuan.model.controller;


import com.alibaba.fastjson.JSONObject;
import com.tongyuan.model.domain.User;
import com.tongyuan.model.service.UserService;
import com.tongyuan.tools.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/** 登录
 * Created by tengj on 2017/4/10.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

/*    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
            User user =new User(userName,password);
            request.getSession().setAttribute("user",user);
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }*/


    @RequestMapping(value = "api/loginUser", method = RequestMethod.GET )
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        Map<String,Object> map =new HashMap<String,Object>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userName",username);
        User user = userService.querUserByName(params);
        if(user != null){
            if(user != null && password.equals(user.getPassWord())){
                if(rememberMe != null && "1".equals(rememberMe)){
                    Cookie userCookie = new Cookie("syslinkUser",username + "==" + password);

                    int seconds=60*60;
                    userCookie.setMaxAge(seconds);
                    response.addCookie(userCookie);
                }else{
                    Cookie[] cookies = request.getCookies();
                    if(cookies != null&&cookies.length>0){
                        for(Cookie cookie : cookies){
                            String cookieName = cookie.getName();
                            if("syslinkUser".equals(cookieName)){
                                Cookie new_cookie = new Cookie(cookieName, null);
                                new_cookie.setMaxAge(0);
                                response.addCookie(new_cookie);
                            }
                        }
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("base_path", request.getContextPath());
                Cookie c = new Cookie("gogs_awesome",username);
                c.setDomain(".modelica-china.com");
                c.setMaxAge(60);
                c.setPath("/");
                response.addCookie(c);
   //             return "redirect:/model/getMyIndex";
                map.put("result","1");
                map.put("errormsg","登陆成功！");
            }else{
                request.setAttribute("loginFlag",1);
   //             return "login";
                map.put("result","1");
                map.put("errormsg","登陆成功！");
            }
        }else{
            request.setAttribute("loginFlag", -1);
          //  return "login";
            map.put("result","0");
            map.put("errormsg","登陆失败，请输入正确的用户！");
        }
        return map;
    }
    @RequestMapping(value="api/test",method=RequestMethod.POST)
    @ResponseBody
    public void test(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        JSONObject jo = new JSONObject();
        jo.put("name",name+"nnnnn");
        jo.put("age",age+"aaaaa");
        ServletUtil.createSuccessResponse(200, jo, response);
    }
}
