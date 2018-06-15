package com.qding.insurance.rpc.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.insurance.domain.CompensateRecord;
import com.qding.insurance.domain.InsurancePolicy;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.rpc.IInsurancePolicyRpc;
import com.qding.insurance.rpc.request.FinishCompensateRequest;
import com.qding.insurance.rpc.request.IsInsuranceProductRequest;
import com.qding.insurance.rpc.request.ProductCompensateRequest;
import com.qding.insurance.rpc.request.UserCompensateRequest;
import com.qding.insurance.rpc.response.IsInsuranceProductResponse;
import com.qding.insurance.rpc.response.ProductCompensateResponse;
import com.qding.insurance.rpc.response.RoomInsureStatusInfoResponse;
import com.qding.insurance.rpc.response.UserPolicyListResponse;
import com.qding.insurance.service.ICompensateRecordService;
import com.qding.insurance.service.IInsurancePolicyService;
import com.qding.insurance.service.IPolicyGuaranteeItemService;
import com.qding.insurance.service.IPolicyGuaranteePlanService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.InsurancePolicyDetailVo;
import com.qding.insurance.vo.InsurancePolicyInfo;
import com.qding.insurance.vo.RoomInsureStatusInfo;

@Service("policyRpc")
public class InsurancePolicyRpcImpl implements IInsurancePolicyRpc {
    
	private static final Logger logger = Logger.getLogger(InsurancePolicyRpcImpl.class);
	
	@Autowired
	private IInsurancePolicyService insurancePolicyService;
	@Autowired
	private ICompensateRecordService compensateRecordService;
	@Autowired
	private IPolicyGuaranteePlanService policyGuaranteePlanService;
	@Autowired
	private IPolicyGuaranteeItemService policyGuaranteeItemService;
	
	// 保险单位
	@Value("#{configproperties_disconf[insurance_provider_name]}")
	private String insuranceProviderName;
	
	// 限制金额，超过此金额需PICC授权
	@Value("#{configproperties_disconf[need_auth_money_limit]}")
	private String limitMoneyStr;
	
	@Override
    public BaseResponse cancelCompensate(FinishCompensateRequest request) {
	    
	    BaseResponse response = new BaseResponse();
        ReturnInfo returnInfo = new ReturnInfo();
        response.setReturnInfo(returnInfo);
	    
        // 根据订单号&房屋ID确定理赔记录
        CompensateRecord compensate = compensateRecordService.getByOrderCode(request.getRoomID(), request.getOrderCode());
        if(compensate == null){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("未找到订单对应的理赔记录");
            return response;
        }
        if(compensate.getStatus()!=Constant.COMPENSATERECORD_STATUS_LOCKED && compensate.getStatus()!=Constant.COMPENSATERECORD_STATUS_UNAUTHOR){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("理赔记录当前状态不可撤销");
            return response;
        }
        
        
        try {
            // 解锁已锁定权益
            policyGuaranteePlanService.unLockRight(compensate.getPolicyId(), compensate.getGuaranteeItemId(), compensate.getPaidMoney());
            // 理赔记录取消
            compensate.setStatus(Constant.COMPENSATERECORD_STATUS_CANCELED);
            compensate.setFinishAt(new Date());
            compensate.setFinishBy("system");
            compensateRecordService.updateCompensate(compensate);
            
            returnInfo.setCode(HttpStatus.OK.getStatusCode());
            
        } catch (Exception e) {
            logger.error("cancel compensate error :", e);
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage(e.getMessage()); 
        }
        return response;
    }

    @Override
    public BaseResponse finishCompensate(FinishCompensateRequest request) {
        
        BaseResponse response = new BaseResponse();
        ReturnInfo returnInfo = new ReturnInfo();
        response.setReturnInfo(returnInfo);
        
        // 根据订单号&房屋ID确定理赔记录
        CompensateRecord compensate = compensateRecordService.getByOrderCode(request.getRoomID(), request.getOrderCode());
        if(compensate == null){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("未找到订单对应的理赔记录");
            return response;
        }
        if(compensate.getStatus()!=Constant.COMPENSATERECORD_STATUS_LOCKED){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("理赔记录当前状态不可完成");
            return response;
        }
        
        try {
            // 完成已锁定权益
            policyGuaranteePlanService.finishLockRight(compensate.getPolicyId(), compensate.getGuaranteeItemId(), compensate.getPaidMoney());
            // 理赔记录完成
            compensate.setStatus(Constant.COMPENSATERECORD_STATUS_FINISHED);
            compensate.setFinishAt(new Date());
            compensate.setFinishBy("system");
            compensateRecordService.updateCompensate(compensate);
            
            returnInfo.setCode(HttpStatus.OK.getStatusCode());
            
        } catch (Exception e) {
            logger.error("finish compensate error :", e);
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage(e.getMessage()); 
        }
        return response;
    }

	/**
     * 商品是否在保
     */
	@Override
    public IsInsuranceProductResponse isInsuranceProduct(IsInsuranceProductRequest request) {
	    IsInsuranceProductResponse response = new IsInsuranceProductResponse();
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(HttpStatus.OK.getStatusCode());
        response.setReturnInfo(returnInfo);
        
        // 保单是否有效
        InsurancePolicy policy = insurancePolicyService.getByRoomID(request.getRoomID());
        if(policy == null){
            response.setInsuranceProduct(false);
            response.setMessage("房屋未投保");
            return response;
        }
        
        if(policy.getStatus()!=Constant.POLICY_STATUS_ENFORCED){
            response.setInsuranceProduct(false);
            response.setMessage("保单当前状态不可赔付");
            return response;
        }
        
        
        // 商品是否属于保障范围
        PolicyGuaranteeItem policyGuaranteeItem = policyGuaranteeItemService.getByPolicyAndObject(policy.getId(),request.getProductId());
        if(policyGuaranteeItem == null){
            response.setInsuranceProduct(false);
            response.setMessage("商品不在保单保障范围内");
            return response;
        }
        
        response.setInsuranceProduct(true);
	    
        return response;
    }
	
	@Override
    public BaseResponse userCompensate(UserCompensateRequest request) {
	    
	    BaseResponse response = new BaseResponse();
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(HttpStatus.OK.getStatusCode());
        response.setReturnInfo(returnInfo);
        
        // 保单是否有效
        InsurancePolicy policy = insurancePolicyService.getResultById(request.getPolicyId());
        if(policy == null){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("保单不存在");
            return response;
        }
        if(policy.getStatus()!=Constant.POLICY_STATUS_ENFORCED){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("保单当前状态不可赔付");
            return response;
        }
        
        // 保障内容
        PolicyGuaranteeItem policyGuaranteeItem = policyGuaranteeItemService.getResultByID(request.getItemId());
        if(policyGuaranteeItem == null){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("保障内容不存在");
            return response;
        }
        
        // 保障权益是否存在
        PolicyGuaranteePlan policyGuaranteePlan = policyGuaranteePlanService.getpolicyGuaranteePlanByPolicyIDAndItemID(policy.getId(),policyGuaranteeItem.getId());
        if(policyGuaranteePlan == null){
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage("保障权益不存在");
            return response;
        }
        
        // 权益值是否足够 ,按次数，剩余次数需大于等于1； 按金额，剩余金额需大于请求金额
        BigDecimal requestMoney = new BigDecimal(request.getEstimateMoney());
        
        if(!Constant.GUARANTEE_PLAN_UNLIMIT.equals(policyGuaranteePlan.getRightValue())){
            
            if((policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME && Integer.parseInt(policyGuaranteePlan.getBalanceValue()) < 1)
                    || (policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY && new BigDecimal(policyGuaranteePlan.getBalanceValue()).compareTo(requestMoney) == -1)){
                returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                returnInfo.setMessage("保障额度不足");
                return response;
            }
        }
        
        /***
         * 所有校验通过，开始理赔
         */
        
        // 创建理赔记录
        Date now = new Date();
        CompensateRecord compensate = new CompensateRecord();
        compensate.setId(Constant.ID_GENERATOR.generate());
        compensate.setPolicyId(policy.getId());
        compensate.setPiccNo(policy.getPiccNo());
        compensate.setGuaranteeItemId(policyGuaranteeItem.getId());
        compensate.setRoomId(policy.getRoomId());
        compensate.setRoomAddress(policy.getRoomAddress());
        compensate.setHappenAt(request.getHappenAt());
        compensate.setReportAt(now);
        compensate.setContactName(request.getContactName());
        compensate.setContactPhone(request.getContactPhone());
        compensate.setEstimateMoney(request.getEstimateMoney());
        compensate.setAccidentMemo(request.getAccidentMemo());
        compensate.setAccidentImgs("");
        compensate.setDataStatus(Constant.COMPENSATERECORD_DATASTATUS_UNSYNC);
        compensate.setCreateAt(now);
        compensate.setCreateBy(request.getMid());
        
        // 理赔金额，按次数扣减1次， 按金额扣减请求金额
        String payMoney = null;
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME){
            payMoney = "1";
        }
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY){
            payMoney = request.getEstimateMoney();
        }
        compensate.setPaidMoney(payMoney);
        
        // 用户提交理赔场景为先通过PICC理赔，后提交理赔记录，所以无需考虑授权金额问题
        // 所以理赔状态固定为 已锁定；理赔性质固定为 外部授权
        compensate.setStatus(Constant.COMPENSATERECORD_STATUS_LOCKED);
        compensate.setPaidType(Constant.COMPENSATE_PAID_TYPE_OUT);
     
        try {
            // 锁定权益
            policyGuaranteePlanService.lockRight(policy.getId(), policyGuaranteeItem.getId(), payMoney);
            
            // 理赔记录入库
            compensateRecordService.addCompensate(compensate);
            
        } catch (Exception e) {
            logger.error("user compensate error :",e);
            returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            returnInfo.setMessage(e.getMessage());
        }
        
        return response;
    }
	
	/**
     * 业态申请理赔
     */
    @Override
    public ProductCompensateResponse productCompensate(ProductCompensateRequest request) {
        
        ProductCompensateResponse response = new ProductCompensateResponse();
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(HttpStatus.OK.getStatusCode());
        response.setReturnInfo(returnInfo);
        
        // 保单是否有效
        InsurancePolicy policy = insurancePolicyService.getByRoomID(request.getRoomID());
        if(policy == null){
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason("房屋未投保");
            return response;
        }
        if(policy.getStatus()!=Constant.POLICY_STATUS_ENFORCED){
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason("保单当前状态不可赔付");
            return response;
        }
        
        // 订单是否已理赔
        CompensateRecord orderCompensate = compensateRecordService.getByOrderCode(request.getRoomID(), request.getOrderNo());
        if(orderCompensate != null){
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason("订单已存在理赔记录，不可重复理赔");
            return response;
        }
        
        // 商品是否属于保障范围
        PolicyGuaranteeItem policyGuaranteeItem = policyGuaranteeItemService.getByPolicyAndObject(policy.getId(),request.getProductId());
        if(policyGuaranteeItem == null){
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason("商品不在保单保障范围内");
            return response;
        }
        
        // 保障权益是否存在
        PolicyGuaranteePlan policyGuaranteePlan = policyGuaranteePlanService.getpolicyGuaranteePlanByPolicyIDAndItemID(policy.getId(),policyGuaranteeItem.getId());
        if(policyGuaranteePlan == null){
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason("保障权益为空");
            return response;
        }
        
        // 权益值是否足够 ,按次数，剩余次数需大于等于1； 按金额，剩余金额需大于请求金额
        BigDecimal requestMoney = new BigDecimal(request.getApplyMoney());
        
        if(!Constant.GUARANTEE_PLAN_UNLIMIT.equals(policyGuaranteePlan.getRightValue())){
            
            if((policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME && Integer.parseInt(policyGuaranteePlan.getBalanceValue()) < 1)
                    || (policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY && new BigDecimal(policyGuaranteePlan.getBalanceValue()).compareTo(requestMoney) == -1)){
                response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
                response.setRefuseReason("保障额度不足");
                return response;
            }
        }
        
        /***
         * 所有校验通过，开始理赔
         */
        response.setPolicyNo(policy.getPiccNo());
        response.setInsuranceProviderName(insuranceProviderName);
        
        // 创建理赔记录
        Date now = new Date();
        CompensateRecord compensate = new CompensateRecord();
        compensate.setId(Constant.ID_GENERATOR.generate());
        compensate.setPolicyId(policy.getId());
        compensate.setPiccNo(policy.getPiccNo());
        compensate.setGuaranteeItemId(policyGuaranteeItem.getId());
        compensate.setRoomId(request.getRoomID());
        compensate.setRoomAddress(policy.getRoomAddress());
        compensate.setHappenAt(request.getReportAt());
        compensate.setReportAt(now);
        compensate.setContactName(request.getContactName());
        compensate.setContactPhone(request.getContactPhone());
        compensate.setEstimateMoney(request.getApplyMoney());
        compensate.setAccidentMemo(request.getAccidentMemo());
        compensate.setAccidentImgs(request.getAccidentImgs());
        compensate.setDataStatus(Constant.COMPENSATERECORD_DATASTATUS_UNSYNC);
        compensate.setCreateAt(now);
        compensate.setCreateBy("system");
        compensate.setOrderNo(request.getOrderNo());
        compensate.setObjectId(request.getProductId());
        compensate.setObjectName(request.getProductName());
        
        // 理赔金额，按次数扣减1次， 按金额扣减请求金额
        String payMoney = null;
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME){
            payMoney = "1";
            
            response.setCompensateType("TIMES");
            response.setCompensateMoney("0");
        }
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY){
            payMoney = request.getApplyMoney();
            
            response.setCompensateType("MONEY");
            response.setCompensateMoney(request.getApplyMoney());
        }
        compensate.setPaidMoney(payMoney);
        
        // 理赔状态，理赔金额大于限制金额需PICC授权，不大于则直接锁定
        BigDecimal limitMoney = new BigDecimal(limitMoneyStr);
        if(limitMoney.compareTo(requestMoney) == -1){
            compensate.setStatus(Constant.COMPENSATERECORD_STATUS_UNAUTHOR);
            compensate.setPaidType(Constant.COMPENSATE_PAID_TYPE_PICC);
            
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_WAITING_AUTH);
        }else{
            compensate.setStatus(Constant.COMPENSATERECORD_STATUS_LOCKED);
            compensate.setPaidType(Constant.COMPENSATE_PAID_TYPE_SELF);
            
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_OK);
        }
     
        try {
            // 锁定权益
            policyGuaranteePlanService.lockRight(policy.getId(), policyGuaranteeItem.getId(), payMoney);
            
            // 理赔记录入库
            compensateRecordService.addCompensate(compensate);
            
        } catch (Exception e) {
            logger.error("product compensate error :",e);
            response.setResult(Constant.COMPENSATE_APPLY_RESULT_REFUSE);
            response.setRefuseReason(e.getMessage());
        }
        
        return response;
    }
	

	@Override
	public RoomInsureStatusInfoResponse getRoomInsureStatusByRoomID(String roomID) {
		RoomInsureStatusInfoResponse response = new RoomInsureStatusInfoResponse();
		ReturnInfo returnInfo = new ReturnInfo();

		try {

			List<RoomInsureStatusInfo> roomInsureStatusInfoList = insurancePolicyService
					.getRoomInsureStatusByRoomID(roomID);

			response.setRoomInsureStatusInfoList(roomInsureStatusInfoList);
			returnInfo.setCode(HttpStatus.OK.getStatusCode());
			returnInfo.setMessage("查询成功");
			response.setReturnInfo(returnInfo);

			if (roomInsureStatusInfoList == null || roomInsureStatusInfoList.size() == 0) {
				returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				returnInfo.setMessage("这个房屋没有投保");
				response.setReturnInfo(returnInfo);
			}
		} catch (Exception e) {

			logger.error("getRoomInsureStatusByRoomID异常", e);

			returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
			returnInfo.setMessage("查询失败" + e.getMessage());
			response.setReturnInfo(returnInfo);

		}
		return response;

	}

	@Override
	public InsurancePolicyDetailVo getPolicyDetailInfo(String policyId) {

		InsurancePolicyDetailVo detailVo = insurancePolicyService.getPolicyDetail(policyId, true, true);

		return detailVo;
	}


	@Override
	public UserPolicyListResponse getUserInsurancePolicyList(String userMid) {

		UserPolicyListResponse response = new UserPolicyListResponse();
		ReturnInfo returnInfo = new ReturnInfo();

		try {

			List<InsurancePolicyInfo> insurancePolicyInfos = insurancePolicyService.getUserInsurancePolicyList(userMid);

			response.setInsurancePolicyInfoList(insurancePolicyInfos);
			returnInfo.setCode(HttpStatus.OK.getStatusCode());
			returnInfo.setMessage("查询成功");
			response.setReturnInfo(returnInfo);

			if (insurancePolicyInfos == null || insurancePolicyInfos.size() == 0) {
				returnInfo.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				returnInfo.setMessage("该用户没有投保");
				response.setReturnInfo(returnInfo);
			}
		} catch (Exception e) {

			logger.error("getUserInsurancePolicyList异常", e);

			returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
			returnInfo.setMessage("查询失败" + e.getMessage());
			response.setReturnInfo(returnInfo);

		}
		return response;
	}

}
