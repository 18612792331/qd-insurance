package com.qding.api.call.app.qding.v2_3_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.OfflinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.OnlinePayMethod;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.VirtualPayMethod;
import com.qding.api.call.app.qding.v1_4_0.CallWallet;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.CombinationInfoForThd;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.CombinationPayMainMethod;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.request.GetPayMethodRequest;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.response.data.GetPayMethodResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.GetOrderDetailResponse;
import com.qding.trade.rpc.service.client.IClientService;
import com.qding.trade.rpc.service.domain.info.Channel;
import com.qding.trade.rpc.service.info.IChannelService;
import com.qding.trade.rpc.service.input.dto.ChannelParamsDto;
import com.qding.trade.rpc.service.input.dto.ClientPrePayParamsDto;
import com.qding.trade.rpc.service.result.dto.ChannelDto;
import com.qding.trade.rpc.service.result.dto.ClientPrePayAmountDto;
import com.qding.useraccount.service.IUserPredepositRemoteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付接口
 * @author lichao
 *
 */
@ExplainAnnotation (explain = "支付")
public class CallPayment extends com.qding.api.call.app.qding.v2_0_0.CallPayment {

	@Autowired
	private IChannelService channelService;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private IClientService clientService;

	@Autowired
	private OrderService apiOrderService;

	@Autowired
	private SkipModeFitting skipMode;

	private static final Logger logger = Logger.getLogger("CallPayment");


	/**
	 * 收银台付款方式（组合支付方式）
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getPayMethod")
	@ExplainAnnotation(explain = "收银台",desc = "组合支付")
	public Response<GetPayMethodResponseData> getPayMethod(GetPayMethodRequest request) {

		Response<GetPayMethodResponseData> response = new Response<>();

		try {
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
			param.setFlag("mix");

			Long projectId = 0L;
			try {
				OrderDetailDto orderDetailDto = apiOrderService.getOrderDetailByCode(request.getOrderId());
				projectId = orderDetailDto.getOrderBase().getProjectId();
			}catch (Exception e) {

			}

			ChannelDto channelList = channelService.getChannelListInfo(param);
			checkAndContinue(channelList);
			List<OnlinePayMethod> onlinePayMethods = new ArrayList<>();
			List<OfflinePayMethod> offlinePayMethods = new ArrayList<>();
			List<VirtualPayMethod> virtualPayMethods = new ArrayList<>();
			List<CombinationPayMainMethod> priorityPayMethods = new ArrayList<>();
			List<Channel> virtualPaymentList = channelList.getVirtualPaymentList();
			CombinationInfoForThd  combinationInfoForThd = null;

			//如果支持组合支付，则获取每种支付方式的应付金额
			if (channelList.getMixPay()) {

					ClientPrePayParamsDto clientPrePayParamsDto = new ClientPrePayParamsDto();
					clientPrePayParamsDto.setOrderCode(request.getOrderId());
					ClientPrePayAmountDto clientPrePayAmountDto = clientService.getPrePayAmount(clientPrePayParamsDto);
					//第三方支付金额
					Double thdAmount = clientPrePayAmountDto.getThdAmount();
					//预存款支付金额
					Double preDepositAmount = clientPrePayAmountDto.getPreDepositAmount();

					for (Channel channel : virtualPaymentList) {

						if ( Constant.PAYMENT_TYPE_WALLET_CARD != channel.getType()) {

							BigDecimal amount = BigDecimal.valueOf(thdAmount);
							combinationInfoForThd = new CombinationInfoForThd(channel.getType(),String.valueOf(amount.doubleValue()));
						} else {
							CombinationPayMainMethod method = transfor(CombinationPayMainMethod.class,channel);
							method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));
							BigDecimal amount = BigDecimal.valueOf(preDepositAmount);
							method.setQuotaAmount(method.getValue());//目前限额金额等于钱包总金额
							method.setCombinationShouldPay(String.valueOf(amount.doubleValue()));
							priorityPayMethods.add(method);
						}
					}
			}

			fittingPaymentChannelList(channelList, onlinePayMethods, offlinePayMethods, virtualPayMethods, combinationInfoForThd, projectId);

			//3.1版本和其他低版本的兼容
			if (CollectionUtils.isNotEmpty(offlinePayMethods) && !"QBCZ".equals(request.getPayBusinessType())) {
				String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
				if (Integer.parseInt(initVersion) <310000) {
					if ((!"AY".equals(request.getPayBusinessType()) && !"CB".equals(request.getPayBusinessType()))) {
						int i =0;
						for (OfflinePayMethod offlinePayMethod : offlinePayMethods) {
							if (offlinePayMethod.getType().intValue() == Constant.PAYMENT_TYPE_POS.intValue()){
								offlinePayMethods.remove(i);
								break;
							}
							i++;
						}
					}
				}
			}
			GetPayMethodResponseData data = new GetPayMethodResponseData(
					onlinePayMethods,
					offlinePayMethods,
					virtualPayMethods,
					CallWallet.getWalletStatus(request.getMemberId()),
					priorityPayMethods
					);

			if (channelList.isValetOrder()) {
				data.setValetOrderContent(Constant.VALET_ORDER_CONTENT);
			}
			response.setData(data);

			return response;
		} catch (Exception e) {

			return handleException(GetPayMethodResponseData.class, e);
		}
	}




}
