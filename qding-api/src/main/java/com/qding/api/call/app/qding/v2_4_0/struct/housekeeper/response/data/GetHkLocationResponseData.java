package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.EmpLocationBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.HkLocationBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/6/8.
 */
public class GetHkLocationResponseData extends ResponseData {

    private static final long serialVersionUID = -1885306191276598015L;

    @ExplainAnnotation (explain = "是否支持地图模式",desc = "1:支持，0:不支持")
    private Integer isLocation =0;

    @ExplainAnnotation (explain = "管家地理位置")
    private List<HkLocationBean> hkLocationList;

    @ExplainAnnotation (explain = "员工地理信息")
    private List<EmpLocationBean>  empLocationList;

    public List<HkLocationBean> getHkLocationList() {
        return hkLocationList;
    }

    public void setHkLocationList(List<HkLocationBean> hkLocationList) {
        this.hkLocationList = hkLocationList;
    }

    public List<EmpLocationBean> getEmpLocationList() {
        return empLocationList;
    }

    public void setEmpLocationList(List<EmpLocationBean> empLocationList) {
        this.empLocationList = empLocationList;
    }

    public Integer getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(Integer isLocation) {
        this.isLocation = isLocation;
    }

    @Override
    public String toString() {
        return "GetHkLocationResponseData{" +
                "isLocation=" + isLocation +
                ", hkLocationList=" + hkLocationList +
                ", empLocationList=" + empLocationList +
                '}';
    }
}
