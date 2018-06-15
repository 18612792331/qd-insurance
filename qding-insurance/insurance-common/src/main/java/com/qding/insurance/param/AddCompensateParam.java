package com.qding.insurance.param;

import com.qding.insurance.domain.CompensateRecord;

public class AddCompensateParam extends CompensateRecord {

    private static final long serialVersionUID = -3268516697811310021L;
    
    private String happenAtStr;
    
    public String getHappenAtStr() {
        return happenAtStr;
    }

    public void setHappenAtStr(String happenAtStr) {
        this.happenAtStr = happenAtStr;
    }

}
