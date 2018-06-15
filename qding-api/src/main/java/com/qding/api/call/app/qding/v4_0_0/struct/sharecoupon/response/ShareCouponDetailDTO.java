package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by xuxiaoxing on 2018/5/3.
 */
public class ShareCouponDetailDTO implements Serializable {

    @ExplainAnnotation(explain = "会员id")
    private String memberId;

    @ExplainAnnotation(explain = "会员姓名")
    private String name;

    @ExplainAnnotation(explain = "会员头像")
    private String imageUrl;

    @ExplainAnnotation(explain = "会员手机号")
    private String mobile;

    @ExplainAnnotation(explain = "拼团身份", desc=" 1发起人，2参与人")
    private Integer role;

    @ExplainAnnotation(explain = "券信息")
    private CouponDTO couponDTO;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public CouponDTO getCouponDTO() {
        return couponDTO;
    }

    public void setCouponDTO(CouponDTO couponDTO) {
        this.couponDTO = couponDTO;
    }
}
