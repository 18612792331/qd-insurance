package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/10/27.
 */
public class Group implements Serializable {

    private static final long serialVersionUID = 7700227982720228504L;

    @ExplainAnnotation(explain = "群ID")
    private String gcRoomId;

    @ExplainAnnotation(explain = "群组头像")
    private String headUrl;

    @ExplainAnnotation(explain = "群组名称")
    private String name;

    @ExplainAnnotation(explain = "当前用户与该群状态",desc = "1:待审核 2:拒绝 3:已加入 4:退群")
    private Integer gcMemberStatus=0;

    @ExplainAnnotation(explain = "备注描述")
    private String remark;

    @ExplainAnnotation(explain = "群二维码")
    private String qrcodeUrl;

    @ExplainAnnotation(explain = "群所属社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "群所属社区名称")
    private String projectName;

    @ExplainAnnotation(explain = "群类型",desc = "1:兴趣群(支持2.6以下版本) 2:社区群 3:内部群(支持2.6以下版本) ,4:报名群（针对2.6版本及以上）,5:旅游业态")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGcMemberStatus() {
        return gcMemberStatus;
    }

    public void setGcMemberStatus(Integer gcMemberStatus) {
        this.gcMemberStatus = gcMemberStatus;
    }
}
