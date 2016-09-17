package com.sfteam.wxzysh.dto;

/**
 * @ClassName: Category.java
 * @Description: 分类
 * @author: knight
 * @date: 2016年7月15日 上午7:57:13
 * @company: sfteam
 */
public class Category {

	private String cate_id;

	private String cate_pid;

	private String cate_name;

	/**
	 * @return the cate_id
	 */
	public String getCate_id() {
		return cate_id;
	}

	/**
	 * @param cate_id
	 *            the cate_id to set
	 */
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}

	/**
	 * @return the cate_pid
	 */
	public String getCate_pid() {
		return cate_pid;
	}

	/**
	 * @param cate_pid
	 *            the cate_pid to set
	 */
	public void setCate_pid(String cate_pid) {
		this.cate_pid = cate_pid;
	}

	/**
	 * @return the cate_name
	 */
	public String getCate_name() {
		return cate_name;
	}

	/**
	 * @param cate_name
	 *            the cate_name to set
	 */
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

}
