package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.wallet.AccountWalletHome;
import com.qding.api.struct.ResponseData;

public class AccountWalletHomePageResonseData  extends ResponseData{
	

	private static final long serialVersionUID = -6429416728342515151L;
	
	private	 AccountWalletHome entity;

    /**
     * @return Returns the entity.
     */
    public AccountWalletHome getEntity() {
        return entity;
    }

    /**
     * @param entity The entity to set.
     */
    public void setEntity(AccountWalletHome entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "AccountWalletHomePageResonseData [entity=" + entity
                + ", toString()=" + super.toString() + "]";
    }
}
