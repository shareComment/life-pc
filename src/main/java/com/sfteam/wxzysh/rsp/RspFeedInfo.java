package com.sfteam.wxzysh.rsp;

/**
 * @ClassName: RspFeedInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 上午16:48:49
 * @company: sfteam
 */
public class RspFeedInfo {

	private String result;

	private String errorInfo;

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
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
