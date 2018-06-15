package com.qding.api.verifycode.generator;

import java.util.Random;

public class DefaultGeneratorCode extends GeneratorCode{

	@Override
	public String getCode() {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < 4; i ++) {
			s.append(String.valueOf(new Random().nextInt(10)));
		}
		return s.toString();
//		return "1234";
	}

}
