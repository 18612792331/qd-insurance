package com.qding.api.smart.validate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.qding.api.constant.Constant;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.service.IRUserPayPasswordService;
import com.qding.useraccount.struct.UserPayPasswordInfo;
import com.qding.useraccount.struct.request.UserPayPwdRequest;
import com.qding.useraccount.struct.response.UserPayPwdResponse;

/**
 * 验证用户钱包相关方法
 * @author lichao
 *
 */
public class WalletValidate {

	private static final String WALLET_STATUS_FORZEN_TIPS = "您的账户于%s被冻结，被冻结后3个小时，系统自动解除冻结。";
	
	
	/**
	 * 验证钱包是否冻结
	 * @param memberId
	 * @return
	 * @throws ServiceException
	 */
	public static boolean validateWalletStatus(String memberId) throws ServiceException {
		
		UserPayPasswordInfo payPassword = getWalletPayPassword(memberId);

		int walletStatus = getWalletStatus(payPassword);
		
		if(walletStatus == Constant.WALLET_STATUS_FORZEN) {
			
			throw new ServiceException(
					HttpStatus.FORBIDDEN.getStatusCode(),
					getWalletStatusForzenTips(payPassword));
		}
		return true;
	}
	
	/**
	 * 验证钱包错误支付次数
	 * @param memberId
	 * @return
	 * @throws ServiceException
	 */
	public static boolean validateLeftTimes(String memberId) throws ServiceException {
		
		UserPayPasswordInfo walletPayPassword = getWalletPayPassword(memberId);
		
		Integer wrongNumber = walletPayPassword.getWrongNumber();
		
		if(wrongNumber == 1) {
			
			throw new ServiceException(
					HttpStatus.BAD_REQUEST.getStatusCode(),
					"密码错误，请重新输入（您还有2次机会）");
		}
		else if(wrongNumber == 2) {
			throw new ServiceException(
					HttpStatus.BAD_REQUEST.getStatusCode(),
					"密码错误，您还有1次输入机会，届时账户将被暂时冻结");
		}
		else if(wrongNumber >= 3) {
			throw new ServiceException(
					HttpStatus.FORBIDDEN.getStatusCode(),
					getWalletStatusForzenTips(walletPayPassword));
		}
		
		return true;
	}
	
	
	/**
	 *  获取钱包状态
	 * @param payPassword
	 * @return
	 */
	public static int getWalletStatus(UserPayPasswordInfo payPassword) {
		
		if(payPassword == null) {
    		return Constant.WALLET_STATUS_NOTSET;
    	}
    	
    	if(Constant.WALLET_STATUS_FORZEN == payPassword.getStatus()) {
    		return Constant.WALLET_STATUS_FORZEN;
    	}
    	
    	if(Constant.WALLET_STATUS_NORMAL == payPassword.getStatus()) {
    		return Constant.WALLET_STATUS_NORMAL;
    	}
    	
    	return Constant.WALLET_STATUS_NORMAL;
    	
	}
	
	/**
	 * 获取钱包状态
	 * @param memberId
	 * @return
	 */
	public static int getWalletStatus(String memberId) {
	   
		UserPayPasswordInfo payPassword = getWalletPayPassword(memberId);
    	
    	return getWalletStatus(payPassword);
    }
	

	/**
	 * 冻结提示信息
	 * @param payPassword
	 * @return
	 */
	public static String getWalletStatusForzenTips(UserPayPasswordInfo payPassword) {
		
		if(payPassword == null) {
			return null;
		}
		
		return String.format(WALLET_STATUS_FORZEN_TIPS, 
				new SimpleDateFormat("MM月dd日 HH:mm").format(
						new Date(payPassword.getEffectAt())
			));
		
	}
	
	/**
	 * 验证密码有效性
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	public static boolean validateWallPayPasswordRule(String password) throws ServiceException {
		
		if(QDStringUtil.isEmpty(password)) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请输入密码");

		}

		//6位数字
		if(!Pattern.matches("\\d{6}", password)) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "支付密码必须为6位数字");

		}
		
		char[] arrays = password.toCharArray();
		
		if(!checkRepeatTimes(arrays, 4)) {
			
			throw new ServiceException(
					HttpStatus.BAD_REQUEST.getStatusCode(),
					"输入的支付密码不能包含有连续4位及以上的顺序（或逆序）数字");

		}
		
		if(!checkPositiveContinuousTimes(arrays, 4)) {
			
			throw new ServiceException(
					HttpStatus.BAD_REQUEST.getStatusCode(),
					"输入的支付密码不能包含有连续4位及以上的顺序（或逆序）数字");

		}
		
		if(!checkReverseContinuousTimes(arrays, 4)) {
			
			throw new ServiceException(
					HttpStatus.BAD_REQUEST.getStatusCode(),
					"输入的支付密码不能包含有连续4位及以上的顺序（或逆序）数字");
		}
		
		return true;
	}

	
	
	/**
	 * 不能包含有连续4位及以上的相同数字
	 * @param arrays
	 * @throws ServiceException
	 */
	private static boolean checkRepeatTimes(char[] arrays, int limit) {
		int repeatTimes = 1;
		
		int length = arrays.length;
		
		for(int i = 1; i < length; i ++) {
			
			if(arrays[i - 1] == arrays[i]) {
				
				repeatTimes ++;
				
				if(repeatTimes >= limit) {
					
					return false;

				}
			} else {
				
				repeatTimes = 1;
			}
			
		}
		
		return true;
	}
	
	/**
	 * 不能包含有连续4位及以上的顺序数字
	 * @param arrays
	 * @throws ServiceException
	 */
	private static boolean checkPositiveContinuousTimes(char[] arrays, int limit) {
		
		int continuousTimes = 1;
		
		int length = arrays.length;
		
		for(int i = 1; i < length; i ++) {
			
			Integer prev = Integer.valueOf(arrays[i - 1]);
			Integer cur = Integer.valueOf(arrays[i]);
			
			if(prev + 1 == cur) {
				
				continuousTimes ++;
				
				if(continuousTimes >= limit) {
					
					return false;

				}
				
			}
			else { 
				
				continuousTimes = 1;
				
			}
			
		}
		return true;
	}

	/**
	 * 不能包含有连续4位及以上的逆序数字
	 * @param arrays
	 * @throws ServiceException
	 */
	private static boolean checkReverseContinuousTimes(char[] arrays, int limit) {
		
		int continuousTimes = 1;
		
		int length = arrays.length;
		
		for(int i = length - 1; i > 0; i --) {
			
			Integer prev = Integer.valueOf(arrays[i - 1]);
			Integer cur = Integer.valueOf(arrays[i]);
			
			if(prev - 1 == cur) {
				
				continuousTimes ++;
				
				if(continuousTimes >= limit) {
					
					return false;
				}
				
			}
			else { 
				
				continuousTimes = 1;
				
			}
			
		}
		return true;
	}
	
	public static UserPayPasswordInfo getWalletPayPassword(String memberId) {
		IRUserPayPasswordService userPayPasswordService = ApplicationContextUtil.getBeansOfType(IRUserPayPasswordService.class);
		 
    	UserPayPwdResponse getPayPassword = userPayPasswordService.getByMember(
					new UserPayPwdRequest(memberId)
		);
    	
    	UserPayPasswordInfo payPassword = getPayPassword.getUserPayPasswordInfo();
		return payPassword;
	}
}
