package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.qding.framework.common.basemodel.ResultPage;

/**
 * 前端接口调用后台返回的实体
 */
public class ModelResult<T> implements Serializable{
    
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_VALID_ERROR = 400;
    public static final Integer CODE_SYSTEM_ERROR = 500;

    private Integer code = CODE_SUCCESS;
    private String message;
    private Map<String,Object> data = Maps.newHashMap();
    
    
    public Integer getCode() {
        return code;
    }
    public List<T> getList() {
        return (List<T>)data.get("list");
    }
    public T getEntity() {
        return (T)data.get("entity");
    }
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setMessage(String message){
        this.message = message;
        data.put("message", message);
    }

    public void setList(List<T> list){
        data.put("list", list);
    }

    public void setEntity(T t){
        data.put("entity", t);
    }
    
    public ModelResult addObject(String attrName,Object obj){
        data.put(attrName,obj);
        return  this;
    }

    public ModelResult setResultPage(ResultPage resultPage){
        data.put("total",resultPage.getTotalCount());
        data.put("page",resultPage.getCurrentPage());
        data.put("size",resultPage.getPageSize());
        data.put("list",resultPage.getItems());
        return  this;
    }


}