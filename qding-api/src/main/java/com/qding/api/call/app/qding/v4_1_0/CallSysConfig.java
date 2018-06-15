package com.qding.api.call.app.qding.v4_1_0;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v4_1_0.struct.propertyService.PropertyServiceResultDTO;
import com.qding.api.call.app.qding.v4_1_0.struct.propertyService.PropertyServiceSubClazResultDTO;
import com.qding.api.call.app.qding.v4_1_0.struct.propertyService.request.PropertyServiceRequest;
import com.qding.api.call.app.qding.v4_1_0.struct.propertyService.response.data.PropertyServiceResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.sysconfig.rpc.model.PropertyserviceResultListModel;
import com.qding.sysconfig.rpc.model.PropertyserviceResultSubListModel;
import com.qding.sysconfig.rpc.service.PropertyServiceConfigRpcService;

public class CallSysConfig extends com.qding.api.call.app.qding.v2_4_0.CallSysConfig {
	private static final Logger logger = Logger.getLogger(CallSysConfig.class);

	@Autowired
	private PropertyServiceConfigRpcService propertyServiceConfigRpcService;
	
    @HTTP(alias = "getPropertyServiceList")
    @ExplainAnnotation(explain = "物业报事服务类型列表")
    public Response<PropertyServiceResponseData> getPropertyServiceList(PropertyServiceRequest request) {
    	logger.info("查询物业报事服务类型列表开始");
        try {
        	Response<PropertyServiceResponseData> response = new Response<PropertyServiceResponseData>();
            PropertyServiceResponseData data = new PropertyServiceResponseData();
            List<PropertyServiceResultDTO> resultDTOList = new ArrayList<PropertyServiceResultDTO>();

            List<PropertyserviceResultListModel> resultList = propertyServiceConfigRpcService.getPropertyServiceList(request.getProjectId());
            if (CollectionUtils.isNotEmpty(resultList)) {
				for (PropertyserviceResultListModel resultModel : resultList) {
					PropertyServiceResultDTO resultDTO = new PropertyServiceResultDTO();
					resultDTO.setMainId(resultModel.getMainId());
					resultDTO.setMainName(resultModel.getMainName());
					
					List<PropertyServiceSubClazResultDTO> subDTOList = new ArrayList<PropertyServiceSubClazResultDTO>();
					List<PropertyserviceResultSubListModel> subModelList = resultModel.getSubList();
					if (CollectionUtils.isNotEmpty(subModelList)) {
						for (PropertyserviceResultSubListModel subModel : subModelList) {
							PropertyServiceSubClazResultDTO subDTO = new PropertyServiceSubClazResultDTO();
							subDTO.setSubId(subModel.getSubId());
							subDTO.setSubName(subModel.getSubName());
							subDTO.setImgUrl(subModel.getImgUrl());
							subDTOList.add(subDTO);
						}
					}
					resultDTO.setSubList(subDTOList);
					resultDTOList.add(resultDTO);
				}
			}
            
            data.setResultList(resultDTOList);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            logger.info("查询成功");
            return response;
        } catch (Exception ex) {
            return handleException(PropertyServiceResponseData.class, ex);
        }
    }
}
