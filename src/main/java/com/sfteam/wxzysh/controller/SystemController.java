package com.sfteam.wxzysh.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sfteam.wxzysh.dto.Feed;
import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.rsp.RspFeedInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.HttpHelper;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: SystemController.java
 * @Description: 系统模块
 * @author: knight
 * @date: 2016年7月15日 下午4:55:02
 * @company: sfteam
 */
@Controller
@RequestMapping("sys")
public class SystemController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: showBrandInfo
	 * @Description: 显示品牌详情
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/feed")
	@ResponseBody
	public String feed(HttpServletRequest request, Feed feed) {
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "请您先登录";
		}
		feed.setUser_id(user.getUser_id());
		String json = gson.toJson(feed);
		RspFeedInfo rspFeedInfo = (RspFeedInfo) getResInfo(json,
				Constants.METHOD_POST, getBaseURL()
						+ UrlConstants.SystemCtrl.FEED, RspFeedInfo.class);
		if (Constants.SUCCESS.equals(rspFeedInfo.getResult())) {
			return Constants.SUCCESS;
		} else {
			return rspFeedInfo.getErrorInfo();
		}
	}

	/**
	 * 
	 * @Title: getShare
	 * @Description: 分享链接
	 * @return: String
	 * @author: knight
	 * @throws
	 * @throws Exception
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/getShare")
	@ResponseBody
	public String getShare(HttpServletRequest request){
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		if (StringUtils.isEmpty(user)) {
			return "http://www.ziyoush.com/signup";
		}else{
			return "http://www.ziyoush.com/signup/"+user.getTelephone();
		}
	}

	/**
	 * 
	 * @Title: showBrandInfo
	 * @Description: 显示品牌详情
	 * @return: String
	 * @author: knight
	 * @throws
	 * @throws Exception
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file)
			throws Exception {

		if (file.isEmpty()) {
			return "";
		}
		String uploadPath = request.getRealPath("/")+"/tmp";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String filename = Constants.getUUID() + ".jpg";
		String filePath = uploadPath + "/" + filename;
		File uploadFile = new File(filePath);
		file.transferTo(uploadFile);
//		return "/pingpai"+"/"+date+"/"+filename;
		List<String> list = new ArrayList<String>();
		list.add(filePath);
		String result = HttpHelper.upload(getBaseURL()+UrlConstants.SystemCtrl.UPLOAD, list);
		return result;
	}

	/**
	 * 
	 * @Title: showBrandInfo
	 * @Description: 显示品牌详情
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		getSession(request).invalidate();
		return "redirect:/";
	}
}
