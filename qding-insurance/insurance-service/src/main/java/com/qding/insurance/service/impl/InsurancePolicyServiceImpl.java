package com.qding.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.dao.CompensateRecordMapper;
import com.qding.insurance.dao.InsurancePolicyMapper;
import com.qding.insurance.dao.PolicyGuaranteePlanMapper;
import com.qding.insurance.domain.InsurancePolicy;
import com.qding.insurance.param.InsurancePolicyParam;
import com.qding.insurance.service.IInsurancePolicyService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.CompensateRecordResult;
import com.qding.insurance.vo.InsurancePolicyDetailVo;
import com.qding.insurance.vo.InsurancePolicyInfo;
import com.qding.insurance.vo.PolicyPlanVo;
import com.qding.insurance.vo.RoomInsureStatusInfo;

@Service("insurancePolicyService")
public class InsurancePolicyServiceImpl implements IInsurancePolicyService {

	@Autowired
	private InsurancePolicyMapper insurancePolicyMapper;
	
	@Autowired
	private PolicyGuaranteePlanMapper policyPlanMapper;
	
	@Autowired
	private CompensateRecordMapper compensateRecordMapper;

	@Override
	public ResultPage<InsurancePolicyDetailVo> getResultPage(InsurancePolicyParam param) {
		ResultPage<InsurancePolicyDetailVo> resultPage = null;
		// 查询总数
		Integer totalCount = insurancePolicyMapper.countByParam(param);
		// 查询列表
		List<InsurancePolicyDetailVo> list = insurancePolicyMapper.selectByParam(param);

		resultPage = new ResultPage<InsurancePolicyDetailVo>(totalCount, param.getSize(), param.getPage(), list);

		return resultPage;
	}

	@Override
	public InsurancePolicy getResultById(String id) {
		return insurancePolicyMapper.selectByPrimaryKey(id);

	}

	@Override
	public InsurancePolicy getResultByOrderID(String orderID) {
		return insurancePolicyMapper.getResultByOrderID(orderID);
	}

	@Override
	public List<RoomInsureStatusInfo> getRoomInsureStatusByRoomID(String roomID) {
		return insurancePolicyMapper.getRoomInsureStatusByRoomID(roomID);
	}

	@Override
	public List<InsurancePolicyInfo> getUserInsurancePolicyList(String userMid) {
		return insurancePolicyMapper.getUserInsurancePolicyList(userMid);
	}

	@Override
	public InsurancePolicy getResultByRoomID(String roomID) {
		return insurancePolicyMapper.getResultByRoomID(roomID);
	}


    @Override
    public void policyAct() {


        List<InsurancePolicy> list = insurancePolicyMapper.selectByStatus(Constant.POLICY_STATUS_UNENFORCED);

        Date now = new Date();
        
        for (InsurancePolicy policy : list) {
            if (policy.getActAt().before(now)) {
                policy.setStatus(Constant.POLICY_STATUS_ENFORCED);
                insurancePolicyMapper.updateByPrimaryKeySelective(policy);
            }
        }
    }

    @Override
    public void policyExpire() {
        List<InsurancePolicy> list = insurancePolicyMapper.selectByStatus(Constant.POLICY_STATUS_ENFORCED);

        Date now = new Date();
        
        for (InsurancePolicy policy : list) {
            if (policy.getExpireAt().before(now)) {
                policy.setStatus(Constant.POLICY_STATUS_WASDUE);
                insurancePolicyMapper.updateByPrimaryKeySelective(policy);
            }
        }
    }

    @Override
    public void addPolicy(InsurancePolicy policy) {
        insurancePolicyMapper.insert(policy);
    }

    @Override
    public void updatePolicy(InsurancePolicy policy) {
        insurancePolicyMapper.updateByPrimaryKeySelective(policy);
    }

    @Override
    public InsurancePolicy getByRoomID(String roomID) {
        List<InsurancePolicy> list = insurancePolicyMapper.selectByRoomId(roomID);
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }


    @Override
    public InsurancePolicyDetailVo getPolicyDetail(String policyId, boolean needRights, boolean needCompensate) {
        
        InsurancePolicyDetailVo detailVo = insurancePolicyMapper.getPolicyDetail(policyId);
        
        if(needRights){
            List<PolicyPlanVo> policyPlanList = policyPlanMapper.getPolicyPlan(policyId);
            detailVo.setPolicyPlanList(policyPlanList);
        }
        
        if(needCompensate){
            List<CompensateRecordResult> compensateList = compensateRecordMapper.selectPolicyCompensate(policyId, null);
            detailVo.setCompensateList(compensateList);
        }
        
        return detailVo;
    }

}
