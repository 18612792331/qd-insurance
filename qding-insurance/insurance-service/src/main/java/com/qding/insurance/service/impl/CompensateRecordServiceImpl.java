package com.qding.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.dao.CompensateRecordMapper;
import com.qding.insurance.domain.CompensateRecord;
import com.qding.insurance.param.CompensateRecordParam;
import com.qding.insurance.service.ICompensateRecordService;
import com.qding.insurance.service.MessagePubService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.CompensateRecordPageResult;
import com.qding.insurance.vo.CompensateRecordResult;
import com.qding.insurance.vo.CompensateRecordVo;

@Service("compensateRecordService")
public class CompensateRecordServiceImpl implements ICompensateRecordService {

	@Autowired
	private CompensateRecordMapper compensateRecordMapper;
	
	@Autowired
	private MessagePubService messagePubService;

	@Override
	public List<CompensateRecordResult> getPolicyCompensate(String policyId, String itemId) {

		return compensateRecordMapper.selectPolicyCompensate(policyId,itemId);
	}

	@Override
	public ResultPage<CompensateRecordPageResult> getResultPage(CompensateRecordParam param) {

		ResultPage<CompensateRecordPageResult> resultPage = null;
		// 查询总数
		Integer totalCount = compensateRecordMapper.countByParam(param);
		// 查询列表
		List<CompensateRecordPageResult> list = compensateRecordMapper.selectByParam(param);

		resultPage = new ResultPage<CompensateRecordPageResult>(totalCount, param.getSize(), param.getPage(), list);

		return resultPage;
	}

	@Override
	public CompensateRecord findById(String id) {
		return compensateRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CompensateRecordVo> getCompensateRecordListByPolicyIDandStatus(
			String policyID, Integer status) {
		return compensateRecordMapper.selectCompensateRecordListByPolicyIDandStatus(policyID,status);
	}

    @Override
    public void piccAuth(String id) throws Exception {
        
        // 理赔记录状态  代授权 --> 已锁定
        CompensateRecord record = compensateRecordMapper.selectByPrimaryKey(id);
        if(record==null || record.getStatus()!=Constant.COMPENSATERECORD_STATUS_UNAUTHOR){
            throw new Exception("数据状态错误");
        }
        record.setStatus(Constant.COMPENSATERECORD_STATUS_LOCKED);
        compensateRecordMapper.updateByPrimaryKey(record);
        
        // 业态理赔授权通过需发布广播消息
        if(!StringUtils.isEmpty(record.getOrderNo())){
            messagePubService.piccAuthMessage(record);
        }
    }


    @Override
    public void addCompensate(CompensateRecord compensate) {
        compensateRecordMapper.insert(compensate);
    }

    @Override
    public void updateCompensate(CompensateRecord compensate) {
        compensateRecordMapper.updateByPrimaryKeySelective(compensate);
    }

    @Override
    public CompensateRecord getByOrderCode(String roomId, String orderCode) {
        return compensateRecordMapper.selectByOrderCode(roomId, orderCode);
    }

	@Override
	public CompensateRecordVo getComRecordVoById(String id) {
		return compensateRecordMapper.selectById(id);
	}
}
