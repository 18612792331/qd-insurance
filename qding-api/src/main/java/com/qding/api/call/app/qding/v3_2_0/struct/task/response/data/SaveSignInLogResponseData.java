package com.qding.api.call.app.qding.v3_2_0.struct.task.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.task.SignInPrizeInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jinhaishan on 17/7/24.
 */
public class SaveSignInLogResponseData extends ResponseData {

    @ExplainAnnotation(explain = "当月商品列表")
    private List<SignInPrizeInfo> signInPrizeInfos;

    @ExplainAnnotation(explain = "签到送的积分数", desc = "整型字符串, 如果为0，说明今日已签过到，不送积分")
    private String signInPoint;

    public List<SignInPrizeInfo> getSignInPrizeInfos() {
        return signInPrizeInfos;
    }

    public void setSignInPrizeInfos(List<SignInPrizeInfo> signInPrizeInfos) {
        this.signInPrizeInfos = signInPrizeInfos;
    }

    public String getSignInPoint() {
        return signInPoint;
    }

    public void setSignInPoint(String signInPoint) {
        this.signInPoint = signInPoint;
    }
}
