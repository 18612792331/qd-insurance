package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class HousekeeperForgetPasswordRequest extends BaseRequest {

    private static final long serialVersionUID = -3283009237249799092L;

    @NotNullValidate
    private String account;

    @NotNullValidate
    private String passWord;

    @NotNullValidate
    private String verifyCode;

    @NotNullValidate
    private String mobile;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "HousekeeperForgetPasswordRequest [account=" + account +",passWord="+passWord+"," +
                " verifyCode="+verifyCode+",super.toString() ]";
    }

}
