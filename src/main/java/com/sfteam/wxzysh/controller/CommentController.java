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

import com.sfteam.wxzysh.dto.Comment;
import com.sfteam.wxzysh.dto.User;
import com.sfteam.wxzysh.rsp.RspBrandInfo;
import com.sfteam.wxzysh.rsp.RspCommentInfo;
import com.sfteam.wxzysh.rsp.RspCommentListInfo;
import com.sfteam.wxzysh.rsp.RspTagInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: CommentController.java
 * @Description: 评价详情页控制器
 * @author: knight
 * @date: 2016年7月12日 下午9:18:35
 * @company: sfteam
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: showCommentInfo
	 * @Description: 显示评价详情
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/{commentId}")
	public String showCommentInfo(@PathVariable String commentId, Model model) {
		// 评价详情
		RspCommentInfo commentInfo = (RspCommentInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.VIEW,
								commentId), RspCommentInfo.class);
		if (Constants.SUCCESS.equals(commentInfo.getResult())) {
			// 查询最新评论
			RspCommentListInfo lastComm = (RspCommentListInfo) getResInfo(null,
					Constants.METHOD_GET, getBaseURL()
							+ UrlConstants.CommentCtrl.QRY_RECENT,
					RspCommentListInfo.class);
			if (Constants.SUCCESS.equals(lastComm.getResult())) {
				model.addAttribute("comment_recent", lastComm.getInfoList());
			}
			// 查询品牌信息
			RspBrandInfo brandInfo = (RspBrandInfo) getResInfo(
					null,
					Constants.METHOD_GET,
					getBaseURL()
							+ MessageFormat.format(
									UrlConstants.BrandCtrl.QUERY, commentInfo
											.getInfo().getBrand_id()),
					RspBrandInfo.class);
			if (Constants.SUCCESS.equals(brandInfo.getResult())) {
				// 查询品牌标签
				// 标签查询
				RspTagInfo rspTagInfo = (RspTagInfo) getResInfo(
						null,
						Constants.METHOD_GET,
						getBaseURL()
								+ MessageFormat.format(
										UrlConstants.BrandCtrl.BRAND_TAG_LIST,
										brandInfo.getRspInfo().getBrand_id()),
						RspTagInfo.class);
				if (Constants.SUCCESS.equals(rspTagInfo.getResult())) {
					model.addAttribute("tag_info", rspTagInfo.getInfoList());
				}
				model.addAttribute("brand_info", brandInfo.getRspInfo());
			}
			model.addAttribute("comment", commentInfo.getInfo());
			return "comment";
		}
		return "redirect:/error";
	}

	/**
	 * 
	 * @Title: addComment
	 * @Description: 显示评价详情
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public String addComment(HttpServletRequest request, Comment comment,
			Model model) {
		User user = (User) getSession(request).getAttribute(
				Constants.PAGE_RESOURCE);
		comment.setUser_id(user.getUser_id());
		String json = gson.toJson(comment);
		RspCommentListInfo commentInfo = (RspCommentListInfo) getResInfo(json,
				Constants.METHOD_POST, getBaseURL()
						+ UrlConstants.CommentCtrl.ADD,
				RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(commentInfo.getResult())) {
			return Constants.SUCCESS;
		} else {
			return commentInfo.getErrorInfo();
		}
	}

	/**
	 * 
	 * @Title: getReplyList
	 * @Description: 查询回复列表
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月28日 上午10:01:14
	 */
	@RequestMapping("/reply")
	@ResponseBody
	public String getReplyList(HttpServletRequest request) {
		String brand_id = request.getParameter("brand_id");
		String comment_id = request.getParameter("comment_id");
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RspCommentListInfo rspCommentListInfo = (RspCommentListInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.QUERY,
								brand_id, comment_id, pageNo),
				RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(rspCommentListInfo.getResult())) {
			map.put("replyList", rspCommentListInfo.getInfoList());
			map.put("replyNum", rspCommentListInfo.getInfoList().size());
			map.put("currentPage", pageNo);
			map.put("totalPages", rspCommentListInfo.getCount());
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: zan
	 * @Description: 点赞
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月29日 下午2:13:34
	 */
	@RequestMapping("/zan")
	@ResponseBody
	public String zan(HttpServletRequest request) {
		String comment_id = request.getParameter("comment_id");
		RspCommentListInfo rspInfo = (RspCommentListInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.CommentCtrl.ZAN,
								comment_id), RspCommentListInfo.class);
		if (Constants.SUCCESS.equals(rspInfo.getResult())) {
			return Constants.SUCCESS;
		}
		return rspInfo.getErrorInfo();
	}
}
