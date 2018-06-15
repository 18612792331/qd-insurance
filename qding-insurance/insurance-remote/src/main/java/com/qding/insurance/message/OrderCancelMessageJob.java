package com.qding.insurance.message;

import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.SpringContextUtils;
import com.qding.imessage.client.BaseIMessageJob;
import com.qding.imessage.common.struct.QdImessageException;
import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.service.IInsuranceOrderService;
import com.qding.insurance.util.Constant;

/**
 * 平台订单取消 消息体： { "comment": "", "isReturnIntegral": 1, "optName": "system",
 * "orderCode": "NG17800011806081740208532", "orderStatus": "201" }
 */
public class OrderCancelMessageJob extends BaseIMessageJob {

    private static final Logger logger = Logger.getLogger(OrderCancelMessageJob.class);

    private IInsuranceOrderService insuranceOrderService;

    public OrderCancelMessageJob() {
        insuranceOrderService = (IInsuranceOrderService) SpringContextUtils.getBean("insuranceOrderService");
    }

    @Override
    public String run() throws QdImessageException {
        logger.info("OrderCancelMessageJob get message, params : " + this.getParams());

        // 根据平台订单号查询保险订单
        String orderCode = JSONObject.parseObject(this.getParams()).getString("orderCode");
        InsuranceOrder insuranceOrder = insuranceOrderService.getByOrderNo(orderCode);
        if(insuranceOrder == null){
            throw new QdImessageException(HttpStatus.BAD_REQUEST.getStatusCode(), "订单["+orderCode+"]不存在");
        }
        if(insuranceOrder.getStatus() != Constant.ORDER_STATUS_UNAPAID){
            throw new QdImessageException(HttpStatus.BAD_REQUEST.getStatusCode(), "订单["+orderCode+"]状态["+insuranceOrder.getStatus()+"]不可取消");
        }
        // 保险订单变更订单状态为已取消
        Date now = new Date();
        insuranceOrder.setStatus(Constant.ORDER_STATUS_CANCELED);
        insuranceOrder.setCancelAt(now);
        insuranceOrder.setCancelReason("支付超时");
        insuranceOrderService.updateSelected(insuranceOrder);

        return null;
    }
}
