package com.kedacom.demo.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kedacom.demo.common.utils.ConstantDefine;
import com.kedacom.demo.model.User;

/**
 * 过滤器拦截请求
 * @author qianqiqing
 *
 */
public class AuthFilter implements Filter {
	private String requestUrl;
	private String initUrl;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
						 FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		requestUrl = request.getRequestURI();
		String[] reUrl = requestUrl.split("/");
		List<String> initUrlList = Arrays.asList(initUrl.split(";"));

		if (user != null) {
			if (initUrlList.contains(reUrl[reUrl.length-1])) {
				response.sendRedirect(ConstantDefine.PREFIX_URL + ConstantDefine.FILTER_URL);
			} else {
				arg2.doFilter(request, response);
			}
		} else if (requestUrl.contains(".css") || requestUrl.contains(".js")) {
			arg2.doFilter(arg0, arg1);
		} else {
			if (reUrl[reUrl.length-1].equals(ConstantDefine.FILTER_URL) || initUrlList.contains(reUrl[reUrl.length-1])) {
				arg2.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/login");
			}
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		initUrl = arg0.getInitParameter("initUrl");
	}
}