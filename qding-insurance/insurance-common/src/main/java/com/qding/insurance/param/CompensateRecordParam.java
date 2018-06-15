package com.qding.insurance.param;

public class CompensateRecordParam extends PageParam {

    private static final long serialVersionUID = -6889429607953779783L;

    /**
     * 理赔ID
     */
    private String compensateId;

    /**
     * 出险地点
     */
    private String roomAddress;

    /**
     * 理赔状态
     */
    private Integer compensateStatus;

    /**
     * 出险日期 - 开始
     */
    private String happenAtStartTime;

    /**
     * 出险日期 - 结束
     */
    private String happenAtEndTime;

    /**
     * 创建日期 - 开始
     */
    private String createAtStartTime;

    /**
     * 创建日期 - 结束
     */
    private String createAtEndTime;

    /**
     * 联系人姓名 电话
     */
    private String contactName;

    private String contactPhone;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 理赔性质
     */
    private String paidType;

    public String getCompensateId() {
        return compensateId;
    }

    public void setCompensateId(String compensateId) {
        this.compensateId = compensateId;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public Integer getCompensateStatus() {
        return compensateStatus;
    }

    public void setCompensateStatus(Integer compensateStatus) {
        this.compensateStatus = compensateStatus;
    }

    public String getHappenAtStartTime() {
        return happenAtStartTime;
    }

    public void setHappenAtStartTime(String happenAtStartTime) {
        this.happenAtStartTime = happenAtStartTime;
    }

    public String getHappenAtEndTime() {
        return happenAtEndTime;
    }

    public void setHappenAtEndTime(String happenAtEndTime) {
        this.happenAtEndTime = happenAtEndTime;
    }

    public String getCreateAtStartTime() {
        return createAtStartTime;
    }

    public void setCreateAtStartTime(String createAtStartTime) {
        this.createAtStartTime = createAtStartTime;
    }

    public String getCreateAtEndTime() {
        return createAtEndTime;
    }

    public void setCreateAtEndTime(String createAtEndTime) {
        this.createAtEndTime = createAtEndTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
