package com.qding.insurance.vo;

import java.util.List;

public class SpecVo {

    private String code;

    private String name;

    private List<SpecVo> children;

    private Integer isSelected = 0;
    
    

    public SpecVo(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public SpecVo() {
        super();  
    }

    public List<SpecVo> getChildren() {
        return children;
    }

    public void setChildren(List<SpecVo> children) {
        this.children = children;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

}
