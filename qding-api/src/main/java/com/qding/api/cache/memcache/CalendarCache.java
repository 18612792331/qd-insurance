package com.qding.api.cache.memcache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qding.api.call.app.qding.v3_0_0.struct.project.DateDTO;
import com.qding.framework.common.cache.MemCacheDataSource;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class CalendarCache {


    public static final String CALENDAR_CACHE = "calendar:s:%s:%s:%s";

    @Autowired
    private MemCacheDataSource xmemcacheDataSource;

    @Autowired
    private IProfileService profileAPI;

    private static Logger logger = Logger.getLogger(MemberStatusCache.class);


    private void putCalendar(String projectId,Integer year,Integer month,List<DateDTO> monthDateList) {
        String monthDateJsonArrayStr = JSON.toJSONString(monthDateList);
        String key = String.format(CALENDAR_CACHE, projectId,year,month);
        xmemcacheDataSource.set(key, monthDateJsonArrayStr,600);

    }


    public List<DateDTO> getCalendar(String projectId,Integer year,Integer month) {

        Object monthDateObj = null;
        try {
            String key = String.format(CALENDAR_CACHE, projectId,year,month);
            monthDateObj = xmemcacheDataSource.get(key);
            if (QDStringUtil.isNotNull(monthDateObj)){
                JSONArray jsonArray = JSON.parseArray(String.valueOf(monthDateObj));
                List<DateDTO> list = JSON.parseArray(monthDateObj.toString(),DateDTO.class);
            }

        } catch (Exception ex) {
        }

    /*    try {
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
        }*/
        return null;
    }
}
