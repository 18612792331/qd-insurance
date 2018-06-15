package com.qding.api.call.app.qding.v1_3_1.struct.project.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/8/11.
 */
public class GetServiceIsOpenByProjectIdRequest extends BaseRequest {


    private static final long serialVersionUID = 4505303005591851434L;
    /**
     * 社区ID
     */
    @NotNullValidate
    private String projectId;

    /**
     * 业态类型
     *
     */
    @NotNullValidate
    private String serviceType;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
