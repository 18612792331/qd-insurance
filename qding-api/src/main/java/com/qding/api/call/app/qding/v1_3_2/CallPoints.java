package com.qding.api.call.app.qding.v1_3_2;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_2.struct.points.request.PointsDetailRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.points.request.PointsExpendDetailRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.points.request.PointsRevenueDetailRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.points.response.data.PointsDetailResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.points.response.data.PointsExpendDetailResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.points.response.data.PointsRevenueDetailResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.useraccount.common.AccountOptTypeEnum;
import com.qding.useraccount.service.IUserIntegralRemoteService;
import com.qding.useraccount.struct.request.IntegralDetailRequest;
import com.qding.useraccount.struct.response.IntegralDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *会员积分接口
 * @author jiawenzheng
 */
public class CallPoints extends Callable{


	@Autowired
	private IUserIntegralRemoteService userIntegralRemoteService;

	/**
	 * 积分明细
	 * @param request
	 * @return
	 */
	@HTTP(alias = "pointsDetail")
	public Response<PointsDetailResponseData> pointsDetail(PointsDetailRequest request) {

		Response<PointsDetailResponseData> response = new Response<PointsDetailResponseData>();
		try {

			IntegralDetailResponse integralDetailResponse = userIntegralRemoteService.getDetails(transfor(IntegralDetailRequest.class,request));
			checkAndContinue(integralDetailResponse);
			PointsDetailResponseData  responseData = transfor(PointsDetailResponseData.class,integralDetailResponse);
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(responseData);

		} catch (ServiceException e) {
			return handleException(PointsDetailResponseData.class, e);
		}

			return  response;
		}

		/**
		 * 积分支出
		 * @param request
		 * @return
		 */
		@HTTP(alias = "pointsExpendDetail")
		public Response<PointsExpendDetailResponseData> pointsRefundDetail(PointsExpendDetailRequest request) {

			Response<PointsExpendDetailResponseData> response = new  Response<PointsExpendDetailResponseData>();

			try {
				IntegralDetailRequest integralDetailRequest = transfor(IntegralDetailRequest.class, request);
				integralDetailRequest.setOptType(AccountOptTypeEnum.EXPENDITURE);
				IntegralDetailResponse integralDetailResponse = userIntegralRemoteService.getDetails(integralDetailRequest);
				checkAndContinue(integralDetailResponse);
				PointsExpendDetailResponseData  responseData = transfor(PointsExpendDetailResponseData.class,integralDetailResponse);
				response.setCode(HttpStatus.OK.getStatusCode());
				response.setData(responseData);

			} catch (ServiceException e) {
				return handleException(PointsExpendDetailResponseData.class, e);
		}

				return  response;
			}


			/**
			 * 积分收入
			 * @param request
			 * @return
			 */
			@HTTP(alias = "pointsRevenueDetail")
			public Response<PointsRevenueDetailResponseData> pointsRevenueDetail(PointsRevenueDetailRequest request) {


				Response<PointsRevenueDetailResponseData> response = new Response<PointsRevenueDetailResponseData>();

				try {
					IntegralDetailRequest integralDetailRequest = transfor(IntegralDetailRequest.class, request);
					integralDetailRequest.setOptType(AccountOptTypeEnum.DEPOSIT);
					IntegralDetailResponse integralDetailResponse = userIntegralRemoteService.getDetails(integralDetailRequest);
					checkAndContinue(integralDetailResponse);
					PointsRevenueDetailResponseData  responseData = transfor(PointsRevenueDetailResponseData.class,integralDetailResponse);
					response.setCode(HttpStatus.OK.getStatusCode());
					response.setData(responseData);

				} catch (ServiceException e) {
					return handleException(PointsRevenueDetailResponseData.class, e);
				}

				return  response;
			}

		}
