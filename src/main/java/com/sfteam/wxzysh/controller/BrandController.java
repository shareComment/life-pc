package com.sfteam.wxzysh.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.rsp.RspBrandInfo;
import com.sfteam.wxzysh.rsp.RspCommentListInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: BrandController.java
 * @Description: 品牌详情控制器
 * @author: knight
 * @date: 2016年7月16日 下午2:20:46
 * @company: sfteam
 */
@Controller
@RequestMapping("brand")
public class BrandController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: showBrandInfo
	 * @Description: 显示品牌详情
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/{brandId}")
	public String showBrandInfo(@PathVariable String brandId, Model model, HttpServletRequest request) {
		// 查询品牌信息
		RspBrandInfo brandInfo = (RspBrandInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
				+ MessageFormat.format(UrlConstants.BrandCtrl.QUERY,brandId),RspBrandInfo.class);
		if (Constants.SUCCESS.equals(brandInfo.getResult())) {
			// 查询最新评论
			RspCommentListInfo lastComm = (RspCommentListInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
					+ UrlConstants.CommentCtrl.QRY_RECENT,RspCommentListInfo.class);
			if(Constants.SUCCESS.equals(lastComm.getResult())){
				model.addAttribute("comment_recent", lastComm.getInfoList());
			}
			// 查询相关品牌
			RspBrandInfo relBrand = (RspBrandInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
					+ MessageFormat.format(UrlConstants.BrandCtrl.BRAND_LIST,brandInfo.getRspInfo().getCate_id(),"1"),RspBrandInfo.class);
			if(Constants.SUCCESS.equals(relBrand.getResult())){
				model.addAttribute("relative_brand", relBrand.getInfoList());
			}
			// 查询该品牌下我的评论
			User user = (User) getUserResource(request);
			if(!StringUtils.isEmpty(user)){
				RspCommentListInfo myCommList = (RspCommentListInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.QRY_B_M, brandInfo.getRspInfo().getBrand_id(), user.getUser_id(), "1"),RspCommentListInfo.class);
				if(Constants.SUCCESS.equals(myCommList.getResult())){
					model.addAttribute("myCommentList", myCommList.getInfoList());
					model.addAttribute("myCommentNum", myCommList.getInfoList().size());
					model.addAttribute("my_currentPage", "1");
					model.addAttribute("my_totalPages", myCommList.getCount());
				}
			}
			model.addAttribute("brand_info", brandInfo.getRspInfo());
			return "brand";
		} else {
			model.addAttribute("errorInfo", brandInfo.getErrorInfo());
			return "redirect:/error";
		}
	}

	/**
	 * 
	 * @Title: allCommment
	 * @Description: 品牌评论
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月27日 上午10:47:10
	 */
	@RequestMapping("/allComment")
	@ResponseBody
	public String allCommment(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String brand_id = request.getParameter("brand_id");
		String pageNo = request.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "1";
		}
		// 查询该品牌评论
		RspCommentListInfo commList = (RspCommentListInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
				+ MessageFormat.format(UrlConstants.CommentCtrl.QUERY, brand_id, "0", pageNo),RspCommentListInfo.class);
		if(Constants.SUCCESS.equals(commList.getResult())){
			map.put("commentList", commList.getInfoList());
			map.put("commentNum", commList.getInfoList().size());
			map.put("all_currentPage", pageNo);
			map.put("all_totalPages", commList.getCount());
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: myComment
	 * @Description: 品牌评论
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月27日 上午10:47:10
	 */
	@RequestMapping("/myComment")
	@ResponseBody
	public String myComment(HttpServletRequest request){
		User user = (User) getUserResource(request);
		if(!StringUtils.isEmpty(user)){
			Map<String,Object> map = new HashMap<String,Object>();
			String brand_id = request.getParameter("brand_id");
			String pageNo = request.getParameter("pageNo");
			if(StringUtils.isEmpty(pageNo)){
				pageNo = "1";
			}
			// 查询该品牌评论
			RspCommentListInfo commList = (RspCommentListInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
					+ MessageFormat.format(UrlConstants.CommentCtrl.QRY_B_M, brand_id, user.getUser_id(), pageNo),RspCommentListInfo.class);
			if(Constants.SUCCESS.equals(commList.getResult())){
				map.put("myCommentList", commList.getInfoList());
				map.put("myCommentNum", commList.getInfoList().size());
				map.put("my_currentPage", pageNo);
				map.put("my_totalPages", commList.getCount());
			}
			return gson.toJson(map);
		}else{
			return Constants.FAILURE;
		}
	}

	/**
	 * 
	 * @Title: showVioce
	 * @Description: 显示点评页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/vioce/{brandId}")
	public String showVioce(@PathVariable String brandId, Model model, HttpServletRequest request) {
		User user = (User) getUserResource(request);
		if(!StringUtils.isEmpty(user)){
			RspBrandInfo brandInfo = (RspBrandInfo)getResInfo(null, Constants.METHOD_GET, getBaseURL()
					+ MessageFormat.format(UrlConstants.BrandCtrl.QUERY,brandId),RspBrandInfo.class);
			if (Constants.SUCCESS.equals(brandInfo.getResult())) {
				model.addAttribute("brand_info", brandInfo.getRspInfo());
				return "vioce";
			}else{
				model.addAttribute("errorInfo", brandInfo.getErrorInfo());
				return "redirect:/error";
			}
		}
		return "redirect:/error";
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 品牌；列表页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/list/{keyword}")
	public String list() {
		log.info("=================list=================");
		return "list";
	}
}
