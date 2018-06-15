package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.util.APIPropertiesClient;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.util.QDStringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.qding.brick.enums.BizTypeEnum.Project;

/**
 * Created by qd on 2017/9/12.
 */
public class BrickService extends Callable {

    @Autowired
    private ProjectReadRemote projectReadService;

    /**
     * 获取指定ID的社区信息
     * @param projectId
     * @return
     */
    public Project getProjectById (Long projectId){
         Project project = projectReadService.get(projectId);
        return  project;
    }

    /**
     * 是否属于2018年会社区
     * @param projectId
     * @return
     */
    public boolean isAnnualMettingProject(String projectId) {
        String annualMettingProjectIds = APIPropertiesClient.getValue("annual_meeting_projectIds");
        List<String> annualMettingProjectIdList = Lists.newArrayList();
        if(QDStringUtil.isNotEmpty(annualMettingProjectIds)){
            String[] projectIds = annualMettingProjectIds.split(",");
            annualMettingProjectIdList = Arrays.asList(projectIds);
        }
        if(annualMettingProjectIdList.contains(projectId)) {
            return true;
        }
        return false;
    }
}
