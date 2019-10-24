package com.kemean.vo.po.admin.investigate;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdminPrecisionUserPO {

	@Size(min = 1, message = "推送的用户不能为空")
	private List<Integer> userIds;
	@NotNull(message = "问卷Id不能为空")
	private Integer investId;

	/**
	 * 匹配条件
	 */
	@NotNull(message = "匹配条件不能为 空")
	private AdminMatchConditionPO matchCondition;

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}

	public Integer getInvestId() {
		return investId;
	}

	public void setInvestId(Integer investId) {
		this.investId = investId;
	}

	public AdminMatchConditionPO getMatchCondition() {
		return matchCondition;
	}

	public void setMatchCondition(AdminMatchConditionPO matchCondition) {
		this.matchCondition = matchCondition;
	}

}
