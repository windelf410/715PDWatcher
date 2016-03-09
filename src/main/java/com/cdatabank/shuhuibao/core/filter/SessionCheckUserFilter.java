package com.cdatabank.shuhuibao.core.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdatabank.shuhuibao.core.bean.UserSourceBean;
import com.cdatabank.shuhuibao.core.constants.GlobalConstants;
import com.cdatabank.shuhuibao.core.listener.SessionListener;
import com.cdatabank.shuhuibao.core.util.SessionUtils;

public class SessionCheckUserFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(SessionCheckUserFilter.class);

	private List<String> notCheckURLList = new ArrayList<String>();

	public void init(FilterConfig filterConfig) throws ServletException {

		String notCheckURLListStr = filterConfig
				.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken().trim());
			}
		}

	}

	@Autowired
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getServletPath()
				+ (request.getPathInfo() == null ? "" : request.getPathInfo());

		logger.info(uri);

		if (notCheckURLList != null && notCheckURLList.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		boolean b = Pattern.matches("^/rest/common/news/\\d{1,}$", uri);
		if (true) {
			chain.doFilter(request, response);
			return;
		}

		// 判断用户是否已经登录
		HttpSession session = request.getSession(false);
		//if(request.get)
		if (session != null
				&& session.getAttribute(GlobalConstants.SESSION_LOGIN_NAME) != null) {
		} else { // 如果用户==null 表示用户没有登陆

			
		}
	}

	public void destroy() {
		notCheckURLList.clear();
	}

}
