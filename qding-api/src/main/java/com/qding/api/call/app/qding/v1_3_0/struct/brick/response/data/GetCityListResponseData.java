package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.City;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.CitySpell;
import com.qding.api.struct.ResponseData;

/**
 * 获取城市列表		
 * @author lichao
 *
 */
public class GetCityListResponseData extends ResponseData{

	private static final long serialVersionUID = 8665631671272549569L;

	@ExplainAnnotation(explain = "城市列表")
	private List<CitySpell> list;

	@ExplainAnnotation(explain = "经GPS定位的城市")
	private City locateCity;

	public GetCityListResponseData() {

	}

	public GetCityListResponseData(List<CitySpell> list) {
		super();
		this.list = list;
	}

	public City getLocateCity() {
		return locateCity;
	}

	public void setLocateCity(City locateCity) {
		this.locateCity = locateCity;
	}

	public List<CitySpell> getList() {
		return list;
	}

	public void setList(List<CitySpell> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetCityListResponseData{" +
				"list=" + list +
				", locateCity=" + locateCity +
				'}';
	}
}
