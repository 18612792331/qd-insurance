package com.qding.api.dozer.convert.user;

import org.dozer.CustomConverter;
import org.dozer.Mapper;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;

public class GetAccountInfoByAccountIdConvert implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		String accountId = sourceFieldValue.toString();
		IPassportService passportAPI = ApplicationContextUtil.getBeansOfType(IPassportService.class);
		GetAccountRequest  request =  new GetAccountRequest ();
		request.setAccountId(accountId);
		GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(request);
		
		AccountInfo accountInfo = accountResponse.getAccountInfo();
		
		if(accountInfo == null) {
			return null;
		}
		
		Mapper mapper = ApplicationContextUtil.getBeansOfType(Mapper.class);
		
		Account user = mapper.map(accountInfo, Account.class);
		
		return user;
	}

}
