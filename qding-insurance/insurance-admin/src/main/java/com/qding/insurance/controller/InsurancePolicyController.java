package com.qding.insurance.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qding.core.common.util.DateUtil;
import com.qding.framework.common.AppUser;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.InsurancePolicy;
import com.qding.insurance.domain.PolicyGuaranteeItem;
import com.qding.insurance.domain.PolicyGuaranteePlan;
import com.qding.insurance.param.AddCompensateParam;
import com.qding.insurance.param.InsurancePolicyParam;
import com.qding.insurance.service.ICompensateRecordService;
import com.qding.insurance.service.IInsurancePolicyService;
import com.qding.insurance.service.IPolicyGuaranteeItemService;
import com.qding.insurance.service.IPolicyGuaranteePlanService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.CompensateRecordResult;
import com.qding.insurance.vo.InsurancePolicyDetailVo;
import com.qding.insurance.vo.ModelResult;
import com.qding.manager.rpc.ManagerRPCService;

/**
 * 单证管理
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/insurancePolicy")
public class InsurancePolicyController { 

	Logger logger = Logger.getLogger(InsurancePolicyController.class);

	@Autowired
	private IInsurancePolicyService insurancePolicyService;
	
	@Autowired
	private IPolicyGuaranteeItemService policyItemService;
	
	@Autowired
	private ICompensateRecordService compensateService;
	
	@Autowired
	private IPolicyGuaranteePlanService policyPlanService;
	
	/**
	 * 查看理赔记录(按单证查 和 按保障内容查 共用)
	 */
	@RequestMapping(value = "getCompensate", method = RequestMethod.GET)
	@ResponseBody
	public ModelResult getCompensate(String policyId, String itemId) {
	    ModelResult modelResult = new ModelResult();
	    
	    if(StringUtils.isEmpty(policyId)){
	        modelResult.setCode(ModelResult.CODE_VALID_ERROR);
	        modelResult.setMessage("单证ID不可空");
	        return modelResult;
	    }
	    
	    List<CompensateRecordResult> list = compensateService.getPolicyCompensate(policyId, itemId);
	    
	    modelResult.setList(list);
	    modelResult.setCode(ModelResult.CODE_SUCCESS);
	    modelResult.setMessage("查询成功");
	    return modelResult;
	}
	
	/**
	 * 搜索
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ModelResult<ResultPage<InsurancePolicyDetailVo>> list(HttpServletRequest request, InsurancePolicyParam param) {
		ModelResult<ResultPage<InsurancePolicyDetailVo>> modelResult = new ModelResult<ResultPage<InsurancePolicyDetailVo>>();
		
		ResultPage<InsurancePolicyDetailVo> resultPage = insurancePolicyService.getResultPage(param);
		
		modelResult.setCode(ModelResult.CODE_SUCCESS);
		modelResult.setMessage("查询成功");
		modelResult.setResultPage(resultPage);
		return modelResult;
	}

	/**
	 * 保单详情
	 */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult<InsurancePolicyDetailVo> detail(HttpServletRequest request, String id) {

        ModelResult<InsurancePolicyDetailVo> modelResult = new ModelResult<InsurancePolicyDetailVo>();

        InsurancePolicyDetailVo detailVo = insurancePolicyService.getPolicyDetail(id, true, true);

        modelResult.setCode(ModelResult.CODE_SUCCESS);
        modelResult.setMessage("查询成功");
        modelResult.setEntity(detailVo);
        return modelResult;
    }

	/**
	 * 终止保单
	 */
	@RequestMapping(value = "termPolicy", method = RequestMethod.POST)
	@ResponseBody
	public ModelResult termPolicy(String id, String endReason) {

		ModelResult modelResult = new ModelResult();
		
		InsurancePolicy policy = insurancePolicyService.getResultById(id);
		
		if(policy.getStatus() != Constant.POLICY_STATUS_ENFORCED){
		    modelResult.setCode(ModelResult.CODE_VALID_ERROR);
	        modelResult.setMessage("当前不可终止保单");
	        return modelResult;
		}
		
		policy.setStatus(Constant.POLICY_STATUS_TERMINATED);
		policy.setEndAt(new Date());
		
		insurancePolicyService.updatePolicy(policy);
		
		modelResult.setCode(ModelResult.CODE_SUCCESS);
		modelResult.setMessage("终止成功");
		
		return modelResult;
	}
	
    /**
     * 添加理赔记录 - 申请类型选项
     */
    @RequestMapping(value = "itemList", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult itemList(String policyId) {

        ModelResult modelResult = new ModelResult();
        
        List<PolicyGuaranteeItem> itemList = policyItemService.getByPolicyId(policyId);
        
        modelResult.setList(itemList);
        modelResult.setCode(ModelResult.CODE_SUCCESS);
        modelResult.setMessage("查询成功");
        return modelResult;
    }
    
    /**
     * 添加理赔记录 
     */
    @RequestMapping(value = "addCompensate", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult addCompensate(AddCompensateParam param) {
        
        ModelResult modelResult = new ModelResult();
        
        // 主要参数校验
        if(StringUtils.isEmpty(param.getPolicyId()) || StringUtils.isEmpty(param.getGuaranteeItemId())){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("参数错误");
            return modelResult;
        }
        
        // 保单校验
        InsurancePolicy policy = insurancePolicyService.getResultById(param.getPolicyId());
        if(policy == null){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保单不存在");
            return modelResult;
        }
        if(policy.getStatus()!=Constant.POLICY_STATUS_ENFORCED){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保单当前状态不可赔付");
            return modelResult;
        }
        
        // 保障内容
        PolicyGuaranteeItem policyGuaranteeItem = policyItemService.getResultByID(param.getGuaranteeItemId());
        if(policyGuaranteeItem == null){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保障内容不存在");
            return modelResult;
        }
        
        // 保障权益是否存在
        PolicyGuaranteePlan policyGuaranteePlan = policyPlanService.getpolicyGuaranteePlanByPolicyIDAndItemID(policy.getId(),policyGuaranteeItem.getId());
        if(policyGuaranteePlan == null){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保障权益不存在");
            return modelResult;
        }
        
        // 权益值是否足够 ,按次数，剩余次数需大于等于1； 按金额，剩余金额需大于请求金额
        BigDecimal requestMoney = new BigDecimal(param.getEstimateMoney());
        
        if(!Constant.GUARANTEE_PLAN_UNLIMIT.equals(policyGuaranteePlan.getRightValue())){
            
            if((policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME && Integer.parseInt(policyGuaranteePlan.getBalanceValue()) < 1)
                    || (policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY && new BigDecimal(policyGuaranteePlan.getBalanceValue()).compareTo(requestMoney) == -1)){
                modelResult.setCode(ModelResult.CODE_VALID_ERROR);
                modelResult.setMessage("保障额度不足");
                return modelResult;
            }
        }
        
        /**
         * 可以理赔
         */
        Date now = new Date();
        AppUser appUser = ManagerRPCService.getCurrentUserInfo();
        
        
        // 填充理赔信息
        param.setId(Constant.ID_GENERATOR.generate());
        param.setPiccNo(policy.getPiccNo());
        param.setRoomId(policy.getRoomId());
        param.setRoomAddress(policy.getRoomAddress());
        param.setHappenAt(DateUtil.strToDate(param.getHappenAtStr(), Constant.DATE_FORMAT));
        param.setReportAt(now);
        param.setCreateAt(now);
        param.setCreateBy(appUser.getUsername());
        param.setPaidType(Constant.COMPENSATE_PAID_TYPE_OUT);
        // 理赔金额，按次数扣减1次， 按金额扣减请求金额
        String payMoney = null;
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_TIME){
            payMoney = "1";
        }
        if(policyGuaranteeItem.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY){
            payMoney = param.getEstimateMoney();
        }
        param.setPaidMoney(payMoney);
        
        
        try {
            // 先锁定权益
            policyPlanService.lockRight(policy.getId(), policyGuaranteeItem.getId(), payMoney);
            
            // 如果录入的是“完成”状态，直接把锁定值完成掉
            if(param.getStatus() == Constant.COMPENSATERECORD_STATUS_FINISHED){
                policyPlanService.finishLockRight(policy.getId(), policyGuaranteeItem.getId(), payMoney);
            }
            
            // 理赔记录入库
            compensateService.addCompensate(param);
            
            modelResult.setCode(ModelResult.CODE_SUCCESS);
            modelResult.setMessage("添加成功");
        } catch (Exception e) {
            logger.error("manager compensate error :",e);
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage(e.getMessage());
        }
        
        return modelResult;
    }
}
