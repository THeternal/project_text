package com.kemean.vo.bo.c.mall;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsAppraisalListBO {

	/**
	 * 评论用户头像
	 */
	private String userHeadImg;

	/**
	 * 评论用户名称
	 */
	private String userNickName;

	/**
	 * 评分等级
	 */
	private Integer score;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 反馈图
	 */
	private List<String> contentImg;

	/**
	 * 评论日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getContentImg() {
		return contentImg;
	}

	public void setContentImg(List<String> contentImg) {
		this.contentImg = contentImg;
	}

}
