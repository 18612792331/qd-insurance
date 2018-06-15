package com.qding.api.call.app.qding.v1_3_1;

import javax.servlet.http.HttpServletRequest;

import com.qding.api.call.app.qding.v1_3_1.struct.payment.request.CardPayRequest;
import com.qding.api.call.service.OrderService;
import com.qding.oder.dto.OrderDetailDto;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_1.struct.payment.request.WalletPayRequest;
import com.qding.api.ip.IPUtil;
import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.GetOrderDetailResponse;
import com.qding.trade.rpc.service.input.dto.ClientParamsDto;
import com.qding.trade.rpc.service.pay.IPayService;
import com.qding.trade.rpc.service.result.dto.ClientDto;
import com.qding.useraccount.service.IRUserPayPasswordService;
import com.qding.useraccount.struct.request.UserPayPwdCheckRequest;
import com.qding.useraccount.struct.request.UserPayPwdRequest;
import com.qding.useraccount.struct.response.UserPayPwdCheckResponse;
import com.qding.useraccount.struct.response.UserPayPwdResponse;

/**
 * 支付接口
 * @author lichao
 *
 */
public class CallPayment extends com.qding.api.call.app.qding.v1_3_0.CallPayment{

	@Autowired
	private IPayService payService;
	
	@Autowired
	private IRUserPayPasswordService userPayPasswordService;
	
	@Autowired
	private OrderService apiOrderService;

	/**
	 * 钱包支付
	 * @param request
	 * @return
	 */
	@HTTP(alias="walletPay")
	@Deprecated
	public Response<ResponseData> walletPay(WalletPayRequest request, HttpServletRequest httpServletRequest) {

		try {

			OrderDetailDto orderDetailDto = apiOrderService.getOrderDetailByCode(request.getOrderId());

			String memberId = orderDetailDto.getOrderBase().getMid();
			
			UserPayPwdResponse getPasswordResponse = userPayPasswordService.getByMember(
					new UserPayPwdRequest(memberId)
			);
			
			checkAndContinue(getPasswordResponse);
			
			//用户设置了支付密码
			//验证用户支付密码
			if(getPasswordResponse.getUserPayPasswordInfo() != null) {
				
				if(QDStringUtil.isEmpty(request.getPassword())) {
					
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
			
			if(null != request.getAppDevice()) {
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



	/**
	 * 预存卡支付 （通过类型区分是哪种预存卡的支付方式）
	 * @param request
	 * @return
	 */
	@HTTP(alias="cardPay")
	public Response<ResponseData> cardPay(CardPayRequest request, HttpServletRequest httpServletRequest) {

		try {

			ClientParamsDto param = new ClientParamsDto();

			param.setIpAddress(IPUtil.getIpAddress(httpServletRequest));
			param.setOrderCode(request.getOrderId());
			param.setType(request.getCardType());

			if(null != request.getAppDevice()) {
				param.setQdPlatform(request.getAppDevice().getQdPlatform());
			}

			ClientDto client = payService.preStoreCardPay(param);

			checkAndContinue(client);

			Response<ResponseData> response = new Response<>();

			response.setData(new ResponseData());

			return response;
		} catch (Exception e) {

			return handleException(ResponseData.class, e);
		}
	}
	
}
