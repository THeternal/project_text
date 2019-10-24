package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_investigate_question")
public class DaikenInvestigateQuestion extends KemeanAbstractBaseBean {

	/**
	 * 调研id
	 */
	@Column(name = "investigate_id")
	private Integer investigateId;

	/**
	 * 区分（单选，多选，简答题）
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 问题
	 */
	@Column(name = "question")
	private String question;

	/**
	 * 答案选项-json
	 */
	@Column(name = "record_answer")
	private String recordAnswer;

	public Integer getInvestigateId() {
		return investigateId;
	}

	public void setInvestigateId(Integer investigateId) {
		this.investigateId = investigateId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRecordAnswer() {
		return recordAnswer;
	}

	public void setRecordAnswer(String recordAnswer) {
		this.recordAnswer = recordAnswer;
	}

}