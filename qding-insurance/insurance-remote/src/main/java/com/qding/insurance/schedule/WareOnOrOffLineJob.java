package com.qding.insurance.schedule;

import org.apache.log4j.Logger;

import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.SpringContextUtils;
import com.qding.insurance.service.IInsuranceWareService;
import com.qding.schedule.client.BaseScheduleJob;
import com.qding.schedule.common.struct.QdScheduleException;

/**
 * 过期任务自动下架
 */
public class WareOnOrOffLineJob extends BaseScheduleJob {
    private Logger logger = Logger.getLogger(WareOnOrOffLineJob.class);
    
    IInsuranceWareService wareService = (IInsuranceWareService) SpringContextUtils.getBean("insuranceWareService");
    
    public WareOnOrOffLineJob() {
    }

    @Override
    public void run() throws QdScheduleException {
        try {
            wareService.wareOnline();
        }catch (Exception e) {
            logger.error("保险产品上架失败",e);
            throw new QdScheduleException(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode()+"", "保险产品上架失败: "+e.getMessage());
        }
        
        try {
            wareService.wareOffline();
        }catch (Exception e) {
            logger.error("保险产品下架失败",e);
            throw new QdScheduleException(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode()+"", "保险产品下架失败: "+e.getMessage());
        }
    }
}
