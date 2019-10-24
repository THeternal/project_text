package com.kemean.vo.bo.admin.investigate;

import java.util.List;

public class AdminInvestQuestionAnwserBO {
	// 答案列表
	private List<AdminInvestUserAnwserBO> anwserBO;
	// 问题列表
	private List<AdminInvestQuestionBO> questionBO;

	public List<AdminInvestUserAnwserBO> getAnwserBO() {
		return anwserBO;
	}

	public void setAnwserBO(List<AdminInvestUserAnwserBO> anwserBO) {
		this.anwserBO = anwserBO;
	}

	public List<AdminInvestQuestionBO> getQuestionBO() {
		return questionBO;
	}

	public void setQuestionBO(List<AdminInvestQuestionBO> questionBO) {
		this.questionBO = questionBO;
	}

}
