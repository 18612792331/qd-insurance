package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/25.
 */
public class ProjectServiceList implements Serializable {

    private static final long serialVersionUID = 3529555164243936200L;

    @ExplainAnnotation(explain = "业态服务列表")
    private List<ProjectService> services;

    @ExplainAnnotation(explain = "业态服务标题")
    private String title;

    public ProjectServiceList() {
    }

    public ProjectServiceList(List<ProjectService> services, String title) {
        this.services = services;
        this.title = title;
    }

    public List<ProjectService> getServices() {
        return services;
    }

    public void setServices(List<ProjectService> services) {
        this.services = services;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProjectServiceList{" +
                "services=" + services +
                ", title='" + title + '\'' +
                '}';
    }
}
