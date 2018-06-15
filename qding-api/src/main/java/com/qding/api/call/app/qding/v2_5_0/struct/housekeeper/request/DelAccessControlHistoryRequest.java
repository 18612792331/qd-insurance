package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/11/18.
 */
@Validate
public class DelAccessControlHistoryRequest extends BaseRequest {

    private static final long serialVersionUID = 4781380533651932060L;

    @ExplainAnnotation (explain = "记录ID",desc = "不传递id的情况，默认为全部删除")
    private String id;
    
    @ExplainAnnotation (explain = "账号id",desc = "账号id")
    @NotNullValidate
    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "DelAccessControlHistoryRequest [id=" + id + ", accountId=" + accountId + "]";
	}
	
	
	 
}
