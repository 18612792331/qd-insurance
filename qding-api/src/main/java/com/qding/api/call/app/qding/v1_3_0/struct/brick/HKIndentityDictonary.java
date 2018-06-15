package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 身份
 * @author lichao
 *
 */
@XStreamAlias(value="hKIndentityDictonary")
public class HKIndentityDictonary extends Dictonary{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8417408284375986298L;

	private Integer[] validatyTimeKey;
	
	public HKIndentityDictonary() {

	}

	public HKIndentityDictonary(Integer[] validatyTimeKey) {
		super();
		this.validatyTimeKey = validatyTimeKey;
	}

	public Integer[] getValidatyTimeKey() {
		return validatyTimeKey;
	}

	public void setValidatyTimeKey(Integer[] validatyTimeKey) {
		this.validatyTimeKey = validatyTimeKey;
	}

	@Override
	public String toString() {
		return "HKIndentityDictonary [validatyTimeKey="
				+ Arrays.toString(validatyTimeKey) + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
