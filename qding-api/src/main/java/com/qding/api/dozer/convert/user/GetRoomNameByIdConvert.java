package com.qding.api.dozer.convert.user;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.dozer.CustomConverter;

public class GetRoomNameByIdConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        if (QDStringUtil.isNull(sourceFieldValue)) {
            return "";
        }
        String roomId = sourceFieldValue.toString();
        RoomReadRemote roomReadRemoteClient = ApplicationContextUtil.getBeansOfType(RoomReadRemote.class);
        Room room = null;
        if (StringUtils.isNotEmpty(roomId) && !roomId.equals("null")) {
            room = roomReadRemoteClient.get(Long.parseLong(roomId));
        }
        StringBuffer roomNameSb = new StringBuffer();

        if (room != null) {
            if (QDStringUtil.isNotEmpty(room.getGroupName())) {
                roomNameSb.append(room.getGroupName() + "-");
            }

            if (QDStringUtil.isNotEmpty(room.getBuildingName())) {
                if (QDStringUtil.isNotEmpty(room.getGroupName())) {
                    roomNameSb.append(room.getBuildingName() + "-");
                } else {
                    roomNameSb.append(room.getBuildingName());
                }
            }

            if (QDStringUtil.isNotEmpty(room.getName())) {
                roomNameSb.append("-" + room.getName());
            }
        }

        return roomNameSb.toString();
    }

}
