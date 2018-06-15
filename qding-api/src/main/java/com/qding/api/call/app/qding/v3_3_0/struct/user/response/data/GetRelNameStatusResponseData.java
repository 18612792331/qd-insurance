package com.qding.api.call.app.qding.v3_3_0.struct.user.response.data;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Create by jinhaishan on 18/5/30
 **/
public class GetRelNameStatusResponseData extends ResponseData {

    private static final long serialVersionUID = 6500584933813103856L;

    @ExplainAnnotation(explain = "实名认证状态",desc = "U:未实名 W:认 证中 S:认证成功 F:认证失败 网银 打款认证,需 REAL_NM_FLG 为实 名")
    private String relNameStatus;

    @ExplainAnnotation(explain = " 会员账户状态",desc = " 1：冻结 0：正常")
    private String actSts = "";

    public String getRelNameStatus() {
        return relNameStatus;
    }

    public void setRelNameStatus(String relNameStatus) {
        this.relNameStatus = relNameStatus;
    }

    public String getActSts() {
        return actSts;
    }

    public void setActSts(String actSts) {
        this.actSts = actSts;
    }

    @Override
    public String toString() {
        return "GetRelNameStatusResponseData{" +
                "relNameStatus='" + relNameStatus + '\'' +
                ", actSts='" + actSts + '\'' +
                '}';
    }
}
