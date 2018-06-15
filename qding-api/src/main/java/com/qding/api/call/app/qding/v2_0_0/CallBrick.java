package com.qding.api.call.app.qding.v2_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.request.GetRoomByBuildingIdRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data.GetRoomByBuildingIdResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.request.GetOnlineCustomerServiceStatusRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.response.data.GetOnlineCustomerServiceStatusResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.RoomRequest;
import com.qding.brick.struts.response.RoomReadResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.newcore.common.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CallBrick extends com.qding.api.call.app.qding.v1_3_1.CallBrick {


    @Autowired
    private RoomReadRemote roomReadRemoteService;


    /**
     * 根据楼栋id获取房间列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getRooms")
    @ExplainAnnotation(explain = "根据楼栋id获取房间列表")
    public Response<GetRoomByBuildingIdResponseData> getRooms(GetRoomByBuildingIdRequest request) {

        GetRoomByBuildingIdResponseData data = new GetRoomByBuildingIdResponseData();
        Response<GetRoomByBuildingIdResponseData> response = new Response<GetRoomByBuildingIdResponseData>();
        List<Room> rooms = Lists.newArrayList();

        if (0 != request.getPageSize()) {
            try {
                RoomRequest roomRequest = new RoomRequest();
                Set<Long> buildingSet = new HashSet<>();
                buildingSet.add(Long.parseLong(request.getBuildingId()));
                if (StringUtils.isNotEmpty(request.getRoomNum())) {
                    Set<String> roomNameSet = new HashSet<>();
                    roomNameSet.add(request.getRoomNum());
                    roomRequest.setNameSet(roomNameSet);
                }
                roomRequest.setBuildingIdSet(buildingSet);
                roomRequest.setPage(request.getPageNo());
                roomRequest.setSize(request.getPageSize());
                RoomReadResponse roomPageResponse = roomReadRemoteService.getRoomsByRequest(roomRequest);
                checkAndContinue(roomPageResponse);
                rooms = roomPageResponse.getRooms();
                data.setTotalCount(roomPageResponse.getTotalCount());
            } catch (Exception e) {
                return handleException(GetRoomByBuildingIdResponseData.class, e);
            }

        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("buildingId", request.getBuildingId());
            map.put("status",1);
            if (StringUtils.isNotEmpty(request.getRoomNum())) {
                map.put("code_like", request.getRoomNum());
            }
            rooms = roomReadRemoteService.gets(map);//.getRoomByBuildingId(Long.parseLong(request.getBuildingId()));
            data.setTotalCount(rooms.size());
        }

        List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> rs =
                transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, rooms);
        data.setList(rs);
        response.setData(data);

        return response;
    }


    /**
     * 获取在线客服状态
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOnlineCustomerServiceStatus")
    @ExplainAnnotation(explain = "获取在线客服状态", desc = "1:开启，0：关闭")
    public Response<GetOnlineCustomerServiceStatusResponseData> getOnlineCustomerServiceStatus(GetOnlineCustomerServiceStatusRequest request) {

        Response<GetOnlineCustomerServiceStatusResponseData> response = new Response<GetOnlineCustomerServiceStatusResponseData>();

        try {

            GetOnlineCustomerServiceStatusResponseData data = new GetOnlineCustomerServiceStatusResponseData();
            data.setStatus(0);
            String status = DictionaryClient.getClient().findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_ONLINE_CUSTOMER_SERVICE.getGroupName(), Constant.Dict_K_V_Enum.DICT_ONLINE_CUSTOMER_SERVICE.getDictKey());
            if ("open".equals(status.toLowerCase())) {
                data.setStatus(1);
            }

            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);

            return response;

        } catch (TException e) {
            return handleException(GetOnlineCustomerServiceStatusResponseData.class, e);
        }

    }


}
