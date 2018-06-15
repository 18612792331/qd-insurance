package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.HKIndentityDictonary;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.HKPurposeDictonary;
import com.qding.api.struct.ResponseData;

/**
 * 获取app字典配置
 *
 * @author lichao
 */
public class GetDictonaryResponseData extends ResponseData {

    private static final long serialVersionUID = -4280715433209474930L;

    @ExplainAnnotation(explain = "性别")
    private List<Dictonary> gender;

    @ExplainAnnotation(explain = "手机系统类型")
    private List<Dictonary> mobileOSType;

    @ExplainAnnotation(explain = "注册来源")
    private List<Dictonary> regSourceType;

    @ExplainAnnotation(explain = "访客目的")
    private List<HKPurposeDictonary> hkPurepose;

    @ExplainAnnotation(explain = "住户身份")
    private List<HKIndentityDictonary> hkIndentity;

    @ExplainAnnotation(explain = "可通行时长", desc = "1:今天，2:明天，3:后天")
    private List<Dictonary> pastReleaseTime;

    @ExplainAnnotation(explain = "有效期时长")
    private List<Dictonary> validityTime;

    @ExplainAnnotation(explain = "短信验证码类型")
    private List<Dictonary> smsAction;

    @ExplainAnnotation(explain = "支付类型")
    private List<Dictonary> paymentType;

    @ExplainAnnotation(explain = "订单状态")
    private List<Dictonary> orderStatus;

    @ExplainAnnotation(explain = "支付状态")
    private List<Dictonary> paymentStatus;

    @ExplainAnnotation(explain = "支付方式", desc = "0:线下支付,1:线上支付")
    private List<Dictonary> orderIsPayOnline;

    @ExplainAnnotation(explain = "千丁手表角色类型")
    private List<Dictonary> watchRole;

    @ExplainAnnotation(explain = "报事评价标签", desc = "1:响应速度慢，2：仪容仪表不整 等")
    private List<Dictonary> evaluationLabel;

    @ExplainAnnotation(explain = "开卡保留时长", desc = "3.3版本新增")
    private Integer entranceCardTime;

    @ExplainAnnotation(explain = "appIcon资源位")
    private Integer appIconIndex = 0;

    @ExplainAnnotation(explain = "是否允许采集日志")
    private boolean collectLogSwitch;

    @ExplainAnnotation(explain = "乐购URL")
    private String newSellIndexURL;

    public String getNewSellIndexURL() {
        return newSellIndexURL;
    }

    public void setNewSellIndexURL(String newSellIndexURL) {
        this.newSellIndexURL = newSellIndexURL;
    }

    public Integer getEntranceCardTime() {
        return entranceCardTime;
    }

    public void setEntranceCardTime(Integer entranceCardTime) {
        this.entranceCardTime = entranceCardTime;
    }

    public List<Dictonary> getEvaluationLabel() {
        return evaluationLabel;
    }

    public void setEvaluationLabel(List<Dictonary> evaluationLabel) {
        this.evaluationLabel = evaluationLabel;
    }

    public GetDictonaryResponseData() {

    }

    public List<Dictonary> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<Dictonary> paymentType) {
        this.paymentType = paymentType;
    }

    public List<Dictonary> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(List<Dictonary> orderStatus) {
    }

    public List<Dictonary> getOrderIsPayOnline() {
        return orderIsPayOnline;
    }

    public void setOrderIsPayOnline(List<Dictonary> orderIsPayOnline) {
        this.orderIsPayOnline = orderIsPayOnline;
    }

    public List<Dictonary> getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(List<Dictonary> paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Dictonary> getGender() {
        return gender;
    }

    public void setGender(List<Dictonary> gender) {
        this.gender = gender;
    }

    public List<Dictonary> getMobileOSType() {
        return mobileOSType;
    }

    public void setMobileOSType(List<Dictonary> mobileOSType) {
        this.mobileOSType = mobileOSType;
    }

    public List<Dictonary> getRegSourceType() {
        return regSourceType;
    }

    public void setRegSourceType(List<Dictonary> regSourceType) {
        this.regSourceType = regSourceType;
    }

    public List<HKPurposeDictonary> getHkPurepose() {
        return hkPurepose;
    }

    public void setHkPurepose(List<HKPurposeDictonary> hkPurepose) {
        this.hkPurepose = hkPurepose;
    }

    public List<HKIndentityDictonary> getHkIndentity() {
        return hkIndentity;
    }

    public void setHkIndentity(List<HKIndentityDictonary> hkIndentity) {
        this.hkIndentity = hkIndentity;
    }

    public List<Dictonary> getPastReleaseTime() {
        return pastReleaseTime;
    }

    public void setPastReleaseTime(List<Dictonary> pastReleaseTime) {
        this.pastReleaseTime = pastReleaseTime;
    }

    public List<Dictonary> getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(List<Dictonary> validityTime) {
        this.validityTime = validityTime;
    }

    public List<Dictonary> getSmsAction() {
        return smsAction;
    }

    public void setSmsAction(List<Dictonary> smsAction) {
        this.smsAction = smsAction;
    }

    public List<Dictonary> getWatchRole() {
        return watchRole;
    }

    public void setWatchRole(List<Dictonary> watchRole) {
        this.watchRole = watchRole;
    }

    public Integer getAppIconIndex() {
        return appIconIndex;
    }

    public void setAppIconIndex(Integer appIconIndex) {
        this.appIconIndex = appIconIndex;
    }

    public boolean getCollectLogSwitch() {
        return collectLogSwitch;
    }

    public void setCollectLogSwitch(boolean collectLogSwitch) {
        this.collectLogSwitch = collectLogSwitch;
    }

    @Override
    public String toString() {
        return "GetDictonaryResponseData{" + "gender=" + gender + ", mobileOSType=" + mobileOSType + ", regSourceType="
                + regSourceType + ", hkPurepose=" + hkPurepose + ", hkIndentity=" + hkIndentity + ", pastReleaseTime="
                + pastReleaseTime + ", validityTime=" + validityTime + ", smsAction=" + smsAction + ", paymentType="
                + paymentType + ", orderStatus=" + orderStatus + ", paymentStatus=" + paymentStatus
                + ", orderIsPayOnline=" + orderIsPayOnline + ", watchRole=" + watchRole + ", evaluationLabel="
                + evaluationLabel + ", entranceCardTime=" + entranceCardTime + ", appIconIndex=" + appIconIndex
                + ", collectLogSwitch=" + collectLogSwitch + '}';
    }
}
