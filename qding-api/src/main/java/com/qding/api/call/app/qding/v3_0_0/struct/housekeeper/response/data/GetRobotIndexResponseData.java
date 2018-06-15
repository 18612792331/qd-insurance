package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.FrequentlyAskedQuestionDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.RobotServiceItem;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/8.
 */
public class GetRobotIndexResponseData extends ResponseData {

    private static final long serialVersionUID = -148567069718213101L;

    @ExplainAnnotation(explain = "常见问题")
    private FrequentlyAskedQuestionDTO entity;

    @ExplainAnnotation(explain = "常见服务列表")
    private List<RobotServiceItem> list;

    public List<RobotServiceItem> getList() {
        return list;
    }

    public void setList(List<RobotServiceItem> list) {
        this.list = list;
    }

    public FrequentlyAskedQuestionDTO getEntity() {
        return entity;
    }

    public void setEntity(FrequentlyAskedQuestionDTO entity) {
        this.entity = entity;
    }
}
