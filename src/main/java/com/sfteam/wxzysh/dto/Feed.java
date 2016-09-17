package com.sfteam.wxzysh.dto;

/**
 * @ClassName: Feed.java
 * @Description: 反馈
 * @author: knight
 * @date: 2016年7月23日 下午4:58:31
 * @company: sfteam
 */
public class Feed {

	private String feed_con;

	private String contact;

	private String user_id;

	/**
	 * @return the feed_con
	 */
	public String getFeed_con() {
		return feed_con;
	}

	/**
	 * @param feed_con the feed_con to set
	 */
	public void setFeed_con(String feed_con) {
		this.feed_con = feed_con;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
