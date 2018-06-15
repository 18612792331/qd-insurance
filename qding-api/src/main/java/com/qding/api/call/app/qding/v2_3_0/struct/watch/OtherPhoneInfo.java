package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * 亲情号码实体类
 * Created by qd on 2016/5/18.
 */
public class OtherPhoneInfo implements Serializable {

    @ExplainAnnotation(explain = "姓名")
    private String name = "";

    @ExplainAnnotation(explain = "手机号")
    private String phone = "";

    @ExplainAnnotation(explain = "顺序")
    private Integer sort = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "OtherPhone{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sort=" + sort +
                '}';
    }
}
