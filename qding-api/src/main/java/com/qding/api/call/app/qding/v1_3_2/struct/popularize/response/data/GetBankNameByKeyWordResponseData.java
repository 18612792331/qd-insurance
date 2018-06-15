package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.BankDto;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.BankSpellDto;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public class GetBankNameByKeyWordResponseData  extends ResponseData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8011730201343296101L;

	/**
     * 满足条件的银行信息
     */
    private List<BankSpellDto> list;

    /**
     * 符合要求的银行总数
     */
    private int totalCount;

    public List<BankSpellDto> getList() {
        return list;
    }

    public void setList(List<BankSpellDto> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetBankNameByKeyWordResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }

}
