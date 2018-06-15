package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;

/**
 * 验证用户钱包是否可用
 * @author lichao
 *
 */
public class MatchWalletStatusValidate extends AbstractMatchValidate<WalletStatusValidate>{

	@Override
	public boolean validate(WalletStatusValidate t, String fieldName, Object value)
			throws ServiceException {
		
		if(value == null || value.toString().trim().length() == 0) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少memberId");

		}
		
		String memberId = value.toString();
		
		return WalletValidate.validateWalletStatus(memberId);
	}
}
