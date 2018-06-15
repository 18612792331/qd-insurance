package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class WalletStatus implements Serializable {


	private static final long serialVersionUID = -7058919630619269931L;

	@ExplainAnnotation (explain = "钱包状态",desc = "0 冻结 1 未冻结 2 未设置支付密码")
	private Integer status;
	
	@ExplainAnnotation (explain = "钱包状态提示")
	private String statusTips;
	
	public WalletStatus(){
        
    }

	public WalletStatus(Integer status, String statusTips) {
		this.status = status;
		this.statusTips = statusTips;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusTips() {
		return statusTips;
	}

	public void setStatusTips(String statusTips) {
		this.statusTips = statusTips;
	}

}
