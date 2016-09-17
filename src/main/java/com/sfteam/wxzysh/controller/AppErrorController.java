package com.sfteam.wxzysh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: AppErrorController.java
 * @Description: 异常处理
 * @author: knight
 * @date: 2016年7月14日 下午2:41:58
 * @company: sfteam
 */
@Controller
public class AppErrorController implements ErrorController {

	private final Logger log = Logger.getLogger(this.getClass());

	private static AppErrorController appErrorController;

	@Autowired
	private ErrorAttributes errorAttributes;

	private final static String ERROR_PATH = "/error";

	public AppErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	public AppErrorController() {
		if (appErrorController == null) {
			appErrorController = new AppErrorController(errorAttributes);
		}
	}

	/**
	 * 
	 * @Title: errorHtml
	 * @Description: 支持HTML
	 * @return: ModelAndView
	 * @author: knight
	 * @date: 2016年7月14日 下午2:46:42
	 */
	@RequestMapping(value = ERROR_PATH, produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request) {
		return new ModelAndView("greeting", getErrorAttributes(request, false));
	}

	/**
	 * 
	 * @Title: error
	 * @Description: 支持JSON等
	 * @return: ResponseEntity<Map<String,Object>>
	 * @author: knight
	 * @date: 2016年7月14日 下午2:47:08
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				getTraceParameter(request));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equals(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(
				request);
		Map<String, Object> map = this.errorAttributes.getErrorAttributes(
				requestAttributes, includeStackTrace);
		String URL = request.getRequestURL().toString();
		map.put("URL", URL);
		log.debug("AppErrorController.method [error info]: status-"
				+ map.get("status") + ", request url-" + URL);
		return map;
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
