package com.sfteam.wxzysh.dto;

/**
 * @ClassName: Comment.java
 * @Description: 评论
 * @author: knight
 * @date: 2016年7月15日 上午11:59:15
 * @company: sfteam
 */
public class Comment {

	private String comment_id;

	private String brand_id;

	private String user_id;

	private String comment_pid;

	private String comment;

	private String comment_img;

	private String add_time;

	private String nickname;

	private String avatar;

	private String grade_Name;

	private String grade_Color;

	private String brand_name;

	private String score;

	private Integer zan;

	/**
	 * 评论的回复数
	 */
	private String isComment;

	/**
	 * @return the comment_id
	 */
	public String getComment_id() {
		return comment_id;
	}

	/**
	 * @param comment_id
	 *            the comment_id to set
	 */
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	/**
	 * @return the brand_id
	 */
	public String getBrand_id() {
		return brand_id;
	}

	/**
	 * @param brand_id
	 *            the brand_id to set
	 */
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the comment_pid
	 */
	public String getComment_pid() {
		return comment_pid;
	}

	/**
	 * @param comment_pid
	 *            the comment_pid to set
	 */
	public void setComment_pid(String comment_pid) {
		this.comment_pid = comment_pid;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment_img
	 */
	public String getComment_img() {
		return comment_img;
	}

	/**
	 * @param comment_img
	 *            the comment_img to set
	 */
	public void setComment_img(String comment_img) {
		this.comment_img = comment_img;
	}

	/**
	 * @return the add_time
	 */
	public String getAdd_time() {
		return add_time;
	}

	/**
	 * @param add_time
	 *            the add_time to set
	 */
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the isComment
	 */
	public String getIsComment() {
		return isComment;
	}

	/**
	 * @param isComment
	 *            the isComment to set
	 */
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	/**
	 * @return the grade_Name
	 */
	public String getGrade_Name() {
		return grade_Name;
	}

	/**
	 * @param grade_Name
	 *            the grade_Name to set
	 */
	public void setGrade_Name(String grade_Name) {
		this.grade_Name = grade_Name;
	}

	/**
	 * @return the grade_Color
	 */
	public String getGrade_Color() {
		return grade_Color;
	}

	/**
	 * @param grade_Color
	 *            the grade_Color to set
	 */
	public void setGrade_Color(String grade_Color) {
		this.grade_Color = grade_Color;
	}

	/**
	 * @return the brand_name
	 */
	public String getBrand_name() {
		return brand_name;
	}

	/**
	 * @param brand_name
	 *            the brand_name to set
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the zan
	 */
	public Integer getZan() {
		return zan;
	}

	/**
	 * @param zan the zan to set
	 */
	public void setZan(Integer zan) {
		this.zan = zan;
	}

}
