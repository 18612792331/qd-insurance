package com.qding.api.call.app.qding.v3_2_0.struct.task.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.task.SignInPrizeInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jinhaishan on 17/7/24.
 */
public class GetSignInInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 243445567283505672L;

    @ExplainAnnotation(explain = "当月签到天数")
    private Integer signInCount;

    @ExplainAnnotation(explain = "当月签到日")
    private List<Integer> signInDays;

    @ExplainAnnotation(explain = "当月商品列表")
    private List<SignInPrizeInfo> signInPrizeInfos;

    public List<SignInPrizeInfo> getSignInPrizeInfos() {
        return signInPrizeInfos;
    }

    public void setSignInPrizeInfos(List<SignInPrizeInfo> signInPrizeInfos) {
        this.signInPrizeInfos = signInPrizeInfos;
    }

    public Integer getSignInCount() {
        return signInCount;
    }

    public void setSignInCount(Integer signInCount) {
        this.signInCount = signInCount;
    }

    public List<Integer> getSignInDays() {
        return signInDays;
    }

    public void setSignInDays(List<Integer> signInDays) {
        this.signInDays = signInDays;
    }
}
