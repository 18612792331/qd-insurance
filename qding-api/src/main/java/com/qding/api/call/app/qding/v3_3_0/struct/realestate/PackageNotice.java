package com.qding.api.call.app.qding.v3_3_0.struct.realestate;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Create by jinhaishan on 17/12/6
 **/
public class PackageNotice implements Serializable {

    private static final long serialVersionUID = -4343296472344156047L;
    /**
     * 主键
     */
    @ExplainAnnotation(explain = "入住包裹通知ID")
    private Integer id;

    /**
     * 快递名称
     */
    @ExplainAnnotation(explain = "快递名称")
    private String packageName;

    /**
     *快递单号
     */
    @ExplainAnnotation(explain = "快递单号")
    private String packageNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    @Override
    public String toString() {
        return "PackageNoticeDto{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", packageNumber='" + packageNumber + '\'' +
                '}';
    }
}
