package com.jimmy.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {

	//进入Handler方法之前执行
	//应用场景：用于身份认证、身份授权
	//比如身份认证，如果认证不通过表示都当前用户没有登录，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是公开地址（实际使用时将公开地址配置在文件中）
		if(url.indexOf("login.action")>=0){
			//公开地址，放行
			return true;
		}
		
		//判断session
		HttpSession session = request.getSession();
		//取出用户身份信息
		String username = (String) session.getAttribute("username");
		if(username != null){
			//用户身份信息存在，放行
			return true;
		}
		
		//执行到这里表示用户信息需要认证，跳转到登陆页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

		//return false表示拦截，不向下执行
		//return true表示放行
		return false;
	}

	//进入Handler方法之后，返回ModelAndView之前执行
	//应用场景：从modelAndView出发，将公用的模型数据（比如菜单导航）在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	//Handler方法执行完成之后执行
	//应用场景：统一的异常处理、统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
