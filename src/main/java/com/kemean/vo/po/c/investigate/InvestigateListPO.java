package com.kemean.vo.po.c.investigate;

import com.kemean.vo.po.KemeanPageApiPO;

public class InvestigateListPO extends KemeanPageApiPO {

	private Integer investigateStatus;

	private Boolean releaseDate;

	private Boolean bonus;

	private Boolean isOver;

	public Boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

	public Boolean getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Boolean releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}

	public Integer getInvestigateStatus() {
		return investigateStatus;
	}

	public void setInvestigateStatus(Integer investigateStatus) {
		this.investigateStatus = investigateStatus;
	}

}
