package com.qding.api.call.app.qding.v1_3_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.plateFormOrder.request.DeletePlateFormOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.plateFormOrder.response.data.DeletePlateFormOrderResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.legou.struct.request.*;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.DelOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 平台订单
 * @author lichao
 *
 */
@ExplainAnnotation (explain = "平台订单")
public class CallPlateFormOrder extends Callable{

	@Autowired
	private IRemoteOrderService orderService;

	@Autowired
	private OrderService apiOrderService;

	/**
	 * 删除订单
	 * @param request
	 * @return
	 */
	@ExplainAnnotation (explain = "删除平台订单")
	@HTTP(alias="deletePlateFormOrder")
	public Response<DeletePlateFormOrderResponseData> deletePlateFormOrder(DeletePlateFormOrderRequest request) {
		
		try {
			Response<DeletePlateFormOrderResponseData> response = new Response<DeletePlateFormOrderResponseData>();
			
			checkOrderAuth(request.getMemberId(), request.getOrderCode());
			
			com.qding.legou.struct.request.DeleteOrderRequest deleteOrderRequest = 
					transfor(com.qding.legou.struct.request.DeleteOrderRequest.class, request);

			DelOrderResponse deleteOrderResposne = orderService.delOrder(request.getOrderCode());
			
			checkAndContinue(deleteOrderResposne);
			
			response.setData(new DeletePlateFormOrderResponseData());
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(DeletePlateFormOrderResponseData.class, e);
		}
	}


	/**
	 * 验证删除订单权限
	 * @param memberId
	 * @param orderCode
	 * @return
	 * @throws ServiceException
     */
	private boolean checkOrderAuth(String memberId, String orderCode)
			throws ServiceException {

		GetOrderDetailByCodeRequest getOrderRequest = new GetOrderDetailByCodeRequest();

		getOrderRequest.setOrderCode(orderCode);

		OrderDetailDto orderDetailDto = apiOrderService.getOrderDetailByCode(orderCode);

		if(!memberId.equals(orderDetailDto.getOrderBase().getMid())) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "此用户没有此订单权限");
		}

		return true;
	}

}
