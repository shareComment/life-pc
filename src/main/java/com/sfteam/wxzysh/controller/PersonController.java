package com.sfteam.wxzysh.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfteam.wxzysh.dto.Comment;
import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.req.ReqUserInfo;
import com.sfteam.wxzysh.rsp.RspCommentListInfo;
import com.sfteam.wxzysh.rsp.RspSysMsgInfo;
import com.sfteam.wxzysh.rsp.RspUserInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.MD5Util;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: PersonController.java
 * @Description: 个人中心控制器
 * @author: knight
 * @date: 2016年7月16日 下午2:20:46
 * @company: sfteam
 */
@Controller
@RequestMapping("person")
public class PersonController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: showIndex
	 * @Description: 显示个人中心
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("")
	public String showIndex(HttpServletRequest request) {
		User user = (User) getUserResource(request);
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.UserCtrl.QUERY,
								user.getUser_id()), RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			setSession((HttpSession) getSession(request),
					Constants.PAGE_RESOURCE, rspInfo.getRspInfo());
		}
		return "person";
	}

	/**
	 * 
	 * @Title: showProfit
	 * @Description: 显示个人收益
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/profit")
	public String showProfit(HttpServletRequest request) {
		User user = (User) getUserResource(request);
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.UserCtrl.QUERY,
								user.getUser_id()), RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			setSession((HttpSession) getSession(request),
					Constants.PAGE_RESOURCE, rspInfo.getRspInfo());
		}
		return "profit";
	}

	/**
	 * 
	 * @Title: showProfile
	 * @Description: 显示个人资料
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/profile")
	public String showProfile(HttpServletRequest request, Model model) {
		User user = (User) getUserResource(request);
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.UserCtrl.QUERY,
								user.getUser_id()), RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			setSession((HttpSession) getSession(request),
					Constants.PAGE_RESOURCE, rspInfo.getRspInfo());
			User result = rspInfo.getRspInfo();
			if (!StringUtils.isEmpty(result.getAddress())) {
				String[] addrs = result.getAddress().split(",");
				result.setProvince(addrs[0]);
				result.setCity(addrs[1]);
				result.setDistrict(addrs[2]);
			}
			model.addAttribute("profile", result);
		}
		return "profile";
	}

	/**
	 * 
	 * @Title: updateProfile
	 * @Description: 修改个人资料
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月25日 下午1:16:46
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public String updateProfile(HttpServletRequest request, User user,
			Model model) {
		User sessUser = (User) getUserResource(request);
		if (StringUtils.isEmpty(sessUser)) {
			return "redirect:/";
		}
		ReqUserInfo reqInfo = new ReqUserInfo();
		user.setUser_id(sessUser.getUser_id());
		reqInfo.setReqInfo(user);
		String json = gson.toJson(reqInfo);
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(json,
				Constants.METHOD_POST, getBaseURL()
						+ UrlConstants.UserCtrl.UPDATE, RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			return Constants.SUCCESS;
		} else {
			return rspInfo.getErrorInfo();
		}
	}

	/**
	 * 
	 * @Title: showAuth
	 * @Description: 显示身份认证
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/auth")
	public String showAuth(HttpServletRequest request, Model model) {
		User user = (User) getUserResource(request);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.UserCtrl.QUERY,
								user.getUser_id()), RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			User result = rspInfo.getRspInfo();
			model.addAttribute("authInfo", result);
		}
		return "auth";
	}

	/**
	 * 
	 * @Title: showAuth
	 * @Description: 显示身份认证
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ResponseBody
	public String showAuth(HttpServletRequest request, User user) {
		User sessUser = (User) getUserResource(request);
		if (StringUtils.isEmpty(sessUser)) {
			return "redirect:/";
		}
		ReqUserInfo reqInfo = new ReqUserInfo();
		user.setUser_id(sessUser.getUser_id());
		user.setVerified(Constants.NOT_VERIFIED);
		reqInfo.setReqInfo(user);
		String json = gson.toJson(reqInfo);
		RspUserInfo rspInfo = (RspUserInfo) getResInfo(json,
				Constants.METHOD_POST, getBaseURL()
						+ UrlConstants.UserCtrl.UPDATE, RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			return Constants.SUCCESS;
		} else {
			return rspInfo.getErrorInfo();
		}
	}

	/**
	 * 
	 * @Title: showSysmsg
	 * @Description: 显示系统消息
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/sysmsg")
	public String showSysmsg(HttpServletRequest request, Model model) {
		User user = (User) getUserResource(request);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		return "sysmsg";
	}

	/**
	 * 
	 * @Title: showSysmsg
	 * @Description: 显示系统消息
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/sysmsg/{pageNo}")
	@ResponseBody
	public String showSysmsg(HttpServletRequest request, @PathVariable String pageNo) {
		User user = (User) getUserResource(request);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RspSysMsgInfo sysmsgList = (RspSysMsgInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(
								UrlConstants.SystemCtrl.SYSINFO_LIST,
								user.getUser_id(), pageNo), RspSysMsgInfo.class);
		if (Constants.SUCCESS.equals(sysmsgList.getResult())) {
			map.put("readMsgList", sysmsgList.getInfoList());
			map.put("sysmsg_totalPages", sysmsgList.getCount());
			map.put("sysmsg_currentPage", pageNo);
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: showReply
	 * @Description: 显示回复信息
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/reply")
	public String showReply(HttpServletRequest request, Model model) {
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		return "reply";
	}

	/**
	 * 
	 * @Title: replyMe
	 * @Description: 显示回复我的消息
	 * @author: knight
	 * @date: 2016年7月15日 下午11:19:44
	 */
	@RequestMapping("/replyMe")
	@ResponseBody
	public String replyMe(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RspCommentListInfo replymList = (RspCommentListInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.RLY_M,
								user.getUser_id(), pageNo),
				RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(replymList.getResult())) {
			map.put("replyMeList", replymList.getInfoList());
			map.put("rm_totalPages", replymList.getCount());
			map.put("rm_currentPage", pageNo);
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: showReply
	 * @Description: 显示我的回复消息
	 * @author: knight
	 * @date: 2016年7月15日 下午11:19:44
	 */
	@RequestMapping("/myReply")
	@ResponseBody
	public String myReply(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RspCommentListInfo myReplyList = (RspCommentListInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.M_RLY,
								user.getUser_id(), pageNo),
				RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(myReplyList.getResult())) {
			map.put("myReplyList", myReplyList.getInfoList());
			map.put("mr_totalPages", myReplyList.getCount());
			map.put("mr_currentPage", pageNo);
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: showRemark
	 * @Description: 显示我的点评
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/remark")
	public String showRemark(HttpServletRequest request, Model model) {
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		return "remark";
	}

	/**
	 * 
	 * @Title: showRemark
	 * @Description: 显示我的点评
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/remark/{pageNo}")
	@ResponseBody
	public String showRemark(@PathVariable String pageNo,
			HttpServletRequest request) {
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RspCommentListInfo rspCommentInfo = (RspCommentListInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.QRY_M,
								user.getUser_id(), pageNo),
				RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(rspCommentInfo.getResult())) {
			map.put("remarkList", rspCommentInfo.getInfoList());
			map.put("totalPages", rspCommentInfo.getCount());
			map.put("currentPage", pageNo);
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: chgPass
	 * @Description: 修改密码
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月28日 下午2:50:52
	 */
	@RequestMapping("/chgPass")
	@ResponseBody
	public String chgPass(HttpServletRequest request) {
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "redirect:/";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user.getUser_id());
		map.put("oldPwd", MD5Util.getMD5String(request.getParameter("oldPwd")));
		map.put("newPwd", MD5Util.getMD5String(request.getParameter("newPwd")));
		Map<String, Object> reqmap = new HashMap<String, Object>();
		reqmap.put("reqInfo", map);
		String json = gson.toJson(reqmap);
		RspUserInfo rspUserInfo = (RspUserInfo) getResInfo(
				json,
				Constants.METHOD_POST,
				getBaseURL()
						+ MessageFormat
								.format(UrlConstants.UserCtrl.RESET, "0"),
				RspUserInfo.class);
		if (Constants.SUCCESS.equals(rspUserInfo.getResult())) {
			getSession(request).invalidate();
			return Constants.SUCCESS;
		} else {
			return rspUserInfo.getErrorInfo();
		}
	}
}
