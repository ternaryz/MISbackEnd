package com.tongyuan.model.interceptor;


import com.tongyuan.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter {

	protected Logger logger = Logger.getLogger(this.getClass());
	private static Set<String> GreenUrlSet = new HashSet<String>();

/*	@Autowired
	private UserRepository userRepository;*/
    @Autowired
    private UserService userService;

/*	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		GreenUrlSet.add(Const.BASE_PATH + "/");
		GreenUrlSet.add(Const.BASE_PATH + "/login");
		GreenUrlSet.add(Const.BASE_PATH + "/register");
		GreenUrlSet.add(Const.BASE_PATH + "/index");
		GreenUrlSet.add(Const.BASE_PATH + "/forgotPassword");
		GreenUrlSet.add(Const.BASE_PATH + "/newPassword");
		GreenUrlSet.add(Const.BASE_PATH + "/tool");
	}*/

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		HttpSession session = request.getSession();
		if(session.getId()==null){
			Cookie[] cookies = request.getCookies();
			if(cookies != null&&cookies.length>0){
				for(Cookie cookie : cookies){
					Cookie new_cookie = new Cookie(cookie.getName(), null);
					new_cookie.setDomain(".modelica-china.com");
					new_cookie.setPath("/");
					new_cookie.setMaxAge(0);
					response.addCookie(new_cookie);
				}
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}



}