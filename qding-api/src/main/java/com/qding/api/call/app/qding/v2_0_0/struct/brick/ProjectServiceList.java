package com.qding.api.call.app.qding.v2_0_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectService;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias(value = "projectServiceList")
public class ProjectServiceList implements Serializable {

    private static final long serialVersionUID = 3755750105604934727L;

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
