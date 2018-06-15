package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.EntranceCardDto;
import com.qding.api.struct.ResponseData;

public class ListResponseData extends ResponseData {

    private static final long serialVersionUID = -5337941266330777488L;

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    @ExplainAnnotation(explain = "用户已绑定门禁卡列表")
    private List<EntranceCardDto> list;

    public List<EntranceCardDto> getList() {
        return list;
    }

    public void setList(List<EntranceCardDto> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
