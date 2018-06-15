package com.qding.api.call.app.qding.v2_0_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 业态
 *
 * @author lichao
 */
@XStreamAlias(value = "projectService")
public class ProjectService implements Serializable {

    private static final long serialVersionUID = 3755750105604934727L;

    @ExplainAnnotation(explain = "业态服务ID")
    private String id;

    @ExplainAnnotation(explain = "业态服务名称")
    private String name;

    @ExplainAnnotation(explain = "业态服务跳转类型")
    private Integer type;

    @ExplainAnnotation(explain = "业态服务跳转内容")
    private String content;

    @ExplainAnnotation(explain = "业态服务图标")
    private String imageUrl;

    @ExplainAnnotation(explain = "业态服务描述")
    private String desc;

    @ExplainAnnotation(explain = "业态服务类型名称")
    private String serviceTypeName;

    @ExplainAnnotation(explain = "业态服务类型")
    private String serviceType;

    public ProjectService() {

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public ProjectService(String id, String name, Integer type, String content, String imageUrl, String desc, String serviceTypeName, String serviceType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.serviceTypeName = serviceTypeName;
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "ProjectService{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", desc='" + desc + '\'' +
                ", serviceTypeName='" + serviceTypeName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                '}';
    }
}
