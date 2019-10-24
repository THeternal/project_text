package com.kemean.vo.bo.c.investigate;

import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class QuestionInfoBO extends KemeanIdBO {

	private Integer type;

	private String question;

	private List<RecordAnswerBO> recordAnswerBO;

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

	public List<RecordAnswerBO> getRecordAnswerBO() {
		return recordAnswerBO;
	}

	public void setRecordAnswerBO(List<RecordAnswerBO> recordAnswerBO) {
		this.recordAnswerBO = recordAnswerBO;
	}

}
