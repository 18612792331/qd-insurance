package com.qding.api.call.app.qding.v1_4_0;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionOrder;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.MemberAuctionHistory;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.OfflineAuction;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.*;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.AuctionOrderHistoryRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.MemberAuctionHistoryRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.MemberAuctionOrderHistoryRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.OfflineAuctionRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.UpdateAuctionShareStatusRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.request.WinningOrderRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data.*;
import com.qding.api.struct.Response;
import com.qding.auction.rpc.request.*;
import com.qding.auction.rpc.response.*;
import com.qding.auction.rpc.service.IAuctionRpcService;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.service.IUserIntegralRemoteService;
import com.qding.useraccount.struct.request.IntegralInfoRequest;
import com.qding.useraccount.struct.response.IntegralInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andy on 15-9-2.
 */
public class CallAuction extends Callable {


    @Autowired
    private IAuctionRpcService auctionRpcService;

    @Autowired
    private IUserIntegralRemoteService userIntegralRemoteService;


    /**
     * 获取指定商品竞拍记录
     * @param request
     * @return
     */
    @HTTP(alias = "getAuctionOrderHistory")
    public Response<AuctionOrderHistoryResponseData> getAuctionRecord(AuctionOrderHistoryRequest request) {

       try {

           Response<AuctionOrderHistoryResponseData> response = new Response<AuctionOrderHistoryResponseData>();

           AuctionOrderHistoryResponse auctionOrderHistoryResponse =  auctionRpcService.getAuctionOrderHistory(transfor(com.qding.auction.rpc.request.AuctionOrderHistoryRequest.class, request));

           checkAndContinue(auctionOrderHistoryResponse);

           AuctionOrderHistoryResponseData data = new AuctionOrderHistoryResponseData();

           List<AuctionOrder>  list = transforList(AuctionOrder.class,auctionOrderHistoryResponse.getResultPage().getItems());

           data.setList(list);

           data.setTotalCount(auctionOrderHistoryResponse.getResultPage().getTotalCount());

           response.setData(data);

           response.setCode(HttpStatus.OK.getStatusCode());

           return response;

       }catch (Exception ex) {

           return handleException(AuctionOrderHistoryResponseData.class, ex);
       }
    }

    /**
     * 获取会员竞拍历史
     * @param request
     * @return
     */
    @HTTP(alias = "getMemberAuctionHistory")
    public Response<MemberAuctionHistoryResponseData> getMemberAuctionHistory(MemberAuctionHistoryRequest request){

        try {

            Response<MemberAuctionHistoryResponseData> response = new Response<MemberAuctionHistoryResponseData>();

            MemberAuctionHistoryResponse memberAuctionHistoryResponse = auctionRpcService.getMemberAuctionHistory(transfor(com.qding.auction.rpc.request.MemberAuctionHistoryRequest.class, request));

            checkAndContinue(memberAuctionHistoryResponse);

            List<MemberAuctionHistory> list =  transforList(MemberAuctionHistory.class,memberAuctionHistoryResponse.getResultPage().getItems());

            MemberAuctionHistoryResponseData data = new MemberAuctionHistoryResponseData();

            data.setList(list);

            data.setTotalCount(memberAuctionHistoryResponse.getResultPage().getTotalCount());

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        }catch (Exception ex) {

            return handleException(MemberAuctionHistoryResponseData.class, ex);
        }
    }


    /**
     * 获取会员竞拍历史订单
     * @param request
     * @return
     */
    @HTTP(alias = "getMemberAuctionOrderHistory")
    public Response<MemberAuctionOrderHistoryResponseData> getMemberAuctionOrderHistory(MemberAuctionOrderHistoryRequest request){

        try {

            Response<MemberAuctionOrderHistoryResponseData> response = new Response<MemberAuctionOrderHistoryResponseData>();

            MemberAuctionOrderHistoryResponse memberAuctionOrderHistoryResponse = auctionRpcService.getMemberAuctionOrderHistory(transfor(com.qding.auction.rpc.request.MemberAuctionOrderHistoryRequest.class, request));

            checkAndContinue(memberAuctionOrderHistoryResponse);

            MemberAuctionOrderHistoryResponseData data = transfor(MemberAuctionOrderHistoryResponseData.class,memberAuctionOrderHistoryResponse);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        }catch (Exception ex) {

            return handleException(MemberAuctionOrderHistoryResponseData.class, ex);
        }
    }


    /**
     * 获取在线竞拍数据
     * @return
     */
    @HTTP(alias = "getOnlineAuction")
    public Response<OnlineAuctionResponseData> getOnlineAuction() {

        try {

            Response<OnlineAuctionResponseData> response = new Response<OnlineAuctionResponseData>();

            OnlineAuctionResponse onlineAuctionResponse = auctionRpcService.getOnlineAuction();

            checkAndContinue(onlineAuctionResponse);

            OnlineAuctionResponseData data = transfor(OnlineAuctionResponseData.class,onlineAuctionResponse);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        }catch (Exception ex) {

            return handleException(OnlineAuctionResponseData.class, ex);
        }
    }


    /**
     * 获取下线竞拍数据
     * @param request
     * @return
     */
    @HTTP(alias = "getOfflineAuctionList")
    public Response<OfflineAuctionResponseData> getOfflineAuctionList(OfflineAuctionRequest request){

        try {

            Response<OfflineAuctionResponseData> response = new Response<OfflineAuctionResponseData>();

            OfflineAuctionResponse offlineAuctionResponse = auctionRpcService.getOfflineAuction(transfor(com.qding.auction.rpc.request.OfflineAuctionRequest.class,request));

            checkAndContinue(offlineAuctionResponse);

            OfflineAuctionResponseData data = new OfflineAuctionResponseData();

            List<OfflineAuction> list = transforList(OfflineAuction.class,offlineAuctionResponse.getResultPage().getItems());

            data.setTotalCount(offlineAuctionResponse.getResultPage().getTotalCount());

            data.setList(list);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        }catch (Exception ex) {

            return handleException(OfflineAuctionResponseData.class, ex);
        }
    }

    /**
     *夺宝详情（静态）
     * @param request
     * @return
     */
    @HTTP(alias = "getOfflineAuction")
    public Response<WinningOrderResponseData> getOfflineAuction (WinningOrderRequest request){

        Response<WinningOrderResponseData> response = new Response<WinningOrderResponseData>();

        int bidCount = 0;

        try {

            if ( QDStringUtil.isNotNull(request.getMemberId()) && QDStringUtil.isNotEmpty(request.getMemberId())) {

                MemberAuctionOrderHistoryResponse memberAuctionOrderHistoryResponse = auctionRpcService.getMemberAuctionOrderHistory(transfor(com.qding.auction.rpc.request.MemberAuctionOrderHistoryRequest.class, request));

                checkAndContinue(memberAuctionOrderHistoryResponse);

                bidCount = memberAuctionOrderHistoryResponse.getList().size();
            }

            WinningOrderResponse winningOrderResponse = auctionRpcService.getWinningOrder(transfor(com.qding.auction.rpc.request.WinningOrderRequest.class,request));

            checkAndContinue(winningOrderResponse);

            String detailImge= winningOrderResponse.getAuctionDetail().getAuctionImages();

            String[] imgs = detailImge.split(",");

            List imgList = Arrays.asList(imgs);

            WinningOrderResponseData data = transfor(WinningOrderResponseData.class,winningOrderResponse);

            data.getAuctionInfo().setDetailImagUrl(imgList);

            if (QDStringUtil.isNotNull(data.getAuctionOrder())) {

                String mobile =  data.getAuctionOrder().getMobile();

                if (mobile.length()==11){

                    StringBuffer mobileBuffer = new StringBuffer(mobile);
                    mobileBuffer.replace(3,7,"****");
                    data.getAuctionOrder().setMobile(mobileBuffer.toString());
                }
            }

            ParticipateUserByAuctionIdRequest  participateUserByAuctionIdRequest = new ParticipateUserByAuctionIdRequest();

            participateUserByAuctionIdRequest.setAuctionId(request.getAuctionId());

            ParticipateUserByAuctionIdResponse participateUserByAuctionIdResponse = auctionRpcService.getParticipateUserByAuctionId(participateUserByAuctionIdRequest);

            List userList = participateUserByAuctionIdResponse.getAuctionParticipateUser();

            data.setParticipateCount(QDStringUtil.isNotNull(userList)?userList.size():0);

            data.setBidCount(bidCount);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

        } catch (ServiceException ex) {

            return handleException(WinningOrderResponseData.class, ex);
        }

        return response;
    }

    /**
     * 修改活动晒单状态
     * @param request
     * @return
     */
    @HTTP(alias = "updateAuctionShareStatus")
    public Response<UpdateAuctionShareStatusResponseData> UpdateAuctionShareStatus (UpdateAuctionShareStatusRequest request){

        Response<UpdateAuctionShareStatusResponseData> response = new Response<UpdateAuctionShareStatusResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            UpdateAuctionShareStatusResponse updateAuctionShareStatusResponse = auctionRpcService.updateAuctionShareStatus(transfor(com.qding.auction.rpc.request.UpdateAuctionShareStatusRequest.class,request ));
            checkAndContinue(updateAuctionShareStatusResponse);

        }catch (Exception ex) {
            return handleException(UpdateAuctionShareStatusResponseData.class, ex);
        }

        UpdateAuctionShareStatusResponseData data = new UpdateAuctionShareStatusResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);

        return  response;
    }

    /**
     * 根据会员ID获取当前会员剩余积分
     * @param request
     * @return
     */
    @HTTP(alias = "getIntegralByMemberId")
    public Response<GetIntegralByMemberIdResponseData> getIntegralByMemberId (GetIntegralByMemberIdRequest request) {

        Response<GetIntegralByMemberIdResponseData> response = new  Response<GetIntegralByMemberIdResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            IntegralInfoRequest integralInfoRequest =  new IntegralInfoRequest();

            integralInfoRequest.setMid(request.getMemberId());

            IntegralInfoResponse integralInfoResponse =  userIntegralRemoteService.get(integralInfoRequest);

            checkAndContinue(integralInfoResponse);

            BigDecimal accountIntegral =  integralInfoResponse.getAvailableNum();

            GetIntegralByMemberIdResponseData data = new GetIntegralByMemberIdResponseData();

            data.setMemberIntegral(accountIntegral.longValue());

            response.setData(data);

        } catch (ServiceException e) {

            return handleException(GetIntegralByMemberIdResponseData.class, e);
        }

        return  response;
    }

}


