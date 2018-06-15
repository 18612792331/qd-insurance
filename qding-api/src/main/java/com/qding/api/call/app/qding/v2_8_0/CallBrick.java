package com.qding.api.call.app.qding.v2_8_0;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.request.GetProjectByIdRequest;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.request.GetProjectListByDistrictIdRequest;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data.GetProjectListByDistrictIdResponseData;
import com.qding.api.call.app.qding.v2_8_0.struct.project.response.data.GetIsGroupAddressResponseData;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.struts.request.ProjectRequest;
import com.qding.brick.struts.response.ProjectReadResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

/**
 * Created by qd on 2016/8/22.
 */
public class CallBrick extends  com.qding.api.call.app.qding.v2_5_0.CallBrick {

    @Autowired
    private ProjectReadRemote projectReadService;

    @HTTP(alias = "getProjectListByDistrictId")
    @ExplainAnnotation(explain = "获取社区列表")
    public Response<GetProjectListByDistrictIdResponseData> getProjectListByDistrictId (GetProjectListByDistrictIdRequest request) {
        Response<GetProjectListByDistrictIdResponseData> response = new Response<GetProjectListByDistrictIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetProjectListByDistrictIdResponseData data = new GetProjectListByDistrictIdResponseData();
        try {
            ProjectRequest regionRequest = transfor(ProjectRequest.class,request);
            ProjectReadResponse projectReadResponse =  projectReadService.getProjectsByRequest(regionRequest);
            checkAndContinue(projectReadResponse);
            List<Project> projectList = projectReadResponse.getProjects();
            List<com.qding.api.call.app.qding.v2_5_0.struct.brick.Project> list =
                    transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.Project.class, projectList);
            //2.8增加如果是组团地址置社区地址为空
            for(com.qding.api.call.app.qding.v2_5_0.struct.brick.Project p:list){
            	if(p.getIsGroupAddress()==1){
            		p.setStreet(null);
            	}
            }
            data.setList(list);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetProjectListByDistrictIdResponseData.class, e);
        }
        return response;

    }
    
    @HTTP(alias="getIsGroupAddress")
    @ExplainAnnotation(explain = "判断社区是否为组团")
	public Response<GetIsGroupAddressResponseData> getIsGroupAddress(GetProjectByIdRequest request) {
		try {
			Response<GetIsGroupAddressResponseData> response = new Response<GetIsGroupAddressResponseData>();
			Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
			if(project == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
			}
			GetIsGroupAddressResponseData data=new GetIsGroupAddressResponseData();
			data.setIsGroupAddress(project.getIsGroupAddress());
			response.setData(data);
			return response;
		} catch (Exception e) {
			return handleException(GetIsGroupAddressResponseData.class, e);
		}
    }

     
}
