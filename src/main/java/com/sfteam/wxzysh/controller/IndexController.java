package com.sfteam.wxzysh.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sfteam.wxzysh.dto.Brand;
import com.sfteam.wxzysh.rsp.RspBrandInfo;
import com.sfteam.wxzysh.rsp.RspCategoryInfo;
import com.sfteam.wxzysh.rsp.RspParameterInfo;
import com.sfteam.wxzysh.rsp.RspTagInfo;
import com.sfteam.wxzysh.util.Constants;
import com.sfteam.wxzysh.util.UrlConstants;

/**
 * @ClassName: IndexController.java
 * @Description: 首页控制器
 * @author: knight
 * @date: 2016年7月12日 下午9:18:35
 * @company: sfteam
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: index
	 * @Description: 显示首页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request, Model model) {
		// 品牌列表
		RspBrandInfo rspInfo = (RspBrandInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat
								.format(UrlConstants.BrandCtrl.INDEX_LIST,
										"score", "1"), RspBrandInfo.class);

		// 快捷导航（一级分类）
		RspCategoryInfo rspCategoryInfo = (RspCategoryInfo) getResInfo(null,
				Constants.METHOD_GET, getBaseURL()
						+ UrlConstants.BrandCtrl.TOP_CATE,
				RspCategoryInfo.class);
		if (Constants.SUCCESS.equals(rspCategoryInfo.getResult())) {
			getSession(request).setAttribute("topCateList",
					rspCategoryInfo.getInfoList());
		}
		model.addAttribute("brandList", rspInfo.getInfoList());
		model.addAttribute("totalPages", rspInfo.getCount());
		model.addAttribute("currentPage", "1");
		return "index";
	}

	/**
	 * 
	 * @Title: carousel
	 * @Description: 取轮播
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 上午8:04:56
	 */
	@RequestMapping("/carousel")
	@ResponseBody
	public String carousel(HttpServletRequest request) {
		// 取轮播图
		Map<String, Object> map = new HashMap<String, Object>();
		RspParameterInfo carousel = (RspParameterInfo) getResInfo(null,
				Constants.METHOD_GET, getBaseURL()
						+ UrlConstants.SystemCtrl.CAROUSEL,
				RspParameterInfo.class);
		if (Constants.SUCCESS.equals(carousel.getResult())) {
			map.put("carouselList", carousel.getInfoList());
		}
		return gson.toJson(map);
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 显示首页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping("/{pageNo}")
	public String index(@PathVariable String pageNo,
			HttpServletRequest request, Model model) {
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		RspBrandInfo rspInfo = (RspBrandInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(
								UrlConstants.BrandCtrl.INDEX_LIST, "score",
								pageNo), RspBrandInfo.class);
		model.addAttribute("brandList", rspInfo.getInfoList());
		model.addAttribute("totalPages", rspInfo.getCount());
		model.addAttribute("currentPage", pageNo);
		return "index";
	}

	/**
	 * 
	 * @Title: showSignup
	 * @Description: 显示注册页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignup(HttpServletRequest request, Model model) {
		if (!StringUtils.isEmpty(getUserResource(request))) {
			return "redirect:/";
		}
		model.addAttribute("lnvite_code", null);
		return "signup";
	}

	/**
	 * 
	 * @Title: showSignup
	 * @Description: 显示注册页(邀请链接过来)
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:24:20
	 */
	@RequestMapping(value = "/signup/{telephone}", method = RequestMethod.GET)
	public String showSignup(@PathVariable String telephone, HttpServletRequest request, Model model) {
		if (!StringUtils.isEmpty(getUserResource(request))) {
			return "redirect:/";
		}
		model.addAttribute("lnvite_code", telephone);
		return "signup";
	}

	/**
	 * 
	 * @Title: showSignin
	 * @Description: 显示登录页
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:25:59
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showSignin(HttpServletRequest request) {
		if (!StringUtils.isEmpty(getUserResource(request))) {
			return "redirect:/";
		}
		return "signin";
	}

	/**
	 * 
	 * @Title: search
	 * @Description: 关键词搜索
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:26:16
	 */
	@RequestMapping(value = "/srh", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Model model) {
		String keyword = request.getParameter("brand_name");
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		Brand brand = new Brand();
		brand.setBrand_name(keyword);
		// ReqBrandInfo reqInfo = new ReqBrandInfo();
		// reqInfo.setReqInfo(brand);// TODO 请求报文最好能统一一下
		String json = gson.toJson(brand);
		RspBrandInfo rspInfo = (RspBrandInfo) getResInfo(
				json,
				Constants.METHOD_POST,
				getBaseURL()
						+ MessageFormat.format(
								UrlConstants.BrandCtrl.SRH_BRAND, pageNo),
				RspBrandInfo.class);
		model.addAttribute("brandList", rspInfo.getInfoList());
		model.addAttribute("totalPages", rspInfo.getCount());
		model.addAttribute("currentPage", pageNo);
		return "list";
	}

	/**
	 * 
	 * @Title: showAbout
	 * @Description: 显示关于我们
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:26:16
	 */
	@RequestMapping("/about")
	public String showAbout() {
		log.info("=================showAbout=================");
		return "about";
	}

	/**
	 * 
	 * @Title: showAbout
	 * @Description: 显示网站公告
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:26:16
	 */
	@RequestMapping("/notice")
	public String showNotice() {
		log.info("=================showNotice=================");
		return "notice";
	}

	/**
	 * 
	 * @Title: getQRCode
	 * @Description: 取二维码
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 上午9:31:27
	 */
	@RequestMapping(value = "/getQ", method = { RequestMethod.GET })
	public void getQRCode(HttpServletResponse rsp) throws IOException {
		String url = "http://w.ziyoush.com";
		if (url != null && !"".equals(url)) {
			ServletOutputStream stream = null;
			try {

				int width = 200;// 图片的宽度
				int height = 200;// 高度
				stream = rsp.getOutputStream();
				QRCodeWriter writer = new QRCodeWriter();
				BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height,
						width);
				MatrixToImageWriter.writeToStream(m, "png", stream);
			} catch (WriterException e) {
				e.printStackTrace();
			} finally {
				if (stream != null) {
					stream.flush();
					stream.close();
				}
			}
		}
	}

	/**
	 * 
	 * @Title: subcate
	 * @Description: 快捷导航（一级分类）
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:26:16
	 */
	@RequestMapping("/subcate/{cate_pid}")
	@ResponseBody
	public String subcate(@PathVariable String cate_pid) {
		// 快捷导航（一级分类）
		RspCategoryInfo rspCategoryInfo = (RspCategoryInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.BrandCtrl.SUB_CATE,
								cate_pid), RspCategoryInfo.class);
		if (Constants.SUCCESS.equals(rspCategoryInfo.getResult())) {
			System.out.println(gson.toJson(rspCategoryInfo.getInfoList()));
			return gson.toJson(rspCategoryInfo.getInfoList());
		}
		return "";
	}

	/**
	 * 
	 * @Title: taglist
	 * @Description: 标签查询
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月15日 下午1:26:16
	 */
	@RequestMapping("/taglist/{cate_id}")
	@ResponseBody
	public String taglist(@PathVariable String cate_id) {
		// 标签查询
		RspTagInfo rspTagInfo = (RspTagInfo) getResInfo(
				null,
				Constants.METHOD_GET,
				getBaseURL()
						+ MessageFormat.format(UrlConstants.BrandCtrl.TAG_LIST,
								cate_id), RspTagInfo.class);
		if (Constants.SUCCESS.equals(rspTagInfo.getResult())) {
			System.out.println(gson.toJson(rspTagInfo.getInfoList()));
			return gson.toJson(rspTagInfo.getInfoList());
		}
		return "";
	}
}
