package com.qding.api.call.app.qding.v4_1_0;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.ApplyCompensateRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.CompensateRecordByIdRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.CompensateRecordRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceCheckRoomRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceGetGuaranteeDetailRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceGetGuaranteePlanRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceInsureRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceOrderDetailRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsurancePolicyDetailRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsurancePolicyListRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.InsuranceWareListRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.request.PolicyGuaranteeItemListRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.ApplyCompensateResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.ComResponseByIdResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.CompensateRecordResopnseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.CompensateSingleResopnseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceCheckRoomResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceGetGuaranteeDetailResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceGetGuaranteePlanResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceInsureResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceOrderDetailResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsurancePolicyDetailResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsurancePolicyListResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.InsuranceWareListResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.MemberAndInsurantInfo;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.PolicyDutyResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.PolicyGuaranteeItemListResponseData;
import com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data.PolicyListResponseData;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.ConvertUtil;
import com.qding.api.util.DateUtil;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.rpc.ICompensateRecordRpc;
import com.qding.insurance.rpc.IInsuranceOrderRpcService;
import com.qding.insurance.rpc.IInsurancePolicyRpc;
import com.qding.insurance.rpc.IInsuranceWareRpcService;
import com.qding.insurance.rpc.IPolicyGuaranteeItemRpc;
import com.qding.insurance.rpc.request.InsuranceOrderInsureRequest;
import com.qding.insurance.rpc.request.UserCompensateRequest;
import com.qding.insurance.rpc.response.CompensateRecordListResponse;
import com.qding.insurance.rpc.response.CompensateRecordResponse;
import com.qding.insurance.rpc.response.InsuranceOrderDetailResponse;
import com.qding.insurance.rpc.response.PolicyGuaranteeItemResponse;
import com.qding.insurance.rpc.response.UserPolicyListResponse;
import com.qding.insurance.vo.CompensateRecordVo;
import com.qding.insurance.vo.GuaranteePlanResultVo;
import com.qding.insurance.vo.InsuranceDetailByOrderNoVo;
import com.qding.insurance.vo.InsurancePolicyDetailVo;
import com.qding.insurance.vo.InsurancePolicyInfo;
import com.qding.insurance.vo.PolicyPlanVo;

@ExplainAnnotation (explain = "保险业务")
public class CallInsurance extends Callable {
	private static final Logger logger = Logger.getLogger(CallInsurance.class);

	@Autowired
	private IInsuranceOrderRpcService insuranceOrderRpcService;
	
	@Autowired
	private IInsuranceWareRpcService insuranceWareRpcService;
	
	@Autowired
	private IInsurancePolicyRpc insurancePolicyRpc;
	
	@Autowired
	private ICompensateRecordRpc compensateRecordRpc;
	
	@Autowired
    private ProjectReadRemote projectReadService;
	
	@Autowired
	private IPolicyGuaranteeItemRpc guaranteeItemRpc;
	
	private final String PROJECT_TYPE_LONGFOR = "project_longfor";
	private final String PROJECT_TYPE_NOT_LONGFOR = "project_notlongfor";
	
	private static final String COMPENSATE_TYPE_AMOUNT = "1";
	private static final String COMPENSATE_TYPE_AMOUNT_EXP = "元";
	private static final String COMPENSATE_TYPE_TIMES = "2";
	private static final String COMPENSATE_TYPE_TIMES_EXP = "次";
	
    @HTTP(alias = "checkWheatherInsured")
    @ExplainAnnotation(explain = "校验房屋是否已投保")
    public Response<InsuranceCheckRoomResponseData> checkWheatherInsured(InsuranceCheckRoomRequest request) {
    	logger.info("校验房屋是否已投保开始");
        try {
        	Response<InsuranceCheckRoomResponseData> response = new Response<InsuranceCheckRoomResponseData>();
        	InsuranceCheckRoomResponseData data = new InsuranceCheckRoomResponseData();

        	boolean result = insuranceOrderRpcService.checkWheatherInsured(request.getRoomId());
            data.setHasInsured(result);
            
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("校验成功，结果为:" + result);
            return response;
        } catch (Exception ex) {
            return handleException(InsuranceCheckRoomResponseData.class, ex);
        }
    }
    
    @HTTP(alias = "getGuaranteeDetail")
    @ExplainAnnotation(explain = "查看保障计划内容和详情")
    public Response<InsuranceGetGuaranteeDetailResponseData> getGuaranteeDetail(InsuranceGetGuaranteeDetailRequest request) {
    	logger.info("查看保障计划内容和详情");
        try {
        	Response<InsuranceGetGuaranteeDetailResponseData> response = new Response<InsuranceGetGuaranteeDetailResponseData>();
        	InsuranceGetGuaranteeDetailResponseData data = new InsuranceGetGuaranteeDetailResponseData();

        	InsuranceWareExtWithBLOBs ext = insuranceWareRpcService.getGuaranteeDetail(request.getWareId());
        	data.setInsuranceWareExtWithBLOBs(ext);
        	
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("查询成功");
            return response;
        } catch (Exception ex) {
            return handleException(InsuranceGetGuaranteeDetailResponseData.class, ex);
        }
    }
    
    
    @HTTP(alias = "getInsurancePolicyList", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "根据用户Mid查询保单列表")
    public Response<InsurancePolicyListResponseData> getInsurancePolicyList(InsurancePolicyListRequest request,UserToken userToken) {
    	logger.info("根据用户Mid查询保单列表");
    	try {
    		request.setMemberId(userToken.getMemberId());
			Response<InsurancePolicyListResponseData> response = new Response<InsurancePolicyListResponseData>();
			InsurancePolicyListResponseData data = new InsurancePolicyListResponseData();
			buildInsurancePolicyListResponseData(data,request);
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return response;
		} catch (Exception e) {
            return handleException(InsurancePolicyListResponseData.class, e);
		}
    }
    
    /**
     * 构造保单列表查询response
     * @param data
     * @param request
     */
    private void buildInsurancePolicyListResponseData(InsurancePolicyListResponseData data,InsurancePolicyListRequest request){
    	UserPolicyListResponse userPolicyListResponse =  insurancePolicyRpc.getUserInsurancePolicyList(request.getMemberId());
    	List<PolicyListResponseData> list = new ArrayList<PolicyListResponseData>();
    	if(userPolicyListResponse.getInsurancePolicyInfoList() != null ){
    		for(InsurancePolicyInfo insurancePolicyInfo:userPolicyListResponse.getInsurancePolicyInfoList()){
        		PolicyListResponseData policyListResponse = new PolicyListResponseData();
        		policyListResponse.setEffectTime(DateUtil.formatDate(insurancePolicyInfo.getActAt()));
        		policyListResponse.setExpireTime(DateUtil.formatDate(insurancePolicyInfo.getExpireAt()));
        		policyListResponse.setInsurantName(insurancePolicyInfo.getInsurantName());
        		policyListResponse.setPolicyNo(insurancePolicyInfo.getPiccNo());//PICC订单号
        		policyListResponse.setPolicyId(insurancePolicyInfo.getId());	//单证ID
        		policyListResponse.setRoomAddress(insurancePolicyInfo.getRoomAddress());
        		policyListResponse.setStatus(transferPolicyStatus(Integer.toString(insurancePolicyInfo.getStatus())));
        		policyListResponse.setWareName(insurancePolicyInfo.getWareName());
        		list.add(policyListResponse);
        	}
        	data.setList(list);
    	} else {
    		data.setMessage(userPolicyListResponse.getReturnInfo().getMessage());
    	}
    	
    }
    
   
    
    @HTTP(alias = "getInsurancePolicyDetail", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "保单详情")
    public Response<InsurancePolicyDetailResponseData> getInsurancePolicyDetail(InsurancePolicyDetailRequest request,UserToken userToken) {
    	logger.info("根据保单ID查询保单详情及保障权益");
    	try {
			Response<InsurancePolicyDetailResponseData> response = new Response<InsurancePolicyDetailResponseData>();
			InsurancePolicyDetailResponseData data = new InsurancePolicyDetailResponseData();
			response.setData(data);
			
			InsurancePolicyDetailVo policyDetailVo = insurancePolicyRpc.getPolicyDetailInfo(request.getPolicyId());
			
			if(policyDetailVo != null){
	            //保单基础明细
	            data.setEffectTime(DateUtil.formatDate(policyDetailVo.getActAt()));
	            data.setExpireTime(DateUtil.formatDate(policyDetailVo.getExpireAt()));
	            data.setInsurantName(policyDetailVo.getInsurantName());
	            data.setPolicyNo(policyDetailVo.getPiccNo());//PICC订单号
	            data.setPolicyId(policyDetailVo.getId());//单证ID
	            data.setRoomAddress(policyDetailVo.getRoomAddress());
	            data.setStatus(transferPolicyStatus(Integer.toString(policyDetailVo.getStatus())));
	            data.setWareName(policyDetailVo.getWareName());
	            data.setInsuranceCompany("中国人民财产保险股份有限公司");
	            data.setPolicyHolderName(policyDetailVo.getMemberName());
	            data.setBenefitName(policyDetailVo.getBenefitName());
	            data.setPaidMoney(policyDetailVo.getPaidMoney());
	            data.setPolicyUrl(policyDetailVo.getPolicyUrl());
	            // 保单责任
	            List<PolicyDutyResponseData> list = buildPolicyDutyList(policyDetailVo);
	            data.setList(list);
	        }
			
			
			return response;
		} catch (Exception e) {
            return handleException(InsurancePolicyDetailResponseData.class, e);
		}
    }
    
    
    /**
     * 构造保单详情中保险责任列表
     */
    private List<PolicyDutyResponseData> buildPolicyDutyList(InsurancePolicyDetailVo policyDetailVo) {
    	
        List<PolicyPlanVo> planList = policyDetailVo.getPolicyPlanList();
        List<PolicyDutyResponseData> list = new ArrayList<PolicyDutyResponseData>();
        
    	if(planList.size() > 0){
        	for(PolicyPlanVo policyPlanVo : planList){
        		PolicyDutyResponseData policyDutyResponseData = new PolicyDutyResponseData();
        		policyDutyResponseData.setGuaranteeItemTitle(policyPlanVo.getItemName());
        		// 理赔方式，1：按金额，2：按次数（次）
        		if(COMPENSATE_TYPE_AMOUNT.equals(Integer.toString(policyPlanVo.getCompensateType()))){
        			policyDutyResponseData.setRightValue(policyPlanVo.getRightValue()+COMPENSATE_TYPE_AMOUNT_EXP);
        			policyDutyResponseData.setBalanceRightValue(policyPlanVo.getBalanceValue()+COMPENSATE_TYPE_AMOUNT_EXP);
        		} else if(COMPENSATE_TYPE_TIMES.equals(Integer.toString(policyPlanVo.getCompensateType()))){
        			policyDutyResponseData.setBalanceRightValue(policyPlanVo.getBalanceValue()+COMPENSATE_TYPE_TIMES_EXP);
        			policyDutyResponseData.setRightValue(policyPlanVo.getRightValue()+COMPENSATE_TYPE_TIMES_EXP);
        		}
        		list.add(policyDutyResponseData);
        	}
        }
    	
    	return list;
    }
    
    @HTTP(alias = "applyCompensate", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "申请理赔")
    public Response<ApplyCompensateResponseData> applyCompensate(ApplyCompensateRequest request,UserToken userToken) {
    	logger.info("申请理赔");
    	Response<ApplyCompensateResponseData> response;
		try {
			response = new Response<ApplyCompensateResponseData>();
			ApplyCompensateResponseData data  = new ApplyCompensateResponseData();
			
			UserCompensateRequest userCompensateRequest = new UserCompensateRequest();
			userCompensateRequest.setMid(userToken.getMemberId());
			userCompensateRequest.setPolicyId(request.getPolicyId());
			userCompensateRequest.setItemId(request.getGuaranteeItemId());
			userCompensateRequest.setContactName(request.getContactName());
			userCompensateRequest.setContactPhone(request.getContactPhone());
			userCompensateRequest.setEstimateMoney(request.getEstimateMoney());
			userCompensateRequest.setAccidentMemo(request.getAccidentMemo());
			userCompensateRequest.setHappenAt(DateUtil.parseDateTime(request.getHappenTime()));
			
			BaseResponse userCompResponse = insurancePolicyRpc.userCompensate(userCompensateRequest);
			
			data.setMessage(userCompResponse.getReturnInfo().getMessage());
			response.setCode(userCompResponse.getReturnInfo().getCode());
			response.setData(data);
			return response;
		} catch (Exception e) {
            return handleException(ApplyCompensateResponseData.class, e);
		}
    	
    }
    
    @HTTP(alias = "getCompensateRecord", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "理赔记录")
    public Response<CompensateRecordResopnseData> getCompensateRecord(CompensateRecordRequest request,UserToken userToken) {
    	logger.info("根据用户Mid+保单ID查询理赔记录列表");
    	try {
			Response<CompensateRecordResopnseData> response  = new Response<CompensateRecordResopnseData>();
			CompensateRecordResopnseData data = new CompensateRecordResopnseData();
			return buildCompensateRecordResopnseData(data,request,response);
		} catch (Exception e) {
            return handleException(CompensateRecordResopnseData.class, e);
		}
    }
    
    /**
     * 构造理赔列表response
     * @param data
     * @param request
     */
    private Response<CompensateRecordResopnseData> buildCompensateRecordResopnseData(CompensateRecordResopnseData data,CompensateRecordRequest request,Response<CompensateRecordResopnseData> response) {
    	CompensateRecordListResponse compensateRecordListResponse
		= compensateRecordRpc.getCompensateRecord(request.getPolicyId(), request.getStatus());
		List<CompensateRecordVo> compensateRecordList = compensateRecordListResponse.getCompensateRecordList();
		List<CompensateSingleResopnseData> list = new ArrayList<CompensateSingleResopnseData>();
		if(HttpStatus.OK.getStatusCode() == compensateRecordListResponse.getReturnInfo().getCode()){
			if(compensateRecordList.size() > 0) {
				for(CompensateRecordVo compensateRecord:compensateRecordList){
					CompensateSingleResopnseData compensateSingleResopnseData
					 = new CompensateSingleResopnseData();
					compensateSingleResopnseData.setWareName(compensateRecord.getObjectName());//保障内容
					compensateSingleResopnseData.setInsurantName(compensateRecord.getInsurantName());//被保险人
					compensateSingleResopnseData.setCompensateStatus(transferCompensateStatus(Integer.toString(compensateRecord.getStatus())));
					compensateSingleResopnseData.setCreateTime(DateUtil.formatDate(compensateRecord.getCreateAt()));//申请时间（理赔单创建时间）
					compensateSingleResopnseData.setFinishTime(DateUtil.formatDate(compensateRecord.getFinishAt()));//完成时间
					compensateSingleResopnseData.setRoomAddress(compensateRecord.getRoomAddress());//保险财产地址
					compensateSingleResopnseData.setCompensateId(compensateRecord.getId());//理赔ID
					compensateSingleResopnseData.setPolicyId(compensateRecord.getPolicyId());//单证ID
					compensateSingleResopnseData.setPolicyIdNo(compensateRecord.getPiccNo());//PICC保单号
					// 理赔方式，1：按金额，2：按次数（次）
	        		if(COMPENSATE_TYPE_AMOUNT.equals(Integer.toString(compensateRecord.getCompensateType()))){
	        			compensateSingleResopnseData.setCompensateAmount(compensateRecord.getPaidMoney()+COMPENSATE_TYPE_AMOUNT_EXP);
	        		} else if(COMPENSATE_TYPE_TIMES.equals(Integer.toString(compensateRecord.getCompensateType()))){
	        			compensateSingleResopnseData.setCompensateAmount(compensateRecord.getPaidMoney() +COMPENSATE_TYPE_TIMES_EXP);
	        		}
					list.add(compensateSingleResopnseData);
				}
				data.setList(list);
			} else {
				data.setMessage("暂无理赔记录");
			}
		} else {
			data.setMessage(compensateRecordListResponse.getReturnInfo().getMessage());
		}
		response.setCode(compensateRecordListResponse.getReturnInfo().getCode());
		response.setData(data);
		return response;
    }
    
    /**
     * 保单状态转换
     * @param status
     * @return
     */
    private String transferPolicyStatus(String status){
    	if("1".equals(status) ){
    		return "投保中";
    	} else if("2".equals(status)){
    		return "未生效";
    	} else if("3".equals(status)){
    		return "投保失败";
    	} else if("4".equals(status)){
    		return "已生效";
    	} else if("5".equals(status)){
    		return "已到期";
    	} else if("6".equals(status)){
    		return "已终止";
    	} else {
    		return "未知";
    	}
    }
    
    /**
     * 理赔单状态转换
     * @param status
     * @return
     */
    private String transferCompensateStatus(String status){
    	if("1".equals(status) ){
    		return "已锁定";
    	} else if("2".equals(status)){
    		return "已完成";
    	} else if("3".equals(status)){
    		return "已撤销";
    	} else {
    		return "未知";
    	}
    }
    
    @HTTP(alias = "getGuaranteePlanBySkuInfo")
    @ExplainAnnotation(explain = "查看保障计划内容")
    public Response<InsuranceGetGuaranteePlanResponseData> getGuaranteePlanBySkuInfo(InsuranceGetGuaranteePlanRequest request) {
    	logger.info("查看保障计划内容");
        try {
        	Response<InsuranceGetGuaranteePlanResponseData> response = new Response<InsuranceGetGuaranteePlanResponseData>();
        	InsuranceGetGuaranteePlanResponseData data = new InsuranceGetGuaranteePlanResponseData();

        	//根据社区ID判断是否是龙湖社区
        	boolean longForProject = projectReadService.isLongForProject(Long.valueOf(request.getProjectId()));
        	String projectType = longForProject ? PROJECT_TYPE_LONGFOR : PROJECT_TYPE_NOT_LONGFOR;
        	GuaranteePlanResultVo resultVo = insuranceOrderRpcService.getGuaranteePlanBySkuInfo(projectType, request.getStyleType(), request.getTimeType());
        	data.setResultVo(resultVo);
        	
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("查询成功");
            return response;
        } catch (Exception ex) {
            return handleException(InsuranceGetGuaranteePlanResponseData.class, ex);
        }
    }
    
    @HTTP(alias = "insure")
    @ExplainAnnotation(explain = "投保")
    public Response<InsuranceInsureResponseData> insure(InsuranceInsureRequest request) {
    	logger.info("提交投保");
        try {
        	Response<InsuranceInsureResponseData> response = new Response<InsuranceInsureResponseData>();
        	InsuranceInsureResponseData data = new InsuranceInsureResponseData();

        	InsuranceOrderInsureRequest req = ConvertUtil.copy(request, InsuranceOrderInsureRequest.class);
        	insuranceOrderRpcService.insure(req);
        	
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("投保成功");
            return response;
        } catch (Exception ex) {
            return handleException(InsuranceInsureResponseData.class, ex);
        }
    }

    
    @HTTP(alias = "getInsuranceOrderDetail", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "保险订单详情")
    public Response<InsuranceOrderDetailResponseData> getInsuranceOrderDetail(InsuranceOrderDetailRequest request,UserToken userToken) {
    	logger.info("根据平台订单号查询保单详情");
    	try {
			Response<InsuranceOrderDetailResponseData> response = new Response<InsuranceOrderDetailResponseData>();
			InsuranceOrderDetailResponseData data = new InsuranceOrderDetailResponseData();
			buildInsuranceOrderDetailResponseData(data,request);
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return response;
		} catch (Exception e) {
            return handleException(InsuranceOrderDetailResponseData.class, e);
		}
    }
	
	private void buildInsuranceOrderDetailResponseData(InsuranceOrderDetailResponseData data,InsuranceOrderDetailRequest request) {
		InsuranceOrderDetailResponse insOderDetailResp
		= insuranceOrderRpcService.getInsOderDetailByOrderNo(request.getOrderNo());
		
		InsuranceDetailByOrderNoVo order = insOderDetailResp.getInsuranceOrderVo();
		if(order != null){
			data.setStatus(transferInsOrderStatus(Integer.toString(order.getStatus())));
			data.setImage(order.getImage());
			data.setWareName(order.getWareName());
			data.setOrderMoney(order.getOrderMoney());
			
			data.setMemberAndInsurantInfo(new MemberAndInsurantInfo(
					order.getMemberName(),order.getMemberPhone(),order.getMemberIdcard(),
					order.getInsurantName(),order.getInsurantPhone(),order.getInsurantIdcard(),
					order.getInsurantRelation()));
			
			data.setPolicyId(order.getPolicyId());
			data.setPolicyUrl(order.getPolicyUrl());
			data.setInsuranceCompany("中国人民财产保险股份有限公司");
			data.setCreateAt(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getCreateAt()));
			data.setOrderNo(order.getOrderNo());
			data.setPaidMoney(order.getPaidMoney());
		}else {
			data.setMessage("暂无订单记录");
		}
		
	}
	
	private String transferInsOrderStatus(String status) {
		if("1".equals(status) ){
    		return "未支付";
    	} else if("2".equals(status)){
    		return "已支付";
    	} else if("3".equals(status)){
    		return "已完成";
    	} else if("4".equals(status)){
    		return "已取消";
    	} else {
    		return "未知";
    	}
	}

    

    
    @HTTP(alias = "insureWareList")
    @ExplainAnnotation(explain = "全部保险产品列表")
    public Response<InsuranceWareListResponseData> insure(InsuranceWareListRequest request) {
    	logger.info("查询全部保险产品列表");
        try {
        	Response<InsuranceWareListResponseData> response = new Response<InsuranceWareListResponseData>();
        	InsuranceWareListResponseData data = new InsuranceWareListResponseData();

        	List<InsuranceWareExtWithBLOBs> wareList = insuranceWareRpcService.getAllWareList();
        	data.setList(wareList);
        	
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("查询成功");
            return response;
        } catch (Exception ex) {
            return handleException(InsuranceWareListResponseData.class, ex);
        }
    }
    
    
    @HTTP(alias = "getCompentRecordDetail",isNeadToken = false, isRequireAuth = false)
    @ExplainAnnotation(explain = "理赔信息管理-根据id查询")
    public Response<ComResponseByIdResponseData> getCompentRecordDetail(CompensateRecordByIdRequest request){
    	logger.info("理赔信息管理-根据id查询");
    	try {
    		Response<ComResponseByIdResponseData> response = new Response<ComResponseByIdResponseData>();
    		ComResponseByIdResponseData data = new ComResponseByIdResponseData();
    		buildComResponseByIdResponseData(data,request);
    		response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("查询成功");
            return response;
		} catch (Exception e) {
			return handleException(ComResponseByIdResponseData.class, e);
		}
    	
    	
    	
    }


	private void buildComResponseByIdResponseData(
			ComResponseByIdResponseData data, CompensateRecordByIdRequest request) {
		CompensateRecordResponse response = compensateRecordRpc.getCompensateRecordById(request.getId());
		CompensateRecordVo record = response.getCompensateRecord();
		if(record != null){
			data.setWareName(record.getWareName());
			// 理赔方式，1：按金额，2：按次数（次）
    		if(COMPENSATE_TYPE_AMOUNT.equals(Integer.toString(record.getCompensateType()))){
    			data.setCompensateAmount(record.getPaidMoney()+COMPENSATE_TYPE_AMOUNT_EXP);
    		} else if(COMPENSATE_TYPE_TIMES.equals(Integer.toString(record.getCompensateType()))){
    			data.setCompensateAmount(record.getPaidMoney() +COMPENSATE_TYPE_TIMES_EXP);
    		}
    		
    		data.setCompensateStatus(transferCompensateStatus(Integer.toString(record.getStatus())));
    		data.setCreateTime(DateUtil.formatDate(record.getCreateAt()));
			data.setFinishTime(DateUtil.formatDate(record.getFinishAt()));
			data.setInsurantName(record.getInsurantName());
			data.setRoomAddress(record.getRoomAddress());
		}else{
			data.setMessage("暂无理赔信息");
		}
		
		
	}


    @HTTP(alias = "getPolicyGuaranteeitemList", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "通过单证ID获取保障内容列表")
    public Response<PolicyGuaranteeItemListResponseData> getPolicyGuaranteeitemList(PolicyGuaranteeItemListRequest request,UserToken userToken){
    	Response<PolicyGuaranteeItemListResponseData> response = new Response<PolicyGuaranteeItemListResponseData>();
    	PolicyGuaranteeItemListResponseData data = new PolicyGuaranteeItemListResponseData();
    	PolicyGuaranteeItemResponse policyGuaranteeItemResponse = guaranteeItemRpc.getPolicyGuaranteeItemList(request.getPolicyId());
    	buildPolicyGuaranteeItemList(data,policyGuaranteeItemResponse);
    	response.setData(data);
    	response.setCode(policyGuaranteeItemResponse.getReturnInfo().getCode());
    	return response;
    }
    
    /**
     * 构造保障内容列表
     * @param data
     * @param policyGuaranteeItemResponse
     */
    private void buildPolicyGuaranteeItemList(PolicyGuaranteeItemListResponseData data,PolicyGuaranteeItemResponse policyGuaranteeItemResponse){
    	List<PolicyGuaranteeItem> list = policyGuaranteeItemResponse.getPolicyGuaranteeItemList();
    	List<PolicyDutyResponseData> resultList = new ArrayList<>();
    	if(list.size() > 0){
    		for(PolicyGuaranteeItem res : list){
    			PolicyDutyResponseData singleData = new PolicyDutyResponseData();
    			singleData.setGuaranteeItemId(res.getId());
    			singleData.setGuaranteeItemTitle(res.getItemTitle());//保障标题
    			singleData.setCompensateType(String.valueOf(res.getCompensateType()));//理赔方式，1：按金额，2：按次数（次）
    			singleData.setItemType(String.valueOf(res.getItemType()));//保障对象属性，1：商品，2：外部理赔
    			resultList.add(singleData);
    		}
    		data.setList(resultList);
    	} else {
    		data.setMessage(policyGuaranteeItemResponse.getReturnInfo().getMessage());
    	}
    	
    }

}