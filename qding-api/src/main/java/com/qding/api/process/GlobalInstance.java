package com.qding.api.process;

import com.qding.api.process.security.TransportSecurity;
import com.qding.api.process.security.UserTokenCallableSecurity;
import com.qding.api.process.version.strategy.NumberVersionCompareStrategy;
import com.qding.api.process.version.strategy.VersionCompareStrategy;

public class GlobalInstance {


	private static final UserTokenCallableSecurity userTokenCallableSecurity = new UserTokenCallableSecurity();
	
	private static final TransportSecurity transportSecurity = new TransportSecurity();
	
	private static final VersionCompareStrategy versionCompareStrategy = new NumberVersionCompareStrategy();
	
	public static TransportSecurity getTransportSecurity() {
		
		return transportSecurity;
	}
	
	public static UserTokenCallableSecurity getUserTokenCallableSecurity() {
		
		return userTokenCallableSecurity;
	}
	
	public static VersionCompareStrategy getVersioncomparestrategy() {
		return versionCompareStrategy;
	}
}
