package com.qding.api.call.app.qding.v2_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request.CreateFamilyRelationRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request.GetFamilyRelationListRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request.RelieveFamilyRelationRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.familypayment.response.data.*;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.PaymentInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.TicketInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.UserFamilyRelationInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.UserFamilyRelationType;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.oder.dto.ClosepayCouponDetailDto;
import com.qding.order.SearchParams.ClosepayRelation;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.request.GetClosepayCouponRequest;
import com.qding.order.struct.response.GetClosepayCouponResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberByMobileRequest;
import com.qding.passport.struct.response.GetMemberByMobileResponse;
import com.qding.useraccount.service.IRFamilyRelationPayRemoteService;
import com.qding.useraccount.service.IUserPredepositRemoteService;
import com.qding.useraccount.struct.PredepositFamilyQuotaInfo;
import com.qding.useraccount.struct.request.PredepositFamilyQuotaRequest;
import com.qding.useraccount.struct.response.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * 亲情支付
 */
public class CallFamilyPayment extends Callable {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallFamilyPayment.class);

    @Autowired
    private IRFamilyRelationPayRemoteService familyRelationPayRemoteService;

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private IUserPredepositRemoteService userPredepositRemoteService;

    @Autowired
    private IProfileService profileAPI;

    /**
     * 亲情支付首页
     * @param request
     * @return
     */
    @HTTP(alias = "index", isRequireAuth = true)
    @ExplainAnnotation(explain = " 亲情支付首页")
    public Response<GetFamilyRelationListResponseData> index(GetFamilyRelationListRequest request) {
        try {
            Response<GetFamilyRelationListResponseData> response = new Response<GetFamilyRelationListResponseData>();

            com.qding.useraccount.struct.request.GetFamilyRelationListRequest getFamilyRelationListRequest = new com.qding.useraccount.struct.request.GetFamilyRelationListRequest();
            getFamilyRelationListRequest.setMid(request.getMid());
            getFamilyRelationListRequest.setType(request.getType());
            getFamilyRelationListRequest.setCreateTimeBegin(request.getCreateTimeBegin());
            getFamilyRelationListRequest.setCreateTimeEnd(request.getCreateTimeEnd());
            GetFamilyRelationListResponse getFamilyRelationListResponse = familyRelationPayRemoteService.getRelationList(getFamilyRelationListRequest);
            List<com.qding.useraccount.struct.UserFamilyRelationInfo> ls = getFamilyRelationListResponse.getList();

            List<UserFamilyRelationInfo> list = transforList(com.qding.api.call.app.qding.v2_0_0.struct.useraccount.UserFamilyRelationInfo.class, ls);

            List<ClosepayRelation> closepayRelationList = Lists.newArrayList();
            for (UserFamilyRelationInfo relation : list) {
                //亲情支付-身份类型 1:开通者; 2:使用者
                ClosepayRelation closepayRelation = new ClosepayRelation();
                closepayRelation.setOwnerMid(relation.getMidFrom());
                closepayRelation.setUserMid(relation.getMidTo());
                closepayRelationList.add(closepayRelation);
            }

            //亲密付优惠券汇总信息查询
            GetClosepayCouponRequest getClosepayCouponRequest = new GetClosepayCouponRequest();
            getClosepayCouponRequest.setClosepayRelationList(closepayRelationList);
            GetClosepayCouponResponse getClosepayCouponResponse = orderService.getClosepayCouponList(getClosepayCouponRequest);
            List<ClosepayCouponDetailDto> closepayCouponDetailDtoList = Lists.newArrayList();
            if (getClosepayCouponResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                closepayCouponDetailDtoList = getClosepayCouponResponse.getClosepayCouponDetailDtoList();
            } else {
                logger.error("亲密付优惠券汇总信息查询失败:" + getClosepayCouponResponse.getReturnInfo().getMessage());
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                return response;
            }

            List<TicketInfo> ticketInfoList = transforList(TicketInfo.class, closepayCouponDetailDtoList);

            //亲密付钱包汇总信息查询
            PredepositFamilyQuotaRequest predepositFamilyQuotaRequest = new PredepositFamilyQuotaRequest();
            predepositFamilyQuotaRequest.setMid(request.getMid());
            predepositFamilyQuotaRequest.setAmount(new BigDecimal("0"));
            PredepositFamilyQuotaResponse predepositFamilyQuotaResponse = new PredepositFamilyQuotaResponse();
            if (request.getType().equals(Constant.FROMTOTYPE_FROM)) {
                predepositFamilyQuotaResponse = userPredepositRemoteService.getFamilyQuotaInfoByFrom(predepositFamilyQuotaRequest);
            } else if (request.getType().equals(Constant.FROMTOTYPE_TO)) {
                predepositFamilyQuotaResponse = userPredepositRemoteService.getFamilyQuotaInfoByTo(predepositFamilyQuotaRequest);
            }

            List<PredepositFamilyQuotaInfo> predepositFamilyQuotaInfoList = Lists.newArrayList();
            if (predepositFamilyQuotaResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                predepositFamilyQuotaInfoList = predepositFamilyQuotaResponse.getList();
            } else {
                logger.error("亲密付钱包汇总信息查询失败:" + predepositFamilyQuotaResponse.getReturnInfo().getMessage());
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                return response;
            }

            List<PaymentInfo> paymentList = transforList(PaymentInfo.class, predepositFamilyQuotaInfoList);

            for (UserFamilyRelationInfo relation : list) {
                for (PaymentInfo payment : paymentList) {
                    if (relation.getMidFrom().equals(payment.getMidFrom()) && relation.getMidTo().equals(payment.getMidTo())) {
                        relation.setPaymentInfo(payment);
                    }
                }
                for (TicketInfo ticket : ticketInfoList) {
                    if (relation.getMidFrom().equals(ticket.getMidFrom()) && relation.getMidTo().equals(ticket.getMidTo())) {
                        relation.setTicketInfo(ticket);
                    }
                }
            }


            response.setData(new GetFamilyRelationListResponseData(request.getType(), list));
            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetFamilyRelationListResponseData.class, e);
        }
    }

    /**
     * 开通亲情支付
     * @param request
     * @return
     */
    @HTTP(alias = "create", isRequireAuth = true)
    @ExplainAnnotation(explain = " 开通亲情支付")
    public Response<CreateFamilyRelationResponseData> create(CreateFamilyRelationRequest request) {
        try {
            Response<CreateFamilyRelationResponseData> response = new Response<CreateFamilyRelationResponseData>();

            com.qding.useraccount.struct.request.CreateFamilyRelationRequest createFamilyRelationRequest = new com.qding.useraccount.struct.request.CreateFamilyRelationRequest();
            createFamilyRelationRequest.setMobile(request.getMobile());
            createFamilyRelationRequest.setType(request.getType());
            createFamilyRelationRequest.setMidFrom(request.getMidFrom());
            CreateFamilyRelationResponse createFamilyRelationResponse = familyRelationPayRemoteService.createRelation(createFamilyRelationRequest);

            if (createFamilyRelationResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setData(new CreateFamilyRelationResponseData("开通成功"));
            } else {
                response.setData(new CreateFamilyRelationResponseData(createFamilyRelationResponse.getErrorMessage()));
            }
            response.setCode(createFamilyRelationResponse.getReturnInfo().getCode());

            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(CreateFamilyRelationResponseData.class, e);
        }
    }

    /**
     * 解除亲情支付
     * @param request
     * @return
     */
    @HTTP(alias = "relieve", isRequireAuth = true)
    @ExplainAnnotation(explain = "解除亲情支付")
    public Response<RelieveFamilyRelationResponseData> relieve(RelieveFamilyRelationRequest request) {
        try {
            Response<RelieveFamilyRelationResponseData> response = new Response<RelieveFamilyRelationResponseData>();
            if (!request.getType().equals(Constant.FROMTOTYPE_FROM) && !request.getType().equals(Constant.FROMTOTYPE_TO)) {
                response.setData(new RelieveFamilyRelationResponseData("非法参数type:" + request.getType()));
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                return response;
            }

            com.qding.useraccount.struct.request.RelieveFamilyRelationRequest relieveFamilyRelationRequest = new com.qding.useraccount.struct.request.RelieveFamilyRelationRequest();
            relieveFamilyRelationRequest.setMidFrom(request.getMidFrom());
            relieveFamilyRelationRequest.setMidTo(request.getMidTo());
            relieveFamilyRelationRequest.setType(request.getType());
            RelieveFamilyRelationResponse relieveFamilyRelationResponse = familyRelationPayRemoteService.relieveRelation(relieveFamilyRelationRequest);

            if (relieveFamilyRelationResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setData(new RelieveFamilyRelationResponseData("解绑成功"));
            } else {
                response.setData(new RelieveFamilyRelationResponseData(relieveFamilyRelationResponse.getErrorMessage()));
            }
            response.setCode(relieveFamilyRelationResponse.getReturnInfo().getCode());

            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(RelieveFamilyRelationResponseData.class, e);
        }
    }

    /**
     * 获取亲情支付关系类型
     * @param request
     * @return
     */
    @HTTP(alias = "relationType")
    @ExplainAnnotation(explain = "获取亲情支付关系类型")
    public Response<GetFamilyRelationTypeResponseData> getRelationType(BaseRequest request) {
        try {
            Response<GetFamilyRelationTypeResponseData> response = new Response<GetFamilyRelationTypeResponseData>();

            FamilyRelationTypeResponse familyRelationTypeResponse = familyRelationPayRemoteService.getRelationTypeList();

            if (familyRelationTypeResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                List<UserFamilyRelationType> list = transforList(UserFamilyRelationType.class, familyRelationTypeResponse.getList());
                response.setData(new GetFamilyRelationTypeResponseData(list));
            } else {
                response.setData(new GetFamilyRelationTypeResponseData(familyRelationTypeResponse.getErrorMessage()));
            }
            response.setCode(familyRelationTypeResponse.getReturnInfo().getCode());

            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetFamilyRelationTypeResponseData.class, e);
        }
    }

    /**
     * 校验亲情支付关系
     * @param request
     * @return
     */
    @HTTP(alias = "check")
    @ExplainAnnotation(explain = "校验亲情支付关系")
    public Response<CheckFamilyRelationResponseData> check(CreateFamilyRelationRequest request) {
        try {
            Response<CheckFamilyRelationResponseData> response = new Response<CheckFamilyRelationResponseData>();

            com.qding.useraccount.struct.request.CreateFamilyRelationRequest createFamilyRelationRequest = new com.qding.useraccount.struct.request.CreateFamilyRelationRequest();
            createFamilyRelationRequest.setMobile(request.getMobile());
            createFamilyRelationRequest.setMidFrom(request.getMidFrom());
            CheckFamilyRelationResponse checkFamilyRelationResponse = familyRelationPayRemoteService.checkRelation(createFamilyRelationRequest);

            ReturnInfo returnInfo = checkFamilyRelationResponse.getReturnInfo();
            CheckFamilyRelationResponseData data = new CheckFamilyRelationResponseData();
            if (returnInfo.getCode() < 100) {
                data.setStatus(returnInfo.getCode());
                data.setMessage(returnInfo.getMessage());
                response.setData(data);
            }

            if (checkFamilyRelationResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                GetMemberByMobileRequest getMemberByMobileRequest = new GetMemberByMobileRequest();
                getMemberByMobileRequest.setMobile(request.getMobile());
                GetMemberByMobileResponse memberByMobileResponse = profileAPI.getMemberByMobile(getMemberByMobileRequest);
                com.qding.api.call.app.qding.v1_3_0.struct.user.Member member = transfor(com.qding.api.call.app.qding.v1_3_0.struct.user.Member.class, memberByMobileResponse.getMember());
                data.setMember(member);
                response.setData(data);
            }

            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(CheckFamilyRelationResponseData.class, e);
        }
    }
}
