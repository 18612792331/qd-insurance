package com.qding.api.call.app.qding.v1_3_2.struct.im.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.im.BlackUserDto;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jiawenzheng on 2015/7/27.
 */
public class GetBlackUserResponse extends ResponseData {


    private List<BlackUserDto> users;

    public List<BlackUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<BlackUserDto> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GetBlackUserResponse [toString()=" + super.toString() + "]";
    }
}
