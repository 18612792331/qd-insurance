package com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.*;
import com.qding.api.struct.ResponseData;

public class GetPayArgumentResponseData extends ResponseData{


	private static final long serialVersionUID = 7726116131969509752L;

	@ExplainAnnotation (explain = "支付宝支付")
	private AlipayArgument alipayArgument;

	@ExplainAnnotation (explain = "微信支付")
	private WeixinPayArgument weixinPayArgument;

	@ExplainAnnotation (explain = "pos支付")
	private PosPayArgument posPayArgument;

	@ExplainAnnotation (explain = "微信二维码支付")
	private WexinQRCodeArgument wexinQRCodeArgument;

	@ExplainAnnotation (explain = "一网通支付参数")
	private OneNetArgument oneNetArgument;

	@ExplainAnnotation (explain = "电银支付参数")
	private DianYinPayArgument dianYinPayArgument;


    public WexinQRCodeArgument getWexinQRCodeArgument() {
        return wexinQRCodeArgument;
    }

    public void setWexinQRCodeArgument(WexinQRCodeArgument wexinQRCodeArgument) {
        this.wexinQRCodeArgument = wexinQRCodeArgument;
    }

    public GetPayArgumentResponseData() {

	}

	
	public PosPayArgument getPosPayArgument() {
		return posPayArgument;
	}


	public void setPosPayArgument(PosPayArgument posPayArgument) {
		this.posPayArgument = posPayArgument;
	}


	public AlipayArgument getAlipayArgument() {
		return alipayArgument;
	}

	public void setAlipayArgument(AlipayArgument alipayArgument) {
		this.alipayArgument = alipayArgument;
	}

	public WeixinPayArgument getWeixinPayArgument() {
		return weixinPayArgument;
	}

	public void setWeixinPayArgument(WeixinPayArgument weixinPayArgument) {
		this.weixinPayArgument = weixinPayArgument;
	}

	public OneNetArgument getOneNetArgument() {
		return oneNetArgument;
	}

	public void setOneNetArgument(OneNetArgument oneNetArgument) {
		this.oneNetArgument = oneNetArgument;
	}


	public DianYinPayArgument getDianYinPayArgument() {
		return dianYinPayArgument;
	}

	public void setDianYinPayArgument(DianYinPayArgument dianYinPayArgument) {
		this.dianYinPayArgument = dianYinPayArgument;
	}

	@Override
	public String toString() {
		return "GetPayArgumentResponseData{" +
				"alipayArgument=" + alipayArgument +
				", weixinPayArgument=" + weixinPayArgument +
				", posPayArgument=" + posPayArgument +
				", wexinQRCodeArgument=" + wexinQRCodeArgument +
				", oneNetArgument=" + oneNetArgument +
				", dianYinPayArgument=" + dianYinPayArgument +
				'}';
	}
}
