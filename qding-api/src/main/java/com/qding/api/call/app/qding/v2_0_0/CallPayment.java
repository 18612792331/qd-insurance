package com.qding.api.call.app.qding.v2_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.CallWallet;
import com.qding.api.call.app.qding.v1_3_0.struct.payment.*;
import com.qding.api.call.app.qding.v2_0_0.struct.payment.request.GetPayMethodRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.payment.request.WalletPayRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.payment.response.data.GetPayMethodResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.payment.FamilyPayBean;
import com.qding.api.call.app.qding.v2_3_0.struct.payment.CombinationInfoForThd;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.ip.IPUtil;
import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.GetOrderDetailResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.trade.rpc.service.domain.info.Channel;
import com.qding.trade.rpc.service.info.IChannelService;
import com.qding.trade.rpc.service.input.dto.ChannelParamsDto;
import com.qding.trade.rpc.service.input.dto.ClientParamsDto;
import com.qding.trade.rpc.service.pay.IPayService;
import com.qding.trade.rpc.service.result.dto.ChannelDto;
import com.qding.trade.rpc.service.result.dto.ClientDto;
import com.qding.useraccount.service.IRUserPayPasswordService;
import com.qding.useraccount.struct.request.UserPayPwdCheckRequest;
import com.qding.useraccount.struct.request.UserPayPwdRequest;
import com.qding.useraccount.struct.response.UserPayPwdCheckResponse;
import com.qding.useraccount.struct.response.UserPayPwdResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付接口
 * @author lichao
 *
 */
public class CallPayment extends com.qding.api.call.app.qding.v1_3_1.CallPayment {

	@Autowired
	private IChannelService channelService;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private IPayService payService;

	@Autowired
	private IRUserPayPasswordService userPayPasswordService;


	@Autowired
	private SkipModeFitting skipMode;

	private static final Logger logger = Logger.getLogger("CallPayment");


	@Autowired
	private IRemoteOrderService orderService;

	@Autowired
	private OrderService apiOrderService;

	@Autowired
	private IProfileService profileAPI;

	@Autowired
	private ProjectReadRemote projectReadService;

	/**
	 * 收银台付款方式（新增亲情支付方式）
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getPayMethod")
	@ExplainAnnotation(explain = "收银台",desc = "新增亲情支付方式")
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
			Long projectId = 0L;
			try {
				OrderDetailDto orderDetailDto =  apiOrderService.getOrderDetailByCode(request.getOrderId());
				projectId = orderDetailDto.getOrderBase().getProjectId();
			}catch (Exception e) {

			}

			fittingPaymentChannelList(channelList, onlinePayMethods, offlinePayMethods, virtualPayMethods,null, projectId);

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

	public void fittingPaymentChannelList(ChannelDto channelList, List<OnlinePayMethod> onlinePayMethods,
										  List<OfflinePayMethod> offlinePayMethods, List<VirtualPayMethod> virtualPayMethods,
										  CombinationInfoForThd combinationInfoForThd, Long projectId) {

		for (Channel channel : channelList.getChannelList()) {

            Integer paymentType = channel.getType();

			PayMethodBean payMethodBean = transfor(PayMethodBean.class,channel);

			//如果当前类型刚好是组合支付中附属支付方式
			if (QDStringUtil.isNotNull(combinationInfoForThd) && paymentType == combinationInfoForThd.getType()) {
				payMethodBean.setCombinationShouldPay(combinationInfoForThd.getShoudPay());
			}

            if (paymentType.intValue() == Constant.PAYMENT_TYPE_ALIPAY.intValue() || paymentType.intValue() == Constant.PAYMENT_TYPE_WEIXINAPP.intValue() || paymentType.intValue() == Constant.PAYMENT_TYPE_WEIXIN.intValue()
					|| paymentType.intValue() == Constant.PAYMENT_ONE_NET.intValue() || paymentType.intValue() == Constant.PAYMENT_DIANYIN.intValue()) {

				if (paymentType.intValue() == Constant.PAYMENT_ONE_NET.intValue()){
					try {
						String switchStatus = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_8.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_8.getDictKey());
						if (!"open".equals(switchStatus)) {
							Project project = projectReadService.get(projectId);
							if (project.getRegionId() != 1L) { //如果不是北京市的一网通 不显示
								continue;
							}
						}
					} catch (TException e) {
						e.printStackTrace();
					}
				}

                OnlinePayMethod method = transfor(OnlinePayMethod.class, payMethodBean);

                method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

                onlinePayMethods.add(method);
            } else if (paymentType.intValue() == Constant.PAYMENT_TYPE_WYHT.intValue() || paymentType.intValue() == Constant.PAYMENT_TYPE_POS.intValue() || paymentType.intValue() == Constant.PAYMENT_TYPE_CASH.intValue()) {

                if (paymentType == Constant.PAYMENT_TYPE_POS && projectId !=0L){
					//查看当前社区是否在不支持pos支付的范围里，如果是则跳出本次循环
					boolean isExt = Constant.posProject.contains(String.valueOf(projectId));
					if (isExt) continue;
				}

                OfflinePayMethod method = transfor(OfflinePayMethod.class, payMethodBean);

                method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

                offlinePayMethods.add(method);
            } else {
                VirtualPayMethod method = transfor(VirtualPayMethod.class, payMethodBean);

                method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));

				method.setValue(QDStringUtil.isNotNull(channel.getAvailableAmount())?String.valueOf(channel.getAvailableAmount().doubleValue()):"0.00");

				virtualPayMethods.add(method);
            }

        }

		List<Channel> IntimatePayList = channelList.getIntimatePayList();

		if (QDStringUtil.isNotNull(IntimatePayList) && IntimatePayList.size() > 0) {
            int i = 0;
            List<FamilyPayBean> familyPayBeanList = Lists.newArrayList();
            VirtualPayMethod method = new VirtualPayMethod();
            for (Channel channel : IntimatePayList) {
                FamilyPayBean familyPayBean = transfor(FamilyPayBean.class, channel);
                familyPayBeanList.add(familyPayBean);
                if (i == 0) {
                    method = transfor(VirtualPayMethod.class, channel);
                    method.setIcon(imageUtil.get(channel.getIconKey(), 100, 100));
                }
                i++;
            }
            method.setFamilyPayBean(familyPayBeanList);
            virtualPayMethods.add(method);
        }
	}

	/**
	 * 钱包支付
	 * @param request
	 * @return
	 */
	@HTTP(alias = "walletPay", isRequireAuth=true)
	@ExplainAnnotation(explain = "钱包支付")
	public Response<ResponseData> walletPay(WalletPayRequest request, HttpServletRequest httpServletRequest) {

		try {

			GetMemberRequest memberRequest = new GetMemberRequest();

			memberRequest.setMemberId(request.getPayMemberId());

			GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);

			if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {

				MemberInfo memberInfo = memberResponse.getMemberInfo();

				if (QDStringUtil.isNotNull(memberInfo.getStatus()) && memberInfo.getStatus() == 0) {

					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "支付者账户已被冻结,请选择其他支付方式");
				}
			}

			GetOrderDetailResponse orderDetailResponse = orderService.getOrderDetailByCode(request.getOrderId());

			checkAndContinue(orderDetailResponse);

			String memberId = orderDetailResponse.getOrderDetailDto().getOrderBase().getMid();

			UserPayPwdResponse getPasswordResponse = userPayPasswordService.getByMember(
					new UserPayPwdRequest(memberId)
			);

			checkAndContinue(getPasswordResponse);

			//用户设置了支付密码
			//验证用户支付密码
			if (getPasswordResponse.getUserPayPasswordInfo() != null) {

				if (QDStringUtil.isEmpty(request.getPassword())) {

					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请输入钱包支付密码");

				}
				UserPayPwdCheckResponse pwdCheckResponse = userPayPasswordService.check(
						new UserPayPwdCheckRequest(memberId, request.getPassword())
				);

				try {

					checkAndContinue(pwdCheckResponse);

				} catch (ServiceException e) {

					WalletValidate.validateLeftTimes(memberId);
				}

			}

			ClientParamsDto param = new ClientParamsDto();

			param.setIpAddress(IPUtil.getIpAddress(httpServletRequest));
			param.setOrderCode(request.getOrderId());
			param.setPayMid(request.getPayMemberId());

			if (null != request.getAppDevice()) {
				param.setQdPlatform(request.getAppDevice().getQdPlatform());
			}

			ClientDto client = payService.walletPay(param);

			checkAndContinue(client);

			Response<ResponseData> response = new Response<>();

			response.setData(new ResponseData());

			return response;

		} catch (Exception e) {

			return handleException(ResponseData.class, e);
		}

	}

}
