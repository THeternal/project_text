package com.kemean.vo.po.c.investigate;

import java.util.List;

import javax.validation.constraints.Size;

/**
 * @author Administrator
 *
 */
public class QuestionInvestigateInfoPO {

	private Integer investigateInId;

	@Size(min = 1, message = "提交的问题不能少于1")
	private List<QuestionInfoPO> questionInfoPO;

	public Integer getInvestigateInId() {
		return investigateInId;
	}

	public void setInvestigateInId(Integer investigateInId) {
		this.investigateInId = investigateInId;
	}

	public List<QuestionInfoPO> getQuestionInfoPO() {
		return questionInfoPO;
	}

	public void setQuestionInfoPO(List<QuestionInfoPO> questionInfoPO) {
		this.questionInfoPO = questionInfoPO;
	}

}
