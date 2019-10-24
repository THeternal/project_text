package com.kemean.vo.bo.c.mall;

public class NewGoodsActivityListBO extends NewGoodsListBO {

	/**
	 * 库存
	 */
	private Integer countStock;

	/**
	 * 销量
	 */
	private Integer salesNum;

	/**
	 * 最低折扣
	 */
	private Float minDiscount;

	/**
	 * 广告排序(1101一屏,1201二屏,1301三屏)
	 */
	private Integer adOrderSort;

	public Integer getAdOrderSort() {
		return adOrderSort;
	}

	public void setAdOrderSort(Integer adOrderSort) {
		this.adOrderSort = adOrderSort;
	}

	public Integer getCountStock() {
		return countStock;
	}

	public void setCountStock(Integer countStock) {
		this.countStock = countStock;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Float getMinDiscount() {
		return minDiscount;
	}

	public void setMinDiscount(Float minDiscount) {
		this.minDiscount = minDiscount;
	}

}
