package com.qding.api.call.app.qding.v3_1_1;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.MakeOrderResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGood;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateCouponBean;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesSaveBean;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.InvoiceBean;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.LegouCrabsDelivery;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request.*;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data.*;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.ip.IPUtil;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.evaluation.remote.qdapp.IQdAppRemote;
import com.qding.evaluation.struct.qdapp.bean.EvaluateFlagBean;
import com.qding.evaluation.struct.qdapp.bean.EvaluatePromotionCouponBean;
import com.qding.evaluation.struct.qdapp.bean.EvaluateSourcesBean;
import com.qding.evaluation.struct.qdapp.bean.EvaluteSourceBatchSaveBean;
import com.qding.evaluation.struct.qdapp.request.*;
import com.qding.evaluation.struct.qdapp.response.EvaluateFlagInfoResponse;
import com.qding.evaluation.struct.qdapp.response.EvaluateReturnCouponResponse;
import com.qding.evaluation.struct.qdapp.response.EvaluateSourceBatchSaveResponse;
import com.qding.evaluation.struct.qdapp.response.EvaluateUserDetailResponse;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.CreateCrabsGorderRequest;
import com.qding.legou.struct.request.GetCrabsDeliverResultPageRequest;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.response.CreateGorderResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.member.service.IMemberInvoiceRpcService;
import com.qding.member.vo.MemberInvoiceVo;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.domain.OrderBase;
import com.qding.order.domain.OrderSub;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.GetOrderDetailResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.SkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by qd on 2017/2/23.
 */
@ExplainAnnotation(explain = "乐购")
public class CallLegouOrder extends com.qding.api.call.app.qding.v3_1_0.CallLegouOrder {

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private IQdAppRemote evaluationRemote;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IntegralMessage integralMessage;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMemberInvoiceRpcService iMemberInvoiceRpcService;

    @Autowired
    private OrderService apiOrderService;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallLegouOrder.class);


    @HTTP(alias = "getOrderEvaluateDataPreparation", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "获取订单评价的准备数据")
    public Response<GetOrderEvaluateDataPreparationResponseData> getOrderEvaluateDataPreparation(GetOrderEvaluateDataPreparationRequest request) {

        Response<GetOrderEvaluateDataPreparationResponseData> response = new Response<GetOrderEvaluateDataPreparationResponseData>();
        try {
            GetOrderEvaluateDataPreparationResponseData data = new GetOrderEvaluateDataPreparationResponseData();
            GetOrderDetailResponse getOrderDetailResponse = orderService.getOrderDetailByCode(request.getOrderCode());
            checkAndContinue(getOrderDetailResponse);
            OrderDetailDto orderDetailDto = getOrderDetailResponse.getOrderDetailDto();
            List<OrderGood> orderGoodList = getOrderGoods(orderDetailDto);
            EvaluateFlagInfoRequest evaluateFlagInfoRequest = new EvaluateFlagInfoRequest();
            evaluateFlagInfoRequest.setProductNo(Constant.PRODUCT_NO_NG);
            evaluateFlagInfoRequest.setProjectId(String.valueOf(orderDetailDto.getOrderBase().getProjectId()));
            EvaluateFlagInfoResponse evaluateFlagInfoResponse = evaluationRemote.queryFlagInfos(evaluateFlagInfoRequest);
            List<EvaluateFlagBean> evaluateFlagBeanList = evaluateFlagInfoResponse.getList();
            data.setOrderGoods(orderGoodList);
            data.setList(transforList(com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateFlagBean.class, evaluateFlagBeanList));
            data.setEvaluatePrompt(evaluateFlagInfoResponse.getEvaluatePrompt());
            data.setPromotionName(evaluateFlagInfoResponse.getCouponPrice());
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetOrderEvaluateDataPreparationResponseData.class, e);
        }
        return response;
    }


    @HTTP(alias = "getEvaluateUserDetailByOrderCode", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取当前用户订单评价")
    public Response<GetEvaluateUserDetailByOrderCodeResponseData> getEvaluateUserDetailByOrderCode(GetEvaluateUserDetailByOrderCodeRequest request) {

        Response<GetEvaluateUserDetailByOrderCodeResponseData> response = new Response<GetEvaluateUserDetailByOrderCodeResponseData>();
        GetEvaluateUserDetailByOrderCodeResponseData data = new GetEvaluateUserDetailByOrderCodeResponseData();
        try {
            EvaluateUserDetailRequest evaluateUserDetailRequest = new EvaluateUserDetailRequest();
            evaluateUserDetailRequest.setOriginSourceCode(request.getOrderCode());
            EvaluateUserDetailResponse evaluateUserDetailResponse = evaluationRemote.queryEvaluateUserDetail(evaluateUserDetailRequest);
            checkAndContinue(evaluateUserDetailResponse);
            List<EvaluateSourcesBean> evaluateSourcesBeanList = evaluateUserDetailResponse.getList();
            List<com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesBean> list = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(evaluateSourcesBeanList)) {
                for (EvaluateSourcesBean evaluateSourcesBean : evaluateSourcesBeanList) {
                    com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesBean entity =
                            transfor(com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesBean.class, evaluateSourcesBean);
                    String images = evaluateSourcesBean.getImages();
                    List<String> evaluateImgList = Lists.newArrayList();
                    if (QDStringUtil.isNotEmpty(images)) {
                        evaluateImgList = JSON.parseArray(images,String.class);
                    }
                    entity.setEvaluateImgs(evaluateImgList);
                    entity.setMemberName(evaluateSourcesBean.getUserName());
                    entity.setMemberId(evaluateSourcesBean.getUserId());
                    entity.setGoodsImg(evaluateSourcesBean.getSourceImg());
                    entity.setGoodsName(evaluateSourcesBean.getSourceName());
                    entity.setSkuId(evaluateSourcesBean.getSourceCode());
                    entity.setGoodsDesc(getGoodsDetail(Long.parseLong(evaluateSourcesBean.getSourceCode())));
                    entity.setEvaluateContent(evaluateSourcesBean.getSourceValue());
                    entity.setAnonymousFlag(evaluateSourcesBean.getAnonymousFlag());
                    list.add(entity);
                }
                data.setList(list);
                response.setCode(HttpStatus.OK.getStatusCode());
                response.setData(data);
            }
        } catch (ServiceException e) {
            return handleException(GetEvaluateUserDetailByOrderCodeResponseData.class, e);
        }
        return response;
    }


    public String getGoodsDetail(Long skuId) {
        String goodsDesc = "";
        List<Long> skuIds = Lists.newArrayList();
        skuIds.add(skuId);
        SkuRequest skuRequest = new SkuRequest();
        skuRequest.setSortedSkuIds(skuIds);
        SkuResponse skuResponse = solrSku.querySku(skuRequest);
        List<SkuDetailInfo> skuDetailInfoList = skuResponse.getSkus();
        if (CollectionUtils.isNotEmpty(skuDetailInfoList)) {
            SkuDetailInfo skuDetailInfo = skuDetailInfoList.get(0);
            goodsDesc = skuDetailInfo.getDescription();
        }
        return goodsDesc;
    }

    @HTTP(alias = "saveEvaluateSource", isRequireAuth = true, isNeadToken = true, isNeadProject = true)
    @ExplainAnnotation(explain = "保存订单评价信息", desc = "多个商品评级，一个评价内容")
    public Response<SaveEvaluateSourceResponseData> saveEvaluateSource(SaveEvaluateSourceRequest request, UserToken userToken, HttpServletRequest httpServletRequest) {

        Response<SaveEvaluateSourceResponseData> response = new Response<SaveEvaluateSourceResponseData>();
        SaveEvaluateSourceResponseData data = new SaveEvaluateSourceResponseData();
        try {
            String accountId = userToken.getAccountId();
            String memberId = userToken.getMemberId();
            int anonymousFlag = request.getAnonymousFlag();
            String ip = IPUtil.getIpAddress(httpServletRequest);
            String orderCode = request.getOrderCode();

            OrderDetailDto orderDetailDto = apiOrderService.getOrderDetailByCode(orderCode);
            OrderBase orderBase = orderDetailDto.getOrderBase();
            Long projectId = orderBase.getProjectId();
            String projectName = orderBase.getProjectName();
            Long regionId = orderBase.getRegionId();
            String regionName = orderBase.getRegionName();

            GetMemberRequest memberRequest = new GetMemberRequest();
            memberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();

            List<EvaluateSourcesSaveBean> evaluateList = request.getList();
            if (CollectionUtils.isNotEmpty(evaluateList)) {
                List<Long> skuIds = Lists.newArrayList();
                for (EvaluateSourcesSaveBean evaluateSourcesSaveBean : evaluateList) {
                    skuIds.add(Long.parseLong(evaluateSourcesSaveBean.getSkuId()));
                }
                SkuRequest skuRequest = new SkuRequest();
                skuRequest.setSortedSkuIds(skuIds);
                skuRequest.setFindAllStatus(true);
                skuRequest.setFindWareAllSku(true);
                SkuResponse skuResponse = solrSku.querySku(skuRequest);
                checkAndContinue(skuResponse);
                List<SkuDetailInfo> skuDetailList = skuResponse.getSkus();
                Map<String, SkuDetailInfo> skuImgMap = new HashMap<>();
                if (CollectionUtils.isNotEmpty(skuDetailList)) {
                    for (SkuDetailInfo skuDetailInfo : skuDetailList) {
                        skuImgMap.put(skuDetailInfo.getSkuId(), skuDetailInfo);
                    }
                }

                List<EvaluteSourceBatchSaveBean> evaluteList = Lists.newArrayList();
                for (EvaluateSourcesSaveBean evaluateSourcesSaveBean : evaluateList) {
                    SkuDetailInfo skuDetailInfo = skuImgMap.get(evaluateSourcesSaveBean.getSkuId());
                    EvaluteSourceBatchSaveBean evaluteSourceBatchSaveBean = new EvaluteSourceBatchSaveBean();
                    evaluteSourceBatchSaveBean.setOriginSourceCode(orderCode);//订单号
                    evaluteSourceBatchSaveBean.setSourceCode(evaluateSourcesSaveBean.getSkuId());//商品ID
                    evaluteSourceBatchSaveBean.setSourceImg(QDStringUtil.isNotNull(skuDetailInfo) && skuDetailInfo.getSkuImgUrl().length > 0 ? skuDetailInfo.getSkuImgUrl()[0] : Constant.defaultPicForOrderListMap.get(Constant.PRODUCT_NO_NG)[0]);//商品图片
                    evaluteSourceBatchSaveBean.setSourceName(skuDetailInfo.getName());//商品名称
                    evaluteSourceBatchSaveBean.setUserId(memberId);//会员ID
                    evaluteSourceBatchSaveBean.setUserName(memberInfo.getName());//会员昵称
                    evaluteSourceBatchSaveBean.setUserAccount(accountId);//账户ID
                    evaluteSourceBatchSaveBean.setUserPhone(memberInfo.getMobile());//手机号
                    evaluteSourceBatchSaveBean.setRegionId(regionId);//城市ID
                    evaluteSourceBatchSaveBean.setRegionName(regionName);//城市名称
                    evaluteSourceBatchSaveBean.setProjectId(projectId);//社区ID
                    evaluteSourceBatchSaveBean.setProjectName(projectName);//社区名称
                    evaluteSourceBatchSaveBean.setProductNo(Constant.PRODUCT_NO_NG);//业态号
                    evaluteSourceBatchSaveBean.setProductValue("乐购");//业态名称
                    evaluteSourceBatchSaveBean.setAnonymousFlag(anonymousFlag);//是否匿名
                    evaluteSourceBatchSaveBean.setScore(evaluateSourcesSaveBean.getScore());//评分
                    evaluteSourceBatchSaveBean.setFlagItems(evaluateSourcesSaveBean.getFlagItems());//评分标签索引
                    evaluteSourceBatchSaveBean.setImages(CollectionUtils.isNotEmpty(evaluateSourcesSaveBean.getEvaluateImgs()) ? JSON.toJSONString(evaluateSourcesSaveBean.getEvaluateImgs()) : "");//用户上传图片
                    evaluteSourceBatchSaveBean.setSourceValue(evaluateSourcesSaveBean.getEvaluateContent());//评价内容
                    evaluteSourceBatchSaveBean.setUserIp(ip);//IP地址
                    evaluteList.add(evaluteSourceBatchSaveBean);
                }
                EvaluateSourceBatchSaveRequest batchSaveRequest = new EvaluateSourceBatchSaveRequest();
                batchSaveRequest.setBeans(evaluteList);
                EvaluateSourceBatchSaveResponse batchSaveResponse = evaluationRemote.saveBatchEvaluateSource(batchSaveRequest);
                checkAndContinue(batchSaveResponse);
                List<EvaluatePromotionCouponBean> promotionCouponList = batchSaveResponse.getList();
                List<EvaluateCouponBean> couponList = transforList(EvaluateCouponBean.class, promotionCouponList);
                data.setIntegralScore(batchSaveResponse.getIntegralScore());
                data.setCouponList(couponList);

                //评价通知订单
                try{
                    MsginfoRequest msginfoRequest = new MsginfoRequest();
                    msginfoRequest.setMaxRetrycount(8);
                    msginfoRequest.setType(20);
                    msginfoRequest.setToclass("EvaluateCallbackJob");
                    msginfoRequest.setTourl(Constant.PINJIA_NOTICE_ORDER);
                    msginfoRequest.setMsgBody(request.getOrderCode());
                    msginfoRequest.setName("商品评价完成后修改订单评价状态");
                    MsginfoResponse msg=integralMessage.sendImessage(msginfoRequest);
                    logger.info("订单评价通知 Imessage Response code:"+msg.getReturnInfo().getCode()+"," +
                            "message:"+msg.getReturnInfo().getMessage());
                }catch(Exception e){
                }
            }
        } catch (ServiceException e) {
            return handleException(SaveEvaluateSourceResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    /**
     * 获取指定订单下的商品列表
     *
     * @param
     * @throws ServiceException
     */
    private List<OrderGood> getOrderGoods( OrderDetailDto orderDetailDto) throws ServiceException {

        List<OrderSub> subOrderList = orderDetailDto.getSubOrderList();
        List<Long> skuIds = Lists.newArrayList();
        for (OrderSub orderSub : subOrderList) {
            skuIds.add(orderSub.getWareSkuId());
        }
        SkuRequest skuRequest = new SkuRequest();
        skuRequest.setSortedSkuIds(skuIds);
        SkuResponse skuResponse = solrSku.querySku(skuRequest);
        List<SkuDetailInfo> skuDetailInfoList = skuResponse.getSkus();
        List<OrderGood> orderGoods = Lists.newArrayList();
        for (SkuDetailInfo skuDetailInfo : skuDetailInfoList) {
            OrderGood orderGood = new OrderGood();
            orderGood.setGoodsDesc(skuDetailInfo.getDescription());
            orderGood.setGoodsName(skuDetailInfo.getSkuName());
            orderGood.setSkuImgUrl(QDStringUtil.isNotNull(skuDetailInfo.getSkuImgUrl()) && skuDetailInfo.getSkuImgUrl().length > 0 ? skuDetailInfo.getSkuImgUrl() : Constant.defaultPicForOrderListMap.get(Constant.PRODUCT_NO_NG));
            orderGood.setWareId(skuDetailInfo.getWareId());
            orderGood.setSkuId(skuDetailInfo.getSkuId());
            orderGood.setMarkingName(skuDetailInfo.getMarkingName());
            orderGood.setMarkingCode(skuDetailInfo.getMarkingCode());
            orderGoods.add(orderGood);
        }

        return orderGoods;
    }


/*    @HTTP(alias = "createCrabsGorder", isRequireAuth = true, isNeadToken = true, isNeadProject = true)
    @ExplainAnnotation(explain = "大闸蟹送友下单")
    public Response<CreateGorderDataResponse> createCrabsGorder(com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request.CreateCrabsGorderRequest request, UserToken userToken) {

        Response<CreateGorderDataResponse> response = new Response<CreateGorderDataResponse>();
        CreateGorderDataResponse data = new CreateGorderDataResponse();
        try {
            CreateCrabsGorderRequest createCrabsGorderRequest = new CreateCrabsGorderRequest();
            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));
            createCrabsGorderRequest.setMid(userToken.getMemberId());
            createCrabsGorderRequest.setProjectId(project.getId());
            createCrabsGorderRequest.setCouponCodeList(request.getCouponCodeList());
            createCrabsGorderRequest.setInvoiceTitle(request.getInvoiceType());
            createCrabsGorderRequest.setInvoiceType(request.getInvoiceTitle());
            createCrabsGorderRequest.setProjectName(project.getName());
            createCrabsGorderRequest.setPublicsId(QDStringUtil.isNull(request.getPublicsId()) ? null : request.getPublicsId());
            createCrabsGorderRequest.setReceiverInfos(request.getReceiverInfos());
            createCrabsGorderRequest.setRegionId(project.getRegionId());
            createCrabsGorderRequest.setRegionName(project.getRegionName());
            createCrabsGorderRequest.setSkuName(request.getSkuName());
            createCrabsGorderRequest.setWareSkuId(request.getSkuId());
            createCrabsGorderRequest.setWareCount(request.getWareCount());
            createCrabsGorderRequest.setSpm(request.getSpm());
            createCrabsGorderRequest.setSourceType(request.getSourceType());
            CreateGorderResponse createGorderResponse = legouRemoteService.createCrabsGorder(createCrabsGorderRequest);
            if (createGorderResponse.getReturnInfo().getCode() == 600) {
                data.setMessage(createGorderResponse.getReturnInfo().getMessage());
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            } else {
                checkAndContinue(createGorderResponse);
                data.setOrderCode(createGorderResponse.getOrderCode());
                data.setShouldPay(createGorderResponse.getTotalRealpay());
            }
            response.setData(data);
            return response;

        } catch (Exception ex) {
            return handleException(CreateGorderDataResponse.class, ex);
        }

    }

    @HTTP(alias = "getCrabsDeliverResultPage", isRequireAuth = true, isNeadToken = true, isNeadProject = true)
    @ExplainAnnotation(explain = "大闸蟹送货记录")
    public Response<GetCrabsDeliverResultPageDataResponse> getCrabsDeliverResultPage(com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request.GetCrabsDeliverResultPageRequest request, UserToken userToken) {

        Response<GetCrabsDeliverResultPageDataResponse> response = new Response<GetCrabsDeliverResultPageDataResponse>();
        GetCrabsDeliverResultPageDataResponse data = new GetCrabsDeliverResultPageDataResponse();
        try {
            GetCrabsDeliverResultPageRequest crabsDeliverResultPageRequest = new GetCrabsDeliverResultPageRequest();
            crabsDeliverResultPageRequest.setMid(userToken.getMemberId());
            crabsDeliverResultPageRequest.setPage(request.getPageNo());
            crabsDeliverResultPageRequest.setSize(request.getPageSize());
            GetCrabsDeliverResultPageResponse crabsDeliverResultPageResponse = legouRemoteService.getCrabsDeliverResultPage(crabsDeliverResultPageRequest);
            checkAndContinue(crabsDeliverResultPageResponse);
            ResultPage<LegouCrabsDeliveryDto> page = crabsDeliverResultPageResponse.getResultPage();
            List<LegouCrabsDeliveryDto> legouCrabsDeliveryList = page.getItems();
            List<LegouCrabsDelivery> list = transforList(LegouCrabsDelivery.class, legouCrabsDeliveryList);
            data.setTotalCount(page.getTotalCount());
            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (ServiceException e) {
            return handleException(GetCrabsDeliverResultPageDataResponse.class, e);
        }
    }*/

    @HTTP(alias = "getInvoicesByMid", isRequireAuth = true, isNeadToken = true, isNeadProject = true)
    @ExplainAnnotation(explain = "获取用户发票信息列表")
    public Response<GetInvoicesByMidResponseData> getInvoicesByMid(GetInvoicesByMidRequest request, UserToken userToken) {

        Response<GetInvoicesByMidResponseData> response = new Response<GetInvoicesByMidResponseData>();
        GetInvoicesByMidResponseData data = new GetInvoicesByMidResponseData();
        List<MemberInvoiceVo>   invoiceVoList = iMemberInvoiceRpcService.findMemberInvoiceByMemberId(userToken.getMemberId());
        List<InvoiceBean> invoiceBeanList = Lists.newArrayList();
        if ( CollectionUtils.isNotEmpty(invoiceVoList)) {
            for (MemberInvoiceVo memberInvoiceVo : invoiceVoList) {
                if (memberInvoiceVo.getInvoiceType().intValue() != request.getInvoiceType().intValue()) continue;
                invoiceBeanList.add(transfor(InvoiceBean.class,memberInvoiceVo));
            }
        }
        data.setInvoiceBeanList(invoiceBeanList);
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


}
