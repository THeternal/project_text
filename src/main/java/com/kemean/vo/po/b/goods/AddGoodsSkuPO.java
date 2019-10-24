package com.kemean.vo.po.b.goods;

import java.util.List;

public class AddGoodsSkuPO {

	/**
	 * id
	 */
	private Integer objId;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 折扣
	 */
	private Float discount;

	/**
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 规格类型记录
	 */
	private List<RecordTypePO> recordTypePO;

	/**
	 * 库存
	 */
	private Integer stock;

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public List<RecordTypePO> getRecordTypePO() {
		return recordTypePO;
	}

	public void setRecordTypePO(List<RecordTypePO> recordTypePO) {
		this.recordTypePO = recordTypePO;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
