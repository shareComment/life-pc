package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.Comment;

/**
 * @ClassName: RspCommentInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 下午12:38:43
 * @company: sfteam
 */
public class RspCommentListInfo {

	public String result;

	public String errorInfo;

	public Integer count;

	public List<Comment> infoList;

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
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the infoList
	 */
	public List<Comment> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList
	 *            the infoList to set
	 */
	public void setInfoList(List<Comment> infoList) {
		this.infoList = infoList;
	}

}
