package com.qding.api.call.app.qding.v1_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.*;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data.WexinQRCodeArgument;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.request.GetPayArgumentRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.request.GetPayMethodRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.request.WalletPayRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data.GetPayArgumentResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data.GetPayMethodResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data.WalletPayResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.ip.IPUtil;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.trade.rpc.service.client.IClientService;
import com.qding.trade.rpc.service.domain.info.Channel;
import com.qding.trade.rpc.service.info.IChannelService;
import com.qding.trade.rpc.service.input.dto.ChannelParamsDto;
import com.qding.trade.rpc.service.input.dto.ClientParamsDto;
import com.qding.trade.rpc.service.pay.IPayService;
import com.qding.trade.rpc.service.result.dto.ChannelDto;
import com.qding.trade.rpc.service.result.dto.ClientDto;

/**
 * 支付接口
 * @author lichao
 *
 */
public class CallPayment extends Callable{

	@Autowired
	private IChannelService channelService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private IPayService payService;

	private static final Logger logger = Logger.getLogger("CallPayment");

	/**
	 * 获取收银台可支付方式
	 * @param request
	 * @return
     */
	@HTTP(alias="getPayMethod")
	@Deprecated
	public Response<GetPayMethodResponseData> getPayMethod(GetPayMethodRequest request) {

		try {
			Response<GetPayMethodResponseData> response = new Response<>();

			ChannelParamsDto param = new ChannelParamsDto();

			param.setBusinessType(request.getPayBusinessType());

			param.setMid(request.getMemberId());

			String platForm = request.getAppDevice().getQdPlatform();

			logger.info(" url platform is "+param.getQdPlatform());

			if(("alipay").equals(platForm)){
				param.setQdPlatform("weixin");
			} else {
				param.setQdPlatform(platForm);
			}
			logger.info(" the platform is "+param.getQdPlatform());

			param.setOrderCode(request.getOrderId());

			ChannelDto channelList = channelService.getChannelListInfo(param);

			checkAndContinue(channelList);

			List<OnlinePayMethod> onlinePayMethods = new ArrayList<>();
			List<OfflinePayMethod> offlinePayMethods = new ArrayList<>();
			List<VirtualPayMethod> virtualPayMethods = new ArrayList<>();

			for(Channel channel : channelList.getChannelList()) {

				Integer paymentType = channel.getType();

				if(paymentType == Constant.PAYMENT_TYPE_ALIPAY || paymentType == Constant.PAYMENT_TYPE_WEIXINAPP || paymentType==Constant.PAYMENT_TYPE_WEIXIN) {

					OnlinePayMethod method = transfor(OnlinePayMethod.class, channel);

					method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

					onlinePayMethods.add(method);
				}
				else if(paymentType == Constant.PAYMENT_TYPE_POS || paymentType == Constant.PAYMENT_TYPE_CASH) {

					OfflinePayMethod method = transfor(OfflinePayMethod.class, channel);

					method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

					offlinePayMethods.add(method);
				}
				else{

					VirtualPayMethod method = transfor(VirtualPayMethod.class, channel);

					method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

					virtualPayMethods.add(method);
				}

			}

			GetPayMethodResponseData data = new GetPayMethodResponseData(
					onlinePayMethods,
					offlinePayMethods,
					virtualPayMethods,
					CallWallet.getWalletStatus(request.getMemberId()));

			response.setData(data);

			return response;
		} catch (Exception e) {

			return handleException(GetPayMethodResponseData.class, e);
		}
	}

	/**
	 * 获取支付参数
	 * @param request
	 * @return
	 */
	@HTTP(alias="getPayArgument")
	public Response<GetPayArgumentResponseData> getPayArgument(GetPayArgumentRequest request) {

		try {

			Response<GetPayArgumentResponseData> response = new Response<>();
			GetPayArgumentResponseData data = new GetPayArgumentResponseData();

			ClientParamsDto param = new ClientParamsDto();
			param.setType(request.getType());
			param.setOrderCode(request.getOrderId());

			if(null != request.getAppDevice()) {
				param.setQdPlatform(request.getAppDevice().getQdPlatform());
			}

			if (QDStringUtil.isNotNull(request.getCombinationPayType())) {
				switch (request.getCombinationPayType()) {

					case 1:
						param.setPayWithPreDeposit(1);
						break;
					case 2:
						param.setPayWithIntegral(1);
						break;
					default:
						break;
				}
			}
			param.setFlag("twoImg");

			ClientDto client = clientService.getClientAppSignData(param);
			checkAndContinue(client);
			String jsonData = client.getData();
			JSONObject json = JSON.parseObject(jsonData);

			if(request.getType() == Constant.PAYMENT_TYPE_WEIXINAPP) {

				WeixinPayArgument argument = new WeixinPayArgument(
						json.getString("appId"),
						json.getString("package"),
						json.getString("partnerId"),
						json.getString("sign"),
						json.getString("nonceStr"),
						json.getString("prepayId"),
						json.getString("timeStamp")
				);
				data.setWeixinPayArgument(argument);
			}
			else if(request.getType() == Constant.PAYMENT_TYPE_ALIPAY) {

				StringBuffer join = new StringBuffer();
				join.append(concatParam(json, "partner"))
						.append(concatParam(json, "seller_id"))
						.append(concatParam(json, "out_trade_no"))
						.append(concatParam(json, "subject"))
						.append(concatParam(json, "body"))
						.append(concatParam(json, "total_fee"))
						.append(concatParam(json, "notify_url"))
						.append(concatParam(json, "service"))
						.append(concatParam(json, "_input_charset"))
						.append(concatParam(json, "return_url"))
						.append(concatParam(json, "it_b_pay"))
						.append(concatParam(json, "sign_type"))
						.append(concatParam(json, "sign"));

				AlipayArgument argument = new AlipayArgument(join.toString().substring(0, join.toString().length()-1));

				data.setAlipayArgument(argument);
			}
			else if(request.getType() == Constant.PAYMENT_TYPE_POS) {

				String qrCodeKey = json.getString("iconKey");//二维码
				String barCode = json.getString("iconKeyOne");//条形码
				String tradeNo = json.getString("tradeNo");//流水号
				PosPayArgument argument = new PosPayArgument(imageUtil.get(qrCodeKey, 200, 200),imageUtil.get(barCode),tradeNo);
				data.setPosPayArgument(argument);
			}
			else if(request.getType()==Constant.PAYMENT_TYPE_WEIXIN){
				String qrCodeKey = json.getString("iconKey");
				WexinQRCodeArgument argument = new WexinQRCodeArgument(imageUtil.get(qrCodeKey, 200, 200));
				data.setWexinQRCodeArgument(argument);
			}
			else if (request.getType().intValue() == Constant.PAYMENT_ONE_NET.intValue()) {//一网通
				OneNetArgument oneNetArgument  = new OneNetArgument();
				oneNetArgument.setPayUrl(json.getString("payUrl"));
				oneNetArgument.setSignData(json.getString("signData"));
				data.setOneNetArgument(oneNetArgument);
			} else if (request.getType().intValue() == Constant.PAYMENT_DIANYIN.intValue()) {//电银
				DianYinPayArgument dianYinPayArgument  =  JSON.parseObject(jsonData,DianYinPayArgument.class);
				data.setDianYinPayArgument(dianYinPayArgument);
			}

			response.setData(data);
			return response;

		} catch (Exception e) {
			return handleException(GetPayArgumentResponseData.class, e);
		}
	}


	/**
	 * 钱包支付
	 * @param request
	 * @return
	 */
	@HTTP(alias="walletPay")
	@Deprecated
	public Response<WalletPayResponseData> walletPay(WalletPayRequest request, HttpServletRequest httpServletRequest) {

		try {
			ClientParamsDto param = new ClientParamsDto();

			param.setIpAddress(IPUtil.getIpAddress(httpServletRequest));
			param.setOrderCode(request.getOrderId());

			if(null != request.getAppDevice()) {
				param.setQdPlatform(request.getAppDevice().getQdPlatform());
			}

			ClientDto client = payService.walletPay(param);

			checkAndContinue(client);

			Response<WalletPayResponseData> response = new Response<>();

			response.setData(new WalletPayResponseData());

			return response;
		} catch (Exception e) {

			return handleException(WalletPayResponseData.class, e);
		}
	}

	private String concatParam(JSONObject json, String key) {

		return concatParam(key, json.getString(key));
	}

	private String concatParam(String key, String value) {

		return key + "=\"" + value +"\"&";
	}
}
