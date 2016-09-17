package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.SysMsg;

/**
 * @ClassName: RspSysMsgInfo.java
 * @Description: 系统消息
 * @author: knight
 * @date: 2016年7月15日 下午8:11:50
 * @company: sfteam
 */
public class RspSysMsgInfo {

	private String result;

	private String errorInfo;

	private String count;

	private List<SysMsg> infoList;

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
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the infoList
	 */
	public List<SysMsg> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList
	 *            the infoList to set
	 */
	public void setInfoList(List<SysMsg> infoList) {
		this.infoList = infoList;
	}
}
