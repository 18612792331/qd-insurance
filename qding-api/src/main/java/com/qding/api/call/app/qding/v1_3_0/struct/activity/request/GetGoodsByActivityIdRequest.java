package com.qding.api.call.app.qding.v1_3_0.struct.activity.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetGoodsByActivityIdRequest  extends BaseRequest{

   
    private static final long serialVersionUID = -6173751489065389302L;
    
    
    public GetGoodsByActivityIdRequest(){
      
    }

    
    /**
     * 活动ID
     */
    @NotNullValidate
    private String activityId;

    /**
     * 社区ID
     */
    @NotNullValidate
    private Long projectId;

    @ExplainAnnotation(explain = "查询排序类型",desc = "1:最新上架时间,2:销量最好(总销量（实际销量+虚拟销量）desc, 实际销量 desc, 虚拟销量 desc),3:价格(销售价格 asc),4:价格(销售价格 desc)")
    private Integer sortType;

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return Returns the activityId.
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId The activityId to set.
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }


    @Override
    public String toString() {
        return "GetGoodsByActivityIdRequest{" +
                "activityId='" + activityId + '\'' +
                ", projectId=" + projectId +
                ", sortType=" + sortType +
                '}';
    }
}
