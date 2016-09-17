package com.sfteam.wxzysh.rsp;

import com.sfteam.wxzysh.dto.Comment;

/**
 * @ClassName: RspCommentInfo.java
 * @Description: 评论
 * @author: knight
 * @date: 2016年7月27日 上午11:48:01
 * @company: sfteam
 */
public class RspCommentInfo {

	public String result;

	public String errorInfo;

	public Integer count;

	public Comment info;

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
	 * @return the info
	 */
	public Comment getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(Comment info) {
		this.info = info;
	}

}
