package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.smart.validate.rule.WalletPayPasswordRuleValidate;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;

/**
 * 验证支付密码规则
 * @author lichao
 *
 */
public class MatchWalletPayPasswordRuleValidate extends AbstractMatchValidate<WalletPayPasswordRuleValidate>{

	@Override
	public boolean validate(WalletPayPasswordRuleValidate t, String fieldName, Object value)
			throws ServiceException {
		
		if(value == null || value.toString().trim().length() == 0) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请输入密码");

		}
		
		return WalletValidate.validateWallPayPasswordRule(value.toString());
		
	}
	
}

