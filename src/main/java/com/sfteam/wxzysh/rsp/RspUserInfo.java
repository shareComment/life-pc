package com.sfteam.wxzysh.rsp;

import com.sfteam.wxzysh.dto.User;

/**
 * @ClassName: RspUserInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 下午1:59:57
 * @company: sfteam
 */
public class RspUserInfo {

	public String result;

	public String errorInfo;

	public User rspInfo;

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
	 * @return the rspInfo
	 */
	public User getRspInfo() {
		return rspInfo;
	}

	/**
	 * @param rspInfo
	 *            the rspInfo to set
	 */
	public void setRspInfo(User rspInfo) {
		this.rspInfo = rspInfo;
	}

}
