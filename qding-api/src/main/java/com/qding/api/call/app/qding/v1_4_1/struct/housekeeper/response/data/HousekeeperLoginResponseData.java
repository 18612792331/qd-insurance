package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.Puser;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class HousekeeperLoginResponseData extends ResponseData {

    private static final long serialVersionUID = 2742783681952760639L;

    private Puser entity;

    private List<Project>  list;

    public List<Project> getList() {
        return list;
    }

    public void setList(List<Project> list) {
        this.list = list;
    }

    public Puser getEntity() {
        return entity;
    }

    public void setEntity(Puser entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "HousekeeperLoginResponseData entity="+entity+" list="+list+",toString()="+super.toString()+" ]";
    }
}
