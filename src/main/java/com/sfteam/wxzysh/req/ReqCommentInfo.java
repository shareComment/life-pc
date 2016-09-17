package com.sfteam.wxzysh.req;

import com.sfteam.wxzysh.dto.Comment;

/**
 * @ClassName: ReqCommentInfo.java
 * @Description: 请求信息
 * @author: knight
 * @date: 2016年7月23日 下午12:36:07
 * @company: sfteam
 */
public class ReqCommentInfo {

	private Comment reqInfo;

	/**
	 * @return the reqInfo
	 */
	public Comment getReqInfo() {
		return reqInfo;
	}

	/**
	 * @param reqInfo
	 *            the reqInfo to set
	 */
	public void setReqInfo(Comment reqInfo) {
		this.reqInfo = reqInfo;
	}

}
