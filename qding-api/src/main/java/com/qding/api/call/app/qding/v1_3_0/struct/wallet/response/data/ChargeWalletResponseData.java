package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.wallet.AfterChargeWallet;
import com.qding.api.struct.ResponseData;

public class ChargeWalletResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1433002710109443923L;

	
	private AfterChargeWallet entity;
	
	public ChargeWalletResponseData() {

	}

	public ChargeWalletResponseData(AfterChargeWallet entity) {
		super();
		this.entity = entity;
	}

	public AfterChargeWallet getEntity() {
		return entity;
	}

	public void setEntity(AfterChargeWallet entity) {
		this.entity = entity;
	}
	
}
