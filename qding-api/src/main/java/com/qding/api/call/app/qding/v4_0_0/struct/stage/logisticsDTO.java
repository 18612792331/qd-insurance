package com.qding.api.call.app.qding.v4_0_0.struct.stage;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author wang.cheng.liang create time 2018.5.8
 */
public class logisticsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3214911402477293545L;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "物流ID")
	private int id;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "物流名称")
	private String name;

}
