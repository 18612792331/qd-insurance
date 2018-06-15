package com.qding.insurance.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qding.core.common.util.DateUtil;
import com.qding.framework.common.AppUser;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.util.JsonUtil;
import com.qding.insurance.domain.CompensateRecord;
import com.qding.insurance.param.CompensateRecordParam;
import com.qding.insurance.param.FinishCompensateParam;
import com.qding.insurance.service.ICompensateRecordService;
import com.qding.insurance.service.IPolicyGuaranteePlanService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.CompensateRecordPageResult;
import com.qding.insurance.vo.CompensateRecordResult;
import com.qding.insurance.vo.ModelResult;
import com.qding.manager.rpc.ManagerRPCService;

/**
 * 理赔记录管理
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/compensateRecord")
public class CompensateRecordController {

	private Logger logger = Logger.getLogger(CompensateRecordController.class);
	
	@Autowired
	private ICompensateRecordService compensateRecordService;
	
	@Autowired
    private IPolicyGuaranteePlanService policyGuaranteePlanService;
	

    /**
     * 理赔记录管理 - 搜索
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult<CompensateRecordPageResult> list(HttpServletRequest request, CompensateRecordParam param) {
        ModelResult<CompensateRecordPageResult> modelResult = new ModelResult<CompensateRecordPageResult>();
       
        ResultPage<CompensateRecordPageResult> resultPage = compensateRecordService.getResultPage(param);
        
        modelResult.setResultPage(resultPage);
        modelResult.setCode(ModelResult.CODE_SUCCESS);
        modelResult.setMessage("查询成功");
        
        return modelResult;
    }
	
	/**
	 * 理赔记录管理 - 更改数据状态
	 */
	@RequestMapping(value = "updateDataStatus", method = RequestMethod.POST)
	@ResponseBody
	public ModelResult updateDataStatus(String id) {

		ModelResult modelResult = new ModelResult();

		CompensateRecord compensate = compensateRecordService.findById(id);
		
		// 数据同步状态[未同步] && 理赔状态[已完成] && 理赔性质[自授权 or PICC授权]
		if(compensate.getDataStatus()==Constant.COMPENSATERECORD_DATASTATUS_UNSYNC
		        && compensate.getStatus()==Constant.COMPENSATERECORD_STATUS_FINISHED
		        && (compensate.getPaidType()==Constant.COMPENSATE_PAID_TYPE_SELF || compensate.getPaidType()==Constant.COMPENSATE_PAID_TYPE_PICC)){
		    
		    compensate.setDataStatus(Constant.COMPENSATERECORD_DATASTATUS_SYNCED);
		    compensateRecordService.updateCompensate(compensate);
		    
		    modelResult.setCode(ModelResult.CODE_SUCCESS);
	        modelResult.setMessage("修改成功");
		}else{
		    modelResult.setCode(ModelResult.CODE_VALID_ERROR);
	        modelResult.setMessage("当前不可更改同步状态");
		}
		return modelResult;
	}
	
	
	/**
	 * 理赔记录管理 - 完成理赔（更改理赔状态） 
	 */
	@RequestMapping(value = "finish", method = RequestMethod.POST)
	@ResponseBody
	public ModelResult finish(FinishCompensateParam param) {
		logger.info("updateDataStatusFinished :" + JsonUtil.Java2Json(param));

		ModelResult modelResult = new ModelResult();

		CompensateRecord compensate = compensateRecordService.findById(param.getId());
		
		// 已锁定 && 外部理赔, 才可以操作
		if(compensate.getStatus()!=Constant.COMPENSATERECORD_STATUS_LOCKED || compensate.getPaidType()!=Constant.COMPENSATE_PAID_TYPE_OUT){
		    modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("当前状态不可完成");
            return modelResult;
		}
		
        try {
            // 完成已锁定权益
            policyGuaranteePlanService.finishLockRight(compensate.getPolicyId(), compensate.getGuaranteeItemId(), compensate.getPaidMoney());
        } catch (Exception e) {
            logger.error("finish lock right error ",e); 
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保单权益完成失败");
            return modelResult;
        }
        
        // 理赔记录修改
        AppUser appUser = ManagerRPCService.getCurrentUserInfo();
        
        compensate.setReportNo(param.getReportNo());
        compensate.setHappenAt(DateUtil.strToDate(param.getHappenAt(), "yyyy-MM-dd HH:mm:ss"));
        compensate.setContactName(param.getContactName());
        compensate.setContactPhone(param.getContactPhone());
        compensate.setPaidMoney(param.getPaidMoney());
        compensate.setDataStatus(Constant.COMPENSATERECORD_DATASTATUS_SYNCED);
        compensate.setAccidentMemo(param.getAccidentMemo());
        compensate.setStatus(Constant.COMPENSATERECORD_STATUS_FINISHED);
        compensate.setFinishAt(new Date());
        compensate.setFinishBy(appUser.getUsername());
        compensateRecordService.updateCompensate(compensate);
		
		modelResult.setCode(ModelResult.CODE_SUCCESS);
		modelResult.setMessage("操作成功");
		return modelResult;
	}
	
	/**
     * 理赔记录管理 - 撤销 
     */
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	@ResponseBody
	public ModelResult cancel(String id) {

	    ModelResult modelResult = new ModelResult();

        CompensateRecord compensate = compensateRecordService.findById(id);
        
        // 已锁定 && (PICC授权 or 外部理赔), 才可以操作
        if(compensate.getStatus()!=Constant.COMPENSATERECORD_STATUS_LOCKED 
                || !(compensate.getPaidType()==Constant.COMPENSATE_PAID_TYPE_PICC || compensate.getPaidType()==Constant.COMPENSATE_PAID_TYPE_OUT)){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("当前状态不可撤销");
            return modelResult;
        }
        
        try {
            // 解锁已锁定权益
            policyGuaranteePlanService.unLockRight(compensate.getPolicyId(), compensate.getGuaranteeItemId(), compensate.getPaidMoney());
        } catch (Exception e) {
            logger.error("unlock right error ",e); 
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("保单权益解锁失败");
            return modelResult; 
        }
        
        // 理赔记录取消
        AppUser appUser = ManagerRPCService.getCurrentUserInfo();
        compensate.setStatus(Constant.COMPENSATERECORD_STATUS_CANCELED);
        compensate.setFinishAt(new Date());
        compensate.setFinishBy(appUser.getUsername());
        compensateRecordService.updateCompensate(compensate);
        
        modelResult.setCode(ModelResult.CODE_SUCCESS);
        modelResult.setMessage("操作成功");
        return modelResult;
	}
	
	/**
	 * 理赔记录管理 - PICC授权
	 */
    @RequestMapping(value = "piccAuth", method = RequestMethod.POST)
	@ResponseBody
	public ModelResult piccAuth(String id) {
	    ModelResult modelResult = new ModelResult();
	    
        try {
            compensateRecordService.piccAuth(id);
            
            modelResult.setCode(ModelResult.CODE_SUCCESS);
            modelResult.setMessage("授权成功");
        } catch (Exception e) {
            logger.error("picc授权失败:"+e.getMessage(),e); 
            modelResult.setCode(ModelResult.CODE_SYSTEM_ERROR);
            modelResult.setMessage(e.getMessage());
        }
	    return modelResult;
	}
}
