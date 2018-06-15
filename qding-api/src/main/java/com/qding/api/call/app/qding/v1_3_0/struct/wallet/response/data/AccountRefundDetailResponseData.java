package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.wallet.MoneyDetail;
import com.qding.api.struct.ResponseData;

public class AccountRefundDetailResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3961963227619513308L;
	
	/**
	 * 记录总数
	 */
	private Integer totalCount;
	
	/**
	 * 退款列表
	 */
	private List<MoneyDetail> list;
	
    /**
     * @return Returns the totalCount.
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount The totalCount to set.
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return Returns the list.
     */
    public List<MoneyDetail> getList() {
        return list;
    }

    /**
     * @param list The list to set.
     */
    public void setList(List<MoneyDetail> list) {
        this.list = list;
    }
	
    @Override
    public String toString() {
        return "AccountRefundDetailResponseData [list=" + list
                + ", totalCount=" + totalCount + ", toString()=" + super.toString() + "]";
    }

}
