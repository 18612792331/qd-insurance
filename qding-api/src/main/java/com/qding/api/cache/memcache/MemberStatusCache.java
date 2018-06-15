package com.qding.api.cache.memcache;

import com.qding.framework.common.cache.MemCacheDataSource;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qd on 2016/12/8.
 */
public class MemberStatusCache {

    public static final String MEMBER_STATUS_CACHE = "mid:s:%s";

    @Autowired
    private MemCacheDataSource xmemcacheDataSource;

    @Autowired
    private IProfileService profileAPI;

    private static Logger logger = Logger.getLogger(MemberStatusCache.class);


    private void putMemberStatus(String memberId, Integer status) {
        String key = String.format(MEMBER_STATUS_CACHE, memberId);
        xmemcacheDataSource.setInteger(key, status,90);

    }


    public boolean getMemberIsHalt(String memberId) {

        Integer status = null;
        try {
            String key = String.format(MEMBER_STATUS_CACHE, memberId);
            status = xmemcacheDataSource.getInteger(key);
        } catch (Exception ex) {
            logger.error("从memcache缓存获取用户:" + memberId + "状态异常", ex);
        }

        try {
            if (QDStringUtil.isNull(status)) {
                GetMemberRequest memberRequest = new GetMemberRequest();
                memberRequest.setMemberId(memberId);
                GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
                if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {
                    MemberInfo memberInfo = memberResponse.getMemberInfo();
                    if (QDStringUtil.isNotNull(memberInfo)) {
                        putMemberStatus(memberInfo.getId(), memberInfo.getStatus());
                        status = memberInfo.getStatus();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("从RPC获取用户:" + memberId + "状态异常", ex);
        }

        if (QDStringUtil.isNotNull(status) && status == 0) {
            return true;
        } else {
            return false;
        }

    }


	public IProfileService getProfileAPI() {
		return profileAPI;
	}


	public void setProfileAPI(IProfileService profileAPI) {
		this.profileAPI = profileAPI;
	}
    
    
    
    
}
