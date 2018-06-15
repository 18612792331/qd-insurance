package com.qding.api.dozer.convert.neighbor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicImag;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.CustomConverter;

import java.util.*;

public class GetTopicImagListConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {

        List<TopicImag> list = Lists.newArrayList();
        List<String> imgList = Lists.newArrayList();
        if (sourceFieldValue != null) {
             try {
                 String imgStr = (String) sourceFieldValue;
                 JSONObject jsonObject = JSON.parseObject(imgStr);
                 JSONArray jsonArray = jsonObject.getJSONArray("images");
                 list = JSON.parseArray(jsonArray.toJSONString(), TopicImag.class);
                 if (CollectionUtils.isNotEmpty(list)){
                     if (list.size() == 1) {
                         return imgList.add("https://"+list.get(0).getUrl());
                     }

                     HashMap<Integer,String> map = new HashMap<>();
                     for (TopicImag topicImag : list) {
                         if(QDStringUtil.isNotEmpty(topicImag.getUrl()))
                         map.put(topicImag.getIndex(),topicImag.getUrl());
                     }

                     Map<Integer, String> resultMap = sortMapByKey(map);	//按Key进行排序
                     for (Map.Entry<Integer, String> entry : resultMap.entrySet()) {
                         imgList.add("https://"+entry.getValue());
                     }
                     resultMap =null;
                     map = null;
                 }

             }catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 return imgList;
             }

        }
        return imgList;
    }


    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public Map<Integer, String> sortMapByKey(Map<Integer, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<Integer, String> sortMap = new TreeMap<Integer, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    class MapKeyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer str1, Integer str2) {

            return str1.compareTo(str2);
        }
    }

}
