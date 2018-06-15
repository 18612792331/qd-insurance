package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;

/**
 * Created by qd on 2015/9/1.
 */
public class GoodProjectAndCity implements Serializable {

    private static final long serialVersionUID = -6729310811713204257L;

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     *  城市名称
     */
    private String cityName;

    /**
     * 社区名称
     */
    private String name;

    /**
     * 社区ID
     */
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
