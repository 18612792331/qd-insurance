package com.qding.insurance.param;

public class InsuranceWareParam extends PageParam {

    private static final long serialVersionUID = -1937316579361485459L;

    private String cityId; // 城市ID
    
    private String projectId; // 社区ID
    
    private String wareId; // 商品ID
     
    private String wareTitle; // 商品标题
     
    private String skuId; // 货品Id
    
    private String wareStatus; // 商品状态，1：未审核， 2：审核中， 3：审核未通过， 4：审核通过， 5：已上架， 6：已下架
    
    private String objectType; // 投保对象，1：房屋
    
    private String providerId; // 供方ID
    
    private String categoryId; // 所属品类ID
    
    private String updateAtBegin; // 更新时间开始，格式 yyyy-MM-dd HH:mm:ss
    
    private String updateAtEnd; // 更新时间结束 

    
    public String getCityId() {
        return cityId;
    }

    
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    
    public String getProjectId() {
        return projectId;
    }

    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    
    public String getWareId() {
        return wareId;
    }

    
    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    
    public String getWareTitle() {
        return wareTitle;
    }

    
    public void setWareTitle(String wareTitle) {
        this.wareTitle = wareTitle;
    }

    
    public String getSkuId() {
        return skuId;
    }

    
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    
    public String getWareStatus() {
        return wareStatus;
    }

    
    public void setWareStatus(String wareStatus) {
        this.wareStatus = wareStatus;
    }

    
    public String getObjectType() {
        return objectType;
    }

    
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    
    public String getProviderId() {
        return providerId;
    }

    
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    
    public String getCategoryId() {
        return categoryId;
    }

    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    
    public String getUpdateAtBegin() {
        return updateAtBegin;
    }

    
    public void setUpdateAtBegin(String updateAtBegin) {
        this.updateAtBegin = updateAtBegin;
    }

    
    public String getUpdateAtEnd() {
        return updateAtEnd;
    }

    
    public void setUpdateAtEnd(String updateAtEnd) {
        this.updateAtEnd = updateAtEnd;
    }
    
    
}
