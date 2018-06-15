package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.io.Serializable;
/**
 * 存放投保人与被保险人基础信息
 * @author tgao
 *
 */
public class MemberAndInsurantInfo  implements Serializable{

	private static final long serialVersionUID = -6509612743726846713L;
	
	private String memberName; //投保人  姓名
	
	private String memberPhone; //投保人  手机号

    private String memberIdcard; //投保人  身份证号码
    
    private String insurantName; //被保险人 姓名
    
    private String insurantPhone; //被保险人 手机号

    private String insurantIdcard; //被保险人 身份证号码
    
    private String insurantRelation;  //投保人与被保险人关系
    
    

	public MemberAndInsurantInfo() {
	}
	
	

	public MemberAndInsurantInfo(String memberName, String memberPhone,
			String memberIdcard, String insurantName, String insurantPhone,
			String insurantIdcard, String insurantRelation) {
		super();
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberIdcard = memberIdcard;
		this.insurantName = insurantName;
		this.insurantPhone = insurantPhone;
		this.insurantIdcard = insurantIdcard;
		this.insurantRelation = insurantRelation;
	}



	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberIdcard() {
		return memberIdcard;
	}

	public void setMemberIdcard(String memberIdcard) {
		this.memberIdcard = memberIdcard;
	}

	public String getInsurantName() {
		return insurantName;
	}

	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}

	public String getInsurantPhone() {
		return insurantPhone;
	}

	public void setInsurantPhone(String insurantPhone) {
		this.insurantPhone = insurantPhone;
	}

	public String getInsurantIdcard() {
		return insurantIdcard;
	}

	public void setInsurantIdcard(String insurantIdcard) {
		this.insurantIdcard = insurantIdcard;
	}

	public String getInsurantRelation() {
		return insurantRelation;
	}

	public void setInsurantRelation(String insurantRelation) {
		this.insurantRelation = insurantRelation;
	}
    
    

}
