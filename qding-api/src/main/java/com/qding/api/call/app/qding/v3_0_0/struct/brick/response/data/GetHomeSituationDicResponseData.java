package com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.brick.HomeSituationDicDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class GetHomeSituationDicResponseData extends ResponseData {

    private static final long serialVersionUID = -3048088629820103021L;

    @ExplainAnnotation(explain = "家庭状况字典")
    private List<HomeSituationDicDTO> list;

    public List<HomeSituationDicDTO> getList() {
        return list;
    }

    public void setList(List<HomeSituationDicDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetHomeSituationDicResponseData{" +
                "list=" + list +
                '}';
    }
}
