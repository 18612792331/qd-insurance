package com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.payment.OfflinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.OnlinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.VirtualPayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.WalletStatus;
import com.qding.api.struct.ResponseData;

public class GetPayMethodResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2666086848159760933L;
 

	private List<OnlinePayMethod> onlinePayMethods;
	
	private List<OfflinePayMethod> offlinePayMethods;
	
	private List<VirtualPayMethod> virtualPayMethods;
	
	private WalletStatus walletStatus;
	
	public GetPayMethodResponseData() {

	}


	public GetPayMethodResponseData(List<OnlinePayMethod> onlinePayMethods,
			List<OfflinePayMethod> offlinePayMethods,
			List<VirtualPayMethod> virtualPayMethods,
			WalletStatus walletStatus) {
		super();
		this.onlinePayMethods = onlinePayMethods;
		this.offlinePayMethods = offlinePayMethods;
		this.virtualPayMethods = virtualPayMethods;
		this.walletStatus = walletStatus;
	}

	public WalletStatus getWalletStatus() {
		return walletStatus;
	}
	
	public void setWalletStatus(WalletStatus walletStatus) {
		this.walletStatus = walletStatus;
	}

	public List<OfflinePayMethod> getOfflinePayMethods() {
		return offlinePayMethods;
	}


	public void setOfflinePayMethods(List<OfflinePayMethod> offlinePayMethods) {
		this.offlinePayMethods = offlinePayMethods;
	}


	public List<OnlinePayMethod> getOnlinePayMethods() {
		return onlinePayMethods;
	}

	public void setOnlinePayMethods(List<OnlinePayMethod> onlinePayMethods) {
		this.onlinePayMethods = onlinePayMethods;
	}

	public List<VirtualPayMethod> getVirtualPayMethods() {
		return virtualPayMethods;
	}

	public void setVirtualPayMethods(List<VirtualPayMethod> virtualPayMethods) {
		this.virtualPayMethods = virtualPayMethods;
	}
	
}
