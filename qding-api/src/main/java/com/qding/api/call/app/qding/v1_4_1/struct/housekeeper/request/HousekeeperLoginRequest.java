package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class HousekeeperLoginRequest extends BaseRequest {

    private static final long serialVersionUID = -9146878609700894959L;

    @NotNullValidate
    private String account;

    @NotNullValidate
    private String passWord;

    private String verifyCode;

    private String verifyKey;

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
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
        return "HousekeeperLoginRequest [account=" + account +",passWord="+passWord+"," +
                " verifyCode="+verifyCode+",verifyKey="+verifyKey+",toString = "+super.toString()+" ]";
    }

}
