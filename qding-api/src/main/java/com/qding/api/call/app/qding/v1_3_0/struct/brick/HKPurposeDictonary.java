package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 访客目的
 * @author lichao
 *
 */
@XStreamAlias(value="hKPurposeDictonary")
public class HKPurposeDictonary extends Dictonary{

	private static final long serialVersionUID = -4681046963045058669L;
	
	private Integer releaseNum;
	
	public HKPurposeDictonary() {
	}

	public HKPurposeDictonary(Integer releaseNum) {
		super();
		this.releaseNum = releaseNum;
	}

	public Integer getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}

	@Override
	public String toString() {
		return "HKPurposeDictonary [releaseNum=" + releaseNum + ", toString()="
				+ super.toString() + "]";
	}

	
}
