package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.wallet.BalanceDetail;
import com.qding.api.struct.ResponseData;

public class AccountBalanceDetailResponseData extends ResponseData {

	
	private static final long serialVersionUID = 282431692434796000L;
	
    
	/**
	 * 总记录数
	 */
    private int totalCount;
    
    /**
     * 余额信息列表
     */
	private List<BalanceDetail> list;

    /**
     * @return Returns the totalCount.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount The totalCount to set.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return Returns the list.
     */
    public List<BalanceDetail> getList() {
        return list;
    }

    /**
     * @param list The list to set.
     */
    public void setList(List<BalanceDetail> list) {
        this.list = list;
    }
	
	
    @Override
    public String toString() {
        return "AccountBalanceDetailResponseData [list=" + list
                + ", totalCount=" + totalCount + ", toString()=" + super.toString() + "]";
    }

}
