package com.qding.api.call.app.qding.v2_3_0.struct.payment.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.OfflinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.OnlinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.VirtualPayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.WalletStatus;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.CombinationPayMainMethod;
import com.qding.api.struct.ResponseData;
import java.util.List;

public class GetPayMethodResponseData extends ResponseData{

	private static final long serialVersionUID = -2666086848159760933L;

	@ExplainAnnotation(explain = "线上支付方式")
	private List<OnlinePayMethod> onlinePayMethods;

	@ExplainAnnotation(explain = "线下支付方式")
	private List<OfflinePayMethod> offlinePayMethods;

	@ExplainAnnotation(explain = "虚拟支付方式")
	private List<VirtualPayMethod> virtualPayMethods;

	@ExplainAnnotation(explain = "钱包状态")
	private WalletStatus walletStatus;

	@ExplainAnnotation(explain = "组合支付主支付方式",desc = "只针对满足组合支付的情况，如果是组合支付，虚拟支付里不会出现该支付方式，否则放入虚拟支付列表")
	private List<CombinationPayMainMethod> combinationPayMainMethods;

	@ExplainAnnotation (explain = "支持待客下单提示语")
	private String valetOrderContent;

	public String getValetOrderContent() {
		return valetOrderContent;
	}

	public void setValetOrderContent(String valetOrderContent) {
		this.valetOrderContent = valetOrderContent;
	}

	public GetPayMethodResponseData() {

	}

	public GetPayMethodResponseData(List<OnlinePayMethod> onlinePayMethods, List<OfflinePayMethod> offlinePayMethods, List<VirtualPayMethod> virtualPayMethods, WalletStatus walletStatus, List<CombinationPayMainMethod> combinationPayMainMethods) {
		this.onlinePayMethods = onlinePayMethods;
		this.offlinePayMethods = offlinePayMethods;
		this.virtualPayMethods = virtualPayMethods;
		this.walletStatus = walletStatus;
		this.combinationPayMainMethods = combinationPayMainMethods;
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

	public List<CombinationPayMainMethod> getCombinationPayMainMethods() {
		return combinationPayMainMethods;
	}

	public void setCombinationPayMainMethods(List<CombinationPayMainMethod> combinationPayMainMethods) {
		this.combinationPayMainMethods = combinationPayMainMethods;
	}

	@Override
	public String toString() {
		return "GetPayMethodResponseData{" +
				"onlinePayMethods=" + onlinePayMethods +
				", offlinePayMethods=" + offlinePayMethods +
				", virtualPayMethods=" + virtualPayMethods +
				", walletStatus=" + walletStatus +
				", combinationPayMainMethods=" + combinationPayMainMethods +
				", valetOrderContent='" + valetOrderContent + '\'' +
				'}';
	}
}
