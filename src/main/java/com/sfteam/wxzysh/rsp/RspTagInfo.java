package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.Tag;

/**
 * @ClassName: RspTagInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 上午8:44:51
 * @company: sfteam
 */
public class RspTagInfo {

	private String result;

	private String errorInfo;

	private List<Tag> infoList;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
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
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the infoList
	 */
	public List<Tag> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList
	 *            the infoList to set
	 */
	public void setInfoList(List<Tag> infoList) {
		this.infoList = infoList;
	}

}
