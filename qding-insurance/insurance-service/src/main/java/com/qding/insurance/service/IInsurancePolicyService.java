package com.qding.insurance.service;

import java.util.List;

import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.InsurancePolicy;
import com.qding.insurance.param.InsurancePolicyParam;
import com.qding.insurance.vo.InsurancePolicyDetailVo;
import com.qding.insurance.vo.InsurancePolicyInfo;
import com.qding.insurance.vo.RoomInsureStatusInfo;

public interface IInsurancePolicyService {

    ResultPage<InsurancePolicyDetailVo> getResultPage(InsurancePolicyParam param);

    InsurancePolicy getResultById(String id);

    /**
     * 根据单证ID获取结果
     */
    InsurancePolicy getResultByOrderID(String orderID);

    /**
     * 根据房屋ID获取房屋投保状态
     */
    List<RoomInsureStatusInfo> getRoomInsureStatusByRoomID(String roomID);

    /**
     * 根据MID获取用户保单列表
     */
    List<InsurancePolicyInfo> getUserInsurancePolicyList(String userMid);

    /**
     * 查询单证详情
     * needRights 是否需要权益信息
     * needCompensate 是否需要理赔信息
     */
    InsurancePolicyDetailVo getPolicyDetail(String policyId, boolean needRights, boolean needCompensate);

    /**
     * 保险单证到期生效
     */
    void policyAct();

    /**
     * 保险单证到期失效
     */
    void policyExpire();

    /**
     * 根据RoomID查单证
     */
    InsurancePolicy getResultByRoomID(String roomID);

    /**
     * 根据房间ID和保单状态（1-投保中/2-未生效/4-已生效）查询保单
     * @param roomID
     * @return
     */
    InsurancePolicy getByRoomID(String roomID);

    void addPolicy(InsurancePolicy policy);

    void updatePolicy(InsurancePolicy policy);

}
