package com.sfteam.wxzysh.dto;

/**
 * @ClassName: SysMsg.java
 * @Description: 系统消息
 * @author: knight
 * @date: 2016年7月15日 下午8:09:23
 * @company: sfteam
 */
public class SysMsg {

	private String sms_id;

	private String title;

	private String content;

	private String send_time;

	private String status;

	/**
	 * @return the sms_id
	 */
	public String getSms_id() {
		return sms_id;
	}

	/**
	 * @param sms_id
	 *            the sms_id to set
	 */
	public void setSms_id(String sms_id) {
		this.sms_id = sms_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the send_time
	 */
	public String getSend_time() {
		return send_time;
	}

	/**
	 * @param send_time
	 *            the send_time to set
	 */
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
