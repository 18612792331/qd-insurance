package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftSkinDTO;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetGiftRemindResponseData extends ResponseData {

    private static final long serialVersionUID = 4024476972850711128L;

    @ExplainAnnotation (explain = "是否显示礼包",desc = "0:不显示,1:显示")
    private Integer showFlag;

    @ExplainAnnotation (explain = "是否还有礼包未认领",desc = "0:无,1:有")
    private Integer moreFlag = 0;

    @ExplainAnnotation (explain = "礼包皮肤信息")
    private GiftSkinDTO giftSkinDTO;


    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public GiftSkinDTO getGiftSkinDTO() {
        return giftSkinDTO;
    }

    public void setGiftSkinDTO(GiftSkinDTO giftSkinDTO) {
        this.giftSkinDTO = giftSkinDTO;
    }

    public Integer getMoreFlag() {
        return moreFlag;
    }

    public void setMoreFlag(Integer moreFlag) {
        this.moreFlag = moreFlag;
    }

    @Override
    public String toString() {
        return "GetGiftRemindResponseData{" +
                "showFlag=" + showFlag +
                ", moreFlag=" + moreFlag +
                ", giftSkinDTO=" + giftSkinDTO +
                '}';
    }
}
