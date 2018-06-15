package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.HcChartlet;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/7/20.
 */
public class GetChartletResponseData  extends ResponseData {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4344166606715665183L;

	private List<HcChartlet> list;

    private Integer total;

	public List<HcChartlet> getList() {
		return list;
	}

	public void setList(List<HcChartlet> list) {
		this.list = list;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "GetChartletResponseData [total="
				+ total + ",list=" + list +", toString()=" + super.toString() + "]";
	}


}
