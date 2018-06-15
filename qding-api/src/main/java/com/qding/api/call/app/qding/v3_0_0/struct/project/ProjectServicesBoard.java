package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class ProjectServicesBoard  implements Serializable {

    private static final long serialVersionUID = -3914648013167608297L;


    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "常用工具")
    private List<ProjectService> projectServices = Lists.newArrayList();


    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public List<ProjectService> getProjectServices() {
        return projectServices;
    }

    public void setProjectServices(List<ProjectService> projectServices) {
        this.projectServices = projectServices;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
