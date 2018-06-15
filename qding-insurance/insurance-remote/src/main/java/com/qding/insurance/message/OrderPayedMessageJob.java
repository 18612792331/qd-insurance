package com.qding.insurance.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.SpringContextUtils;
import com.qding.imessage.client.BaseIMessageJob;
import com.qding.imessage.common.struct.QdImessageException;
import com.qding.insurance.domain.GuaranteeObject;
import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.domain.InsurancePolicy;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.domain.PolicyGuaranteeObject;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.picc.bean.Applicant;
import com.qding.insurance.picc.bean.Insured;
import com.qding.insurance.picc.bean.InsuredPlan;
import com.qding.insurance.picc.bean.Insureds;
import com.qding.insurance.picc.bean.PolicyInfo;
import com.qding.insurance.picc.bean.PolicyInfoReturn;
import com.qding.insurance.picc.bean.PolicyInfoReturns;
import com.qding.insurance.picc.bean.PolicyInfos;
import com.qding.insurance.picc.bean.ReturnInfo;
import com.qding.insurance.picc.dto.PICCInsureRequestParamDto;
import com.qding.insurance.picc.dto.PICCInsureResponseParamDto;
import com.qding.insurance.picc.enums.EnumInsureRespCodeType;
import com.qding.insurance.service.EmailService;
import com.qding.insurance.service.IGuaranteeService;
import com.qding.insurance.service.IInsuranceOrderService;
import com.qding.insurance.service.IInsurancePolicyService;
import com.qding.insurance.service.IInsuranceSkuService;
import com.qding.insurance.service.PICCService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.GuaranteeItemVo;
import com.qding.insurance.vo.OrderPayMessage;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.FinishOrderResponse;

/**
 * 平台订单支付完成消息 消息体： { "isSplit": 0, "mid": "2c9180895a4a4a2f015a4a4e03780008",
 * "orderCode": "KD04300011806071843449122", "payAt": 1528368254672, "payMid":
 * "2c9180895a4a4a2f015a4a4e03780008", "paySourceType": 0, "payTypeList": [{
 * "orderPayType": 41, "payAmount": "0.01", "payType": "wx_gzh" }], "payeeId":
 * 0, "productNo": "KD", "projectId": 31602241129244, "realpay": "0.01",
 * "tradeStatus": "4", "tradeType": "41", "userName": "千丁用户", "userPhone":
 * "14444444443" }
 */
public class OrderPayedMessageJob extends BaseIMessageJob {

    private static final Logger logger = Logger.getLogger(OrderPayedMessageJob.class);

    private IInsuranceOrderService insuranceOrderService;

    private IInsurancePolicyService insurancePolicyService;

    private IGuaranteeService guaranteeService;
    
    private IInsuranceSkuService skuService;
    
    private IRemoteOrderService orderRemote;

    private EmailService emailService;
    
    private PICCService piccService;

    public OrderPayedMessageJob() {
        insuranceOrderService = (IInsuranceOrderService) SpringContextUtils.getBean("insuranceOrderService");
        insurancePolicyService = (IInsurancePolicyService) SpringContextUtils.getBean("insurancePolicyService");
        guaranteeService = (IGuaranteeService) SpringContextUtils.getBean("guaranteeService");
        skuService = (IInsuranceSkuService) SpringContextUtils.getBean("insuranceSkuService");
        orderRemote = (IRemoteOrderService) SpringContextUtils.getBean("orderRemote");
        emailService = (EmailService) SpringContextUtils.getBean("emailService");
        piccService = (PICCService) SpringContextUtils.getBean("piccService");
    }

    @Override
    public String run() throws QdImessageException {
        logger.info("OrderPayedMessageJob get message, params : " + this.getParams());

        OrderPayMessage message = JSONObject.parseObject(this.getParams(), OrderPayMessage.class);
        String orderCode = message.getOrderCode();

        // 根据平台订单号查询保险订单
        InsuranceOrder insuranceOrder = insuranceOrderService.getByOrderNo(orderCode);
        if (insuranceOrder == null) {
            throw new QdImessageException(HttpStatus.BAD_REQUEST.getStatusCode(), "订单[" + orderCode + "]不存在");
        }
        if (insuranceOrder.getStatus() != Constant.ORDER_STATUS_UNAPAID) {
            throw new QdImessageException(HttpStatus.BAD_REQUEST.getStatusCode(),"订单[" + orderCode + "]状态[" + insuranceOrder.getStatus() + "]不可支付");
        }

        // 保险订单变更订单状态、支付状态 为已支付
        insuranceOrderPayed(message, insuranceOrder);

        // 根据保险订单商品信息，创建保险单证（投保中）及权益
        InsurancePolicy policy = createInsurancePolicy(message, insuranceOrder);

        // PICC投保
		PICCInsureResponseParamDto result = insurePICC(insuranceOrder, policy);
		String respCode = null;
		String respMsg = null;
		String piccNo = null;
		String piccUrl = null;
		boolean piccResult = false;
		if (result == null) {
			logger.info("result from PICC is null");
		}else {
			respCode = result.getRespCode();
			respMsg = result.getRespMsg();
			logger.info("调用人保投保接口返回的respCode:" + respCode + ", respMsg:" + respMsg);
			if (EnumInsureRespCodeType.SUCCESS.getCode().equals(respCode)) {
				piccResult = true;
				ReturnInfo returnInfo = result.getReturnInfo();
				if (returnInfo != null) {
					PolicyInfoReturns policyInfoReturns = returnInfo.getPolicyInfoReturns();
					if (policyInfoReturns != null) {
						List<PolicyInfoReturn> pireList = policyInfoReturns.getPireList();
						if (CollectionUtils.isNotEmpty(pireList)) {
							piccNo = pireList.get(0).getPolicyNo();
							piccUrl = pireList.get(0).getDownloadUrl();
						}
					}
				}
			}
		}
	
        // PICC投保成功，变更保险订单状态为已完成，单证状态为未生效，通知平台订单完成（RPC）
        if(piccResult){
            insuranceOrderFinished(insuranceOrder);
            policyStausChange(policy,Constant.POLICY_STATUS_UNENFORCED,piccNo,piccUrl);
            platOrderFinish(orderCode);
        }else{
            // PICC投保失败，变更保单状态为投保失败，发送提醒邮件(含失败原因)
            policyStausChange(policy,Constant.POLICY_STATUS_FAILEDINSURE,null,null);
            emailService.sendEmail("邮件标题", "邮件文本(含失败原因)");
        }

        return null;
    }

	private PICCInsureResponseParamDto insurePICC(
			InsuranceOrder insuranceOrder, InsurancePolicy policy) {
		//拼装参数
		PICCInsureRequestParamDto dto = new PICCInsureRequestParamDto();
		PolicyInfos policyInfos = new PolicyInfos();
		ArrayList<PolicyInfo> policyInfoList = new ArrayList<PolicyInfo>();
		
		//设置保单信息
		PolicyInfo policyInfo = new PolicyInfo();
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		policyInfo.setOperateTimes(sdfTime.format(policy.getInsureAt()));
		policyInfo.setStartDate(sdfDate.format(policy.getActAt()));
		policyInfo.setEndDate(sdfDate.format(policy.getEndAt()));
		policyInfo.setStartHour("0");
		policyInfo.setEndHour("24");
		policyInfo.setSumPremium(insuranceOrder.getPaidMoney());
		policyInfo.setHouseAddress(policy.getRoomAddress());
		
		//设置投保方案
		InsuredPlan insuredPlan = new InsuredPlan();
		InsuranceSku sku = skuService.getById(insuranceOrder.getSkuId());
		if (sku != null) {
			logger.info("skuId:" + sku.getId() + ", 方案代码:" + sku.getPiccCode());
			insuredPlan.setRationType(sku.getPiccCode());
		}
		policyInfo.setInsuredPlan(insuredPlan);
		
		//设置投保人信息
		Applicant applicant = new Applicant();
		applicant.setAppliName(policy.getMemberName());
		applicant.setAppliIdType("01");
		applicant.setAppliIdNo(policy.getMemberIdcard());
		applicant.setAppliIdMobile(policy.getMemberPhone());
		applicant.setAppliIdEmail(policy.getMemberEmail());
		applicant.setAppliIdentity(policy.getInsurantRelation());
		policyInfo.setApplicant(applicant);
		
		//设置被投保人信息
		Insureds insureds = new Insureds();
		ArrayList<Insured> insuredList = new ArrayList<Insured>();
		Insured insured = new Insured();
		insured.setInsuredName(policy.getInsurantName());
		insured.setInsuredIdType("01");
		insured.setInsuredIdNo(policy.getInsurantIdcard());
		insured.setInsuredEmail(policy.getMemberEmail());
		insuredList.add(insured);
		insureds.setInsuredList(insuredList);
		policyInfo.setInsureds(insureds);
		policyInfoList.add(policyInfo);
		policyInfos.setPolicyInfoList(policyInfoList);
		dto.setPolicyInfos(policyInfos);
		
		//调用投保接口
		PICCInsureResponseParamDto result = piccService.insureService(dto);
		return result;
	}
    
    /**
     * 通知平台订单完成
     */
    private void platOrderFinish(String orderCode){
        FinishOrderResponse response = orderRemote.finishOrderByOrderCode(orderCode);
        if(!response.isAllCompleted()){
            logger.error("platOrder["+orderCode+"] finish error, plat message :"+response.getReturnInfo().getMessage());
        }
    }
    
    /**
     * 投保后，更新单证信息
     */
    private void policyStausChange(InsurancePolicy policy,Integer status, String piccNo, String piccUrl){
        policy.setPiccNo(piccNo);
        policy.setPolicyUrl(piccUrl);
        policy.setStatus(status);
        insurancePolicyService.updatePolicy(policy);
        
        logger.info("["+policy.getOrderNo()+"]policy status update to "+status);
    }

    /**
     * 根据订单商品，创建保单及保单权益
     */
    private InsurancePolicy createInsurancePolicy(OrderPayMessage message, InsuranceOrder insuranceOrder) {
        // 单证信息
        InsurancePolicy policy = new InsurancePolicy();
        policy.setId(Constant.ID_GENERATOR.generate());
        policy.setOrderNo(insuranceOrder.getOrderNo());
        policy.setMemberId(insuranceOrder.getMemberId());
        policy.setMemberPhone(insuranceOrder.getMemberPhone());
        policy.setMemberName(insuranceOrder.getMemberName());
        policy.setMemberIdcard(insuranceOrder.getMemberIdcard());
        policy.setInsurantRelation(insuranceOrder.getInsurantRelation());
        policy.setInsurantPhone(insuranceOrder.getInsurantPhone());
        policy.setInsurantName(insuranceOrder.getInsurantName());
        policy.setInsurantIdcard(insuranceOrder.getInsurantIdcard());
        policy.setBenefitPhone(insuranceOrder.getBenefitPhone());
        policy.setBenefitName(insuranceOrder.getBenefitName());
        policy.setBenefitIdcard(insuranceOrder.getBenefitIdcard());
        policy.setProjectId(insuranceOrder.getProjectId());
        policy.setProjectName(insuranceOrder.getProjectName());
        policy.setRoomId(insuranceOrder.getRoomId());
        policy.setRoomAddress(insuranceOrder.getRoomAddress());
        policy.setStatus(Constant.POLICY_STATUS_TOINSURE);
        policy.setInsureAt(new Date());
        policy.setActAt(insuranceOrder.getPolicyActAt());
        policy.setExpireAt(policyExpireDate(insuranceOrder.getPolicyActAt(), insuranceOrder.getSkuId()));
        policy.setMemberEmail(insuranceOrder.getMemberEmail());
        insurancePolicyService.addPolicy(policy);
        
        logger.info("["+insuranceOrder.getOrderNo()+"]create policy OK");

        List<PolicyGuaranteeItem> toAddItem = new ArrayList<PolicyGuaranteeItem>();
        List<PolicyGuaranteeObject> toAddObject = new ArrayList<PolicyGuaranteeObject>();
        List<PolicyGuaranteePlan> toAddPlan = new ArrayList<PolicyGuaranteePlan>();
        PolicyGuaranteeItem policyItem = null;
        PolicyGuaranteeObject policyObject = null;
        PolicyGuaranteePlan policyPlan = null;

        // 保障内容
        List<GuaranteeItemVo> guaranteeItemList = guaranteeService.getWareGuarantee(insuranceOrder.getWareId());
        if (CollectionUtils.isNotEmpty(guaranteeItemList)) {
            for (GuaranteeItemVo itemVo : guaranteeItemList) {
                policyItem = new PolicyGuaranteeItem();
                policyItem.setId(Constant.ID_GENERATOR.generate());
                policyItem.setPolicyId(policy.getId());
                policyItem.setItemTitle(itemVo.getItemTitle());
                policyItem.setItemType(itemVo.getItemType());
                policyItem.setCompensateType(itemVo.getCompensateType());
                toAddItem.add(policyItem);

                // 保障对象
                if (CollectionUtils.isNotEmpty(itemVo.getGuaranteeObjectList())) {
                    for (GuaranteeObject guaranteeObject : itemVo.getGuaranteeObjectList()) {
                        policyObject = new PolicyGuaranteeObject();
                        policyObject.setId(Constant.ID_GENERATOR.generate());
                        policyObject.setPolicyId(policy.getId());
                        policyObject.setItemId(policyItem.getId());
                        policyObject.setObjectId(guaranteeObject.getObjectId());
                        toAddObject.add(policyObject);
                    }
                }

                // 保障权益
                List<GuaranteePlan> guaranteePlanList = guaranteeService.getWarePlan(insuranceOrder.getWareId(),insuranceOrder.getSkuId());
                if (CollectionUtils.isNotEmpty(guaranteePlanList)) {
                    for (GuaranteePlan guaranteePlan : guaranteePlanList) {
                        policyPlan = new PolicyGuaranteePlan();
                        policyPlan.setId(Constant.ID_GENERATOR.generate());
                        policyPlan.setPolicyId(policy.getId());
                        policyPlan.setGuaranteeItemId(policyItem.getId());
                        policyPlan.setRightValue(guaranteePlan.getRightValue());
                        policyPlan.setLockValue("0");
                        policyPlan.setBalanceValue(guaranteePlan.getRightValue());
                        policyPlan.setPaidValue("0");
                        toAddPlan.add(policyPlan);
                    }
                }
            }
            
            guaranteeService.batchAddGuarantee(toAddItem, toAddObject, toAddPlan);
            
            logger.info("["+insuranceOrder.getOrderNo()+"]copy guarantee OK");
        }

        return policy;
    }
    
    /**
     * 计算保险到期时间
     */
    private Date policyExpireDate(Date actDate, String skuId){
        
        InsuranceSku sku = skuService.getById(skuId);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(actDate);
        
        if(Constant.TIME_TYPE_ONE_YEAR.equals(sku.getTimeType())){
            cal.add(Calendar.YEAR, 1);
        }else if(Constant.TIME_TYPE_TWO_YEAR.equals(sku.getTimeType())){
            cal.add(Calendar.YEAR, 2);
        }else if(Constant.TIME_TYPE_THREE_YEAR.equals(sku.getTimeType())){
            cal.add(Calendar.YEAR, 3);
        } 
        
        return cal.getTime();
    }

    /**
     * 保险订单已完成
     */
    private InsuranceOrder insuranceOrderFinished(InsuranceOrder insuranceOrder) {
        
        insuranceOrder.setStatus(Constant.ORDER_STATUS_FINISHED);
        insuranceOrder.setFinishAt(new Date());
        insuranceOrderService.updateSelected(insuranceOrder);
        
        logger.info("["+insuranceOrder.getOrderNo()+"]insurance order status update to FINISHED");
        
        return insuranceOrder;
    }
    /**
     * 保险订单已支付
     */
    private InsuranceOrder insuranceOrderPayed(OrderPayMessage message, InsuranceOrder insuranceOrder) {

        insuranceOrder.setStatus(Constant.ORDER_STATUS_PAID);
        insuranceOrder.setPayStatus(Constant.ORDER_PAY_STATUS_PAID);
        insuranceOrder.setPaidAt(new Date(Long.parseLong(message.getPayAt())));
        insuranceOrder.setPaidMoney(message.getRealpay());
        insuranceOrder.setPayType(message.getTradeType());
        insuranceOrderService.updateSelected(insuranceOrder);

        logger.info("["+insuranceOrder.getOrderNo()+"]insurance order status update to PAID");
        
        return insuranceOrder;
    }
}
