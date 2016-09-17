package com.sfteam.wxzysh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.req.ReqUserInfo;
import com.sfteam.wxzysh.rsp.RspUserInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.MD5Util;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: LoginController.java
 * @Description: 登录控制器
 * @author: knight
 * @date: 2016年7月15日 下午1:19:52
 * @company: sfteam
 */
@Controller
@RequestMapping("/signin")
public class LoginController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:25:59
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user, Model model) {
		if (!StringUtils.isEmpty(getUserResource(request))) {
			return "redirect:/";
		}
		user.setPassword(MD5Util.getMD5String(user.getPassword()));
		ReqUserInfo reqInfo = new ReqUserInfo();
		reqInfo.setReqInfo(user);
		String json = gson.toJson(reqInfo);
		RspUserInfo rspInfo = (RspUserInfo)getResInfo(json, Constants.METHOD_POST, getBaseURL()
				+ UrlConstants.UserCtrl.LOGIN,RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			RspUserInfo rspUserInfo = (RspUserInfo)getResInfo(json, Constants.METHOD_GET, getBaseURL()
					+ UrlConstants.UserCtrl.QUERY,RspUserInfo.class);
			if (Constants.SUCCESS.equals(rspUserInfo.getResult())) {
				setSession((HttpSession) getSession(request),
						Constants.PAGE_RESOURCE, rspUserInfo.getRspInfo());
			}
			log.info("用户：" + user.getTelephone() + " 于"
					+ System.currentTimeMillis() + "登录系统！");
		} else {
			model.addAttribute("errorInfo", rspInfo.getErrorInfo());
			return "signin";
		}
		return "redirect:/";
	}
}
