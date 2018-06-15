package com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_1.struct.brick.ProjectService;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/9/17.
 */
public class GetProjectPropertyServiceByProjectIdResponseData extends ResponseData {


    private static final long serialVersionUID = 1226500921347131797L;

    private List<ProjectService> list;

    public List<ProjectService> getList() {
        return list;
    }

    public void setList(List<ProjectService> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GetServiceItemsByProjectIdResponseData [list=" + list
                + ", toString()=" + super.toString() + "]";
    }
}
