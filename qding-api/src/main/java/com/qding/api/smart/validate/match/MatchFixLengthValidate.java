package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.rule.FixLengthValidate;
import com.qding.api.smart.validate.rule.FullLengthValidate;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;

/**
 * 字符串最大长度
 * Null满足条件
 * @author jwz
 *
 */
public class MatchFixLengthValidate extends AbstractMatchValidate<FixLengthValidate> {

	@Override
	public boolean validate(FixLengthValidate t,
			String fieldName,
			Object value)
			throws ServiceException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的长度应该为%s位或%s位";

		if(value instanceof String) {
			
			int maxLength = t.max();
			int minLength = t.min();
			
			if(((String)value).length() != maxLength && ((String)value).length() !=minLength ) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(),
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			}
			
		}
		else {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "FixLengthValidate only support String");
			
		}
		return true;
	}
	
}
