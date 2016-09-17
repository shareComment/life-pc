/**
 * 
 */
package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.Category;

/**
 * @ClassName: RspCategoryInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 上午7:59:05
 * @company: sfteam
 */
public class RspCategoryInfo {

	private List<Category> infoList;

	private String result;

	private String errorInfo;

	/**
	 * @return the infoList
	 */
	public List<Category> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList the infoList to set
	 */
	public void setInfoList(List<Category> infoList) {
		this.infoList = infoList;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	
}
