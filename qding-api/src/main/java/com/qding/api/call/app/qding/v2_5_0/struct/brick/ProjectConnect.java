package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/11/22.
 */
public class ProjectConnect implements Serializable {

    private static final long serialVersionUID = 5405495042544785807L;

    @ExplainAnnotation (explain = "地址ID")
    private String id;

    @ExplainAnnotation (explain = "地址")
    private String address;

	@Override
	public String toString() {
		return "ProjectConnect [id=" + id + ", address=" + address + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    
    
    
}
