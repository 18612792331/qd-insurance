package com.qding.insurance.domain;

import java.io.Serializable;

public class SpecGroup implements Serializable{
    private static final long serialVersionUID = 2808619887990962065L;

    private Integer id;

    private String code;

    private String name;

    private Integer sort;

    private Integer isSelect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }
}