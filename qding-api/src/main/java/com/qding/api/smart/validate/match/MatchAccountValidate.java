package com.qding.api.smart.validate.match;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.smart.validate.AccountMemberValidate;
import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;

/**
 * 验证账户是否可用
 * @author lichao
 *
 */
public class MatchAccountValidate extends AbstractMatchValidate<AccountValidate>{

	@Override
	public boolean validate(AccountValidate t, String fieldName, Object value)
			throws ServiceException {
		
		if(value == null || value.toString().trim().length() == 0) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少accountId");

		}
		
		IPassportService passportService = ApplicationContextUtil.getBeansOfType(IPassportService.class);
		
		GetAccountRequest request = new GetAccountRequest();
		
		request.setAccountId(value.toString());
		
		GetAccountResponse response = passportService.getAccountByAccountId(request);
		
		if(HttpStatus.OK.getStatusCode() == response.getReturnInfo().getCode()) {
			
			AccountInfo accountInfo = response.getAccountInfo();
			Account user = new Account();
			user.setStatus(accountInfo.getStatus());
			return AccountMemberValidate.validateAccount(user);
			
		} else {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), response.getReturnInfo().getMessage());

		}
	}
}
