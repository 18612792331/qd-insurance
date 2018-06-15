package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftInfoDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/7/18.
 */
public class GetGiftPackListByMidResponseData extends ResponseData {

    private static final long serialVersionUID = 3145253419768481632L;


    @ExplainAnnotation(explain = "礼包列表")
    private List<GiftInfoDTO> list;

    @ExplainAnnotation (explain = "总数")
    private Integer totalCount;

    public List<GiftInfoDTO> getList() {
        return list;
    }

    public void setList(List<GiftInfoDTO> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
