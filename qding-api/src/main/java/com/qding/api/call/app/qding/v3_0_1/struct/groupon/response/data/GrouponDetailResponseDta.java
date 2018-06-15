package com.qding.api.call.app.qding.v3_0_1.struct.groupon.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GrouponDetailDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by jinhaishan on 17/4/14.
 */
public class GrouponDetailResponseDta extends ResponseData{

    @ExplainAnnotation(explain = "团购详情")
    private GrouponDetailDto groupBuyingDetailDto;

    public GrouponDetailDto getGroupBuyingDetailDto() {
        return groupBuyingDetailDto;
    }

    public void setGroupBuyingDetailDto(GrouponDetailDto groupBuyingDetailDto) {
        this.groupBuyingDetailDto = groupBuyingDetailDto;
    }
}
