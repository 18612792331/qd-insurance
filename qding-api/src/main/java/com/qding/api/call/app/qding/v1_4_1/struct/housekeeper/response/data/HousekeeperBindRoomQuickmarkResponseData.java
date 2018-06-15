package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/27.
 */
public class HousekeeperBindRoomQuickmarkResponseData extends ResponseData {

    private static final long serialVersionUID = -5435537308725148238L;

    private String quickMarkUrl;

    public String getQuickMarkUrl() {
        return quickMarkUrl;
    }

    public void setQuickMarkUrl(String quickMarkUrl) {
        this.quickMarkUrl = quickMarkUrl;
    }

    @Override
    public String toString() {
        return "HousekeeperBindRoomQuickmarkResponseData [quickMarkUrl="+quickMarkUrl+",toString()="+super.toString()+" ]";
    }

}
