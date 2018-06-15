package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper;

import java.io.Serializable;

/**
 * Created by qd on 2015/9/17.
 */
public class OwnerInfo implements Serializable {

    private static final long serialVersionUID = 6635484720501561099L;

    private String name;

    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
