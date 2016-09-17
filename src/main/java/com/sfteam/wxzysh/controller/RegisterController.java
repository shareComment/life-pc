package com.sfteam.wxzysh.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.req.ReqUserInfo;
import com.sfteam.wxzysh.rsp.RspUserInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.MD5Util;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: RegisterController.java
 * @Description: 注册控制器
 * @author: knight
 * @date: 2016年7月15日 下午1:19:52
 * @company: sfteam
 */
@Controller
@RequestMapping("/signup")
public class RegisterController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: getCode
	 * @Description: 获取验证吗
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:25:59
	 */
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	@ResponseBody
	public String getCode(HttpServletRequest request) {
		getResInfo(null, Constants.METHOD_GET, getBaseURL()
				+ MessageFormat.format(UrlConstants.SystemCtrl.SMS_CODE, request.getParameter("telephone"),"0"), RspUserInfo.class);
		return Constants.SUCCESS;
	}

	/**
	 * 
	 * @Title: register
	 * @Description: 用户注册
	 * @author: knight
	 * @date: 2016年7月15日 下午11:21:50
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(HttpServletRequest request, User user, Model model){
		if (!StringUtils.isEmpty(getUserResource(request))) {
			return "redirect:/";
		}
		user.setPassword(MD5Util.getMD5String(user.getPassword()));
		ReqUserInfo reqInfo = new ReqUserInfo();
		reqInfo.setReqInfo(user);
		String json = gson.toJson(reqInfo);
		RspUserInfo rspInfo = (RspUserInfo)getResInfo(json, Constants.METHOD_POST, getBaseURL()
				+ UrlConstants.UserCtrl.REGISTER,RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			log.info("用户：" + user.getTelephone() + " 于"
					+ System.currentTimeMillis() + "注册！");
			return "redirect:/signin";
		} else {
			model.addAttribute("errorInfo", rspInfo.getErrorInfo());
			return "signup";
		}
	}
}
