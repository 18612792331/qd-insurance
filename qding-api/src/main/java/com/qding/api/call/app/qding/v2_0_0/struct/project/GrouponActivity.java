package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.project.ProductSalePolicyBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/11/3.
 */
public class GrouponActivity extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 4537205751463285340L;

    @ExplainAnnotation(explain = "阶梯团购ID")
    private String id;

    @ExplainAnnotation(explain = "简要描述")
    private String shortDesc;

    @ExplainAnnotation(explain = "开始时间")
    private Long startTime;

    @ExplainAnnotation(explain = "结束时间")
    private Long endTime;

    @ExplainAnnotation(explain = "图片列表")
    private List<String> imagez;

    @ExplainAnnotation(explain = "数量")
    private Integer num;

    @ExplainAnnotation(explain = "描述")
    private String desc;

    @ExplainAnnotation(explain = "阶段信息列表")
    private List<ProductSalePolicyBean> policies;

    @ExplainAnnotation(explain = "跳转地址")
    private String url;

    @ExplainAnnotation(explain = "活动名称")
    private String name;

    @ExplainAnnotation(explain = "识别码")
    private String markingCode;

    @ExplainAnnotation(explain = "标题")
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getImagez() {
        return imagez;
    }

    public void setImagez(List<String> imagez) {
        this.imagez = imagez;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ProductSalePolicyBean> getPolicies() {
        return policies;
    }

    public void setPolicies(List<ProductSalePolicyBean> policies) {
        this.policies = policies;
    }

    public String getMarkingCode() {
        return markingCode;
    }

    public void setMarkingCode(String markingCode) {
        this.markingCode = markingCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
