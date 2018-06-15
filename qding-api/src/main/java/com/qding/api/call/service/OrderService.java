package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.LogisticsInfo;
import com.qding.api.constant.Constant;
import com.qding.api.util.DateUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.JsonUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetOrderFreightRequest;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.legou.struct.response.GetOrderFreightResponse;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsLogDto;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsOrderDeliveryBean;
import com.qding.logistics.platform.client.remote.struct.request.GetOrderLogisticsDataRequest;
import com.qding.logistics.platform.client.remote.struct.response.GetExpressResponse;
import com.qding.logistics.platform.client.remote.struct.response.GetOrderLogisticsDataResponse;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.oder.dto.OrderPackageinfoDto;
import com.qding.order.domain.OrderSub;
import com.qding.order.service.IRemoteOrderSavingOptimizerService;
import com.qding.order.struct.response.GetOrderDetailResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by qd on 2017/9/6.
 */
public class OrderService extends Callable {

    @Autowired
    private IRemoteOrderSavingOptimizerService orderSavingService;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private ISolrSkuService skuService;


    @Autowired
    private ILegouRemoteService legouRemoteService;


    private static final Logger logger = Logger.getLogger(OrderService.class);

    /**
     * 通过订单ID获取平台订单详情
     * @param orderCode
     * @return
     */
    public OrderDetailDto getOrderDetailByCode(String orderCode) throws ServiceException {

        GetOrderDetailResponse response = orderSavingService.getOrderDetailLimitData(orderCode);
        checkAndContinue(response);
        OrderDetailDto dto  = response.getOrderDetailDto();
        return dto;
    }


    /**
     * 获取物流信息
     * @param orderCode
     * @param getOrderResponse
     * @return
     */
    public List<LogisticsInfo> getLogisticsInfosForPackage(String orderCode, GetOrderDetailByCodeResponse getOrderResponse) {

        List<LogisticsInfo> logisticsInfoList = Lists.newArrayList();
        //查找该包裹中的商品
        List<OrderSub> subOrderDtoList = getOrderResponse.getLegouOrderDetailDto().getSubOrderList();
        Map<String, OrderSub> subMap = new HashMap<>();
        for (OrderSub sub : subOrderDtoList) {
            subMap.put(sub.getCode(), sub);
        }
        if (subMap.size() == 0) return Lists.newArrayList();
        Map<String,List<LogisticsOrderDeliveryBean>> keys = new HashMap<>();
        List<LogisticsOrderDeliveryBean> logisticsDataList = getOrderLogisticsData(orderCode);
        if (CollectionUtils.isNotEmpty(logisticsDataList)) {
            for (LogisticsOrderDeliveryBean bean : logisticsDataList) {
                String key = "";
                if (bean.getDeliveryType() == null) {
                    continue;
                } else if (bean.getDeliveryType() == 0 && bean.getLogisticsCode() != null) {
                    key = String.format("%s:%s:%s", bean.getDeliveryType(), bean.getLogisticsCode(), bean.getLogisticsCompany());
                } else if ((bean.getDeliveryType() == 1 && bean.getDeliveryName() != null)) {
                    key = String.format("%s:%s:%s", bean.getDeliveryType(), bean.getDeliveryMobile(), bean.getDeliveryName());
                } else {
                    continue;
                }
                List<LogisticsOrderDeliveryBean>  list = null;
                if (keys.containsKey(key)){
                    list =  keys.get(key);
                } else {
                    list = Lists.newArrayList();
                }
                list.add(bean);
                keys.put(key, list);
            }
        }

        int i = 1;

        for (Map.Entry<String, List<LogisticsOrderDeliveryBean>> entity : keys.entrySet()) {

            List<LogisticsOrderDeliveryBean> logisticsOrderDeliveryList  = entity.getValue();
            LogisticsInfo logisticsInfo = new LogisticsInfo();
            List<String> imgList = Lists.newArrayList();
            int flag =0;
            List<String> subOrderList = Lists.newArrayList();
            int logisticsBuyCount = 0;
            for (LogisticsOrderDeliveryBean bean : logisticsOrderDeliveryList) {
                OrderPackageinfoDto dto = new OrderPackageinfoDto();
                BeanUtils.copyProperties(bean, dto);
                dto.setDeliverType(bean.getDeliveryType());
                OrderSub sub = subMap.get(bean.getOrderSubCode());
                List<Long> wareSkuIds = dto.getWareSkuIds() != null ? dto.getWareSkuIds() : new ArrayList<Long>();
                Integer goodsCount = dto.getGoodsBuyCount() != null ? dto.getGoodsBuyCount() : 0;

                wareSkuIds.add(sub.getWareSkuId());
                goodsCount += sub.getWareCount();
                logisticsBuyCount+= sub.getWareCount();

                if (dto.getDeliverType() != null) {
                    sub.setLogisticsCode(bean.getDeliveryType() == 0 ? dto.getLogisticsCode() : bean.getDeliveryMobile());
                    sub.setLogisticsName(bean.getDeliveryType() == 0 ? dto.getLogisticsCompany() : bean.getDeliveryName());
                }

                dto.setMarkingCode(sub.getMarkingCode());
                dto.setWareSkuIds(wareSkuIds);
                dto.setGoodsBuyCount(goodsCount);

                LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
                legouSkuRequest.setSortedSkuIds(wareSkuIds);
                LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
                if (HttpStatus.OK.getStatusCode() == skuResponse.getReturnInfo().getCode()) {
                    List<LegouSkuDetailInfo> legouSkuDetailInfoList = skuResponse.getSkus();
                    if (CollectionUtils.isNotEmpty(legouSkuDetailInfoList)) {
                        for (LegouSkuDetailInfo legouSkuDetailInfo : legouSkuDetailInfoList) {
                            String[] skuImgArray = legouSkuDetailInfo.getSkuImgUrl();
                            if (skuImgArray.length > 0) {
                                imgList.add(skuImgArray[0]);
                            }
                        }
                    }
                }
                if ( flag == 0) {
                    StringBuffer infoDescF = new StringBuffer(QDStringUtil.isNotEmpty(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getRegionName()) ? getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getRegionName() : "");
                    infoDescF.append(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectName());

                    GetExpressResponse expressResponse = logisticsPlatformOrder.getExpress(sub.getCode());
                    if (HttpStatus.OK.getStatusCode() == expressResponse.getReturnInfo().getCode() &&
                            CollectionUtils.isNotEmpty(expressResponse.getLogs())) {

                        List<LogisticsLogDto> logisticsLogList = expressResponse.getLogs();
                        LogisticsLogDto logisticsLog = logisticsLogList.get(0);
                        logisticsInfo.setLastLogisticsInfo(logisticsLog.getContext());
                        if (expressResponse.getDeliveryType().intValue() == 0) { //快递配送
                            logisticsInfo.setCompanyName(expressResponse.getDeliverExpressInfo().getLogisticsCompany());
                            logisticsInfo.setCompanyCode(expressResponse.getDeliverExpressInfo().getLogisticsCode());

                        } else { //自行配送
                            logisticsInfo.setCompanyName(expressResponse.getDeliverOwnerInfo().getDeliveryName());
                            logisticsInfo.setCompanyCode(expressResponse.getDeliverOwnerInfo().getDeliveryMobile());
                        }
                    } else {
                        logisticsInfo.setLastLogisticsInfo(String.format(Constant.markingDesc, infoDescF.toString()) +
                                (QDStringUtil.isNotNull(dto.getSettlePeriodEndTime()) ? "，预计于" + DateUtil.Date2Str(dto.getSettlePeriodEndTime()) + "发货" : "，如有疑问请联系物业"));
                    }
                }
                subOrderList.add(sub.getCode());
                flag++;
            }
            logisticsInfo.setGoodsBuyCount(logisticsBuyCount);
            logisticsInfo.setImgList(imgList);
            logisticsInfo.setSubOrderCodes(subOrderList);
            logisticsInfoList.add(logisticsInfo);
        }

        return logisticsInfoList;
    }

    public  List<LogisticsOrderDeliveryBean> getOrderLogisticsData(String orderCode) {

        try {
            GetOrderLogisticsDataRequest request = new GetOrderLogisticsDataRequest();
            request.setOrderCode(orderCode);
            GetOrderLogisticsDataResponse response = logisticsPlatformOrder.getOrderLogisticsData(request);
            logger.info("[getOrderLogisticsData] response="+ JsonUtil.Java2Json(response));
            if (response != null && response.getList() != null) {
                return response.getList();
            }
        }catch(Exception e) {
            logger.error("class:OrderService ,method: getOrderLogisticsData error :" , e);
        }

        return Collections.emptyList();
    }


    /**
     * 确认订单时获取运费
     * @return
     */
    public String getOrderFreight(List<GetOrderFreightRequest.OrderSkuInfo> orderSkuInfoList,String orderPrice){

        String orderFreight = "0.00";
        try {
            GetOrderFreightRequest orderFreightRequest = new GetOrderFreightRequest();
            orderFreightRequest.setSkuInfoList(orderSkuInfoList);
            orderFreightRequest.setOrderPrice(orderPrice);
            GetOrderFreightResponse orderFreightResponse = legouRemoteService.getOrderFreight(orderFreightRequest);
            checkAndContinue(orderFreightResponse);
            orderFreight = orderFreightResponse.getTotalFreight();
        } catch (Exception ex) {
            logger.error("确认订单接口获取取费失败:",ex);
        }
        return orderFreight;
    }

}
