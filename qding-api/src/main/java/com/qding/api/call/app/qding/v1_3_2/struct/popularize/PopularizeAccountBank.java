package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/3.
 */
public class PopularizeAccountBank implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8693327101310042450L;
	private String id;
    private String popularizeAccountId;
    private String bankId;
    private String bankCardNo;
    private Integer status;
    private Long createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPopularizeAccountId() {
		return popularizeAccountId;
	}
	public void setPopularizeAccountId(String popularizeAccountId) {
		this.popularizeAccountId = popularizeAccountId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
    
    
}
