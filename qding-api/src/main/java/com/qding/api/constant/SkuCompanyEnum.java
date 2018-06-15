package com.qding.api.constant;

/**
 * 商品信息来源公司
 * @author wang.cheng.liang 
 * time 2018.05.23
 *
 */
public enum SkuCompanyEnum {
   
	JD("JD", "(京东价)"),
	YX("YX", "(严选价)");
	
	private String type;
	
	private String desc;
	
	SkuCompanyEnum(String type , String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 根据第三方货品序列号获取价格说明
	 */
	public  static String getPriceDesc (String skuBn){
		if (skuBn.startsWith(JD.getType()))
			return JD.getDesc();
		else if (skuBn.startsWith(YX.getType()))
			return YX.getDesc();
		return "";
	}
	
	
}
