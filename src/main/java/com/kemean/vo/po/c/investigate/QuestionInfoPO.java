package com.kemean.vo.po.c.investigate;

import java.util.List;

public class QuestionInfoPO {

	private Integer questionId;

	private List<String> answer;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

}
