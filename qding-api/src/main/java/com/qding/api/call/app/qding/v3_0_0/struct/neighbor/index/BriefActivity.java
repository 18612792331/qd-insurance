package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/24.
 */
public class BriefActivity extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 8193901024000372857L;

    @ExplainAnnotation (explain = "活动ID")
    private String  id;

    @ExplainAnnotation (explain = "活动图片")
    private String  img;

    @ExplainAnnotation (explain = "活动状态")
    private Integer status;

    @ExplainAnnotation (explain = "开始剩余时间",desc = "单位:秒")
    private Integer surplusTime;

    @ExplainAnnotation (explain = "活动名称")
    private String  name;

    @ExplainAnnotation (explain = "举办开始时间")
    private Long  startTime;

    @ExplainAnnotation (explain = "举办结束时间")
    private Long endTime;

    @ExplainAnnotation (explain = "举办地址")
    private String addr;

    @ExplainAnnotation (explain = "预计消费")
    private String consume;

    @ExplainAnnotation (explain = "参加人数")
    private Integer enrollCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(Integer surplusTime) {
        this.surplusTime = surplusTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public Integer getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(Integer enrollCount) {
        this.enrollCount = enrollCount;
    }
}
