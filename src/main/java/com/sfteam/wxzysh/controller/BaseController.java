package com.sfteam.wxzysh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.HttpHelper;

/**
 * @ClassName: BaseController.java
 * @Description: 基类
 * @author: knight
 * @date: 2016年7月15日 下午1:24:20
 * @company: sfteam
 */
public class BaseController {

	@Autowired
	public Environment env;

	public String getBaseURL() {
		return env.getProperty("interface.url");
	}

	public Gson gson = new Gson();

	public Object getResInfo(String json, String method, String url, Class<?> t) {
		String resInfo = "";
		try {
			if (method.equals(Constants.METHOD_POST))
				resInfo = HttpHelper.post(url, json);
			else
				resInfo = HttpHelper.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.fromJson(resInfo, t);
	}

	public Object getUserResource(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(Constants.PAGE_RESOURCE);
	}

	public HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.PAGE_RESOURCE);
		if(StringUtils.isEmpty(session.getAttribute(Constants.PAGE_RESOURCE))){
			session.setAttribute("shareHref", "http://www.ziyoush.com/signup");
		}else{
			session.setAttribute("shareHref", "http://www.ziyoush.com/signup/"+user.getTelephone());
		}
		return session;
	}

	public void setSession(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}
}
