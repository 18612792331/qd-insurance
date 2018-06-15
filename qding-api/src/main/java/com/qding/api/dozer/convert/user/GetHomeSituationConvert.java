package com.qding.api.dozer.convert.user;

import com.qding.api.constant.Constant;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.thrift.TException;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2017/3/6.
 */
public class GetHomeSituationConvert  implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            String homeSituationIndex = String.valueOf(sourceFieldValue);
            try {
                String name =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_FAMILY_STATUS.getGroupName(),homeSituationIndex);
                return  name;
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        return  "";
    }
}
