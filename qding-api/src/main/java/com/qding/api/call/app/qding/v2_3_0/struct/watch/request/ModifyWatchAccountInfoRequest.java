package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.smart.validate.ValidateBean;
import com.smart.validate.rule.NotNullValidate;

@ValidateBean
public class ModifyWatchAccountInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 4162413891394174146L;

    @NotNullValidate
    @ExplainAnnotation(explain = "手表设备码")
    private String imei = "";

    @NotNullValidate
    @ExplainAnnotation(explain = "手表昵称")
    private String nickName = "";

    @NotNullValidate
    @ExplainAnnotation(explain = "手表头像")
    private String headImage = "";

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "ModifyWatchAccountInfoRequest{" +
                "imei='" + imei + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
