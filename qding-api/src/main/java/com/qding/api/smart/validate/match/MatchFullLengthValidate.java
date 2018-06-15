package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.rule.FullLengthValidate;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;
import com.qding.framework.common.smart.validate.rule.MaxLengthValidate;

/**
 * 字符串最大长度
 * Null满足条件
 * @author lichao
 *
 */
public class MatchFullLengthValidate extends AbstractMatchValidate<FullLengthValidate> {

	@Override
	public boolean validate(FullLengthValidate t,
			String fieldName,
			Object value)
			throws ServiceException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的长度应该为%s位";

		if(value instanceof String) {
			
			int length = t.length();
			
			if(((String)value).length() > length || ((String)value).length() < length ) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(),
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "MatchFullLengthValidate only support String");
			
		}
		return true;
	}
	
}
