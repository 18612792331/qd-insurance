package com.qding.api.call.service;

import com.alibaba.fastjson.JSON;
import com.qding.api.call.Callable;
import com.qding.api.constant.Constant;
import com.qding.api.util.SkipBean;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.neighbor.common.Constants;
import com.qding.solr.struct.serviceItem.ServiceItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * Created by qd on 2017/9/13.
 */
public class SkipModelService extends Callable {


    @Autowired
    private SkipModeFitting skipMode;

    private static final Logger logger = Logger.getLogger(SkipModelService.class);

    public String  fittingProjectServiceItemSkip(Project project, ServiceItem item, Map<String, String> skipModelMap ){

        String skipStr ="";
        if (QDStringUtil.isNotNull(item)) {
            SkipBean skipBean = new SkipBean();
            skipBean.setProjectName(project.getName());
            skipBean.setProjectId(String.valueOf(project.getId()));
            skipBean.setIds(String.valueOf(item.getId()));
            if (QDStringUtil.isNull(item.getSkipNo())) {
                logger.error("class :SkipModelService ,method:fittingProjectServiceItemSkip can't get skipNo, RPC item is"+ JSON.toJSONString(item));
                return skipStr;
            }
            skipBean.setSkipNo(Integer.parseInt(item.getSkipNo()));
            StringBuffer identity = new StringBuffer(Constant.DETAL_IDENTITY);
            if (QDStringUtil.isNotNull(item.getAccessPrivilege())){
                identity.setLength(0);
                identity.append(item.getAccessPrivilege());
            }
            skipBean.setIdentity(identity.toString());
            skipBean.setPcode( item.getPrivilegeType()==0?1:3);
            if (Integer.parseInt(item.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                skipBean.setSkipUrl(item.getContant());
                skipStr = skipMode.fittingSkipUrl(skipModelMap, skipBean);
            } else {
                skipStr = skipMode.fittingSkipModelBySkipBean(skipModelMap,skipBean);
            }
        }
        return  skipStr;

    }
    
    
    /**
     * @Description: 获取跟帖的标签，以及引用跳转模型
     * @param subTopicType 帖子子类型
     * @param skipModelMap 
     * @param tagId 标签id  如果subTopicType是邻里互动，那么这个不能为空，否则可以为空
     * @param pid 主贴id
     * @return
     */
    public String getSkipModel(int subTopicType, Map<String, String> skipModelMap,String tagId,String pid){
    	String skip=null;
    	if (subTopicType == Constants.SubTopicType.Neighborhood.getValue()) {
            //邻里活动标签列表页跳转
            skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), tagId);
            
        } else if (subTopicType == Constants.SubTopicType.TopicST.getValue() ||
        		subTopicType == Constants.SubTopicType.TopicTL.getValue()) {
            skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(), pid);
        } else if (subTopicType == Constants.SubTopicType.Enroll.getValue()) {
        	skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), pid);
        }else if (subTopicType == Constants.SubTopicType.Vote.getValue()) {
        	skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(), pid);
        }else if (subTopicType == Constants.SubTopicType.News.getValue()) {
        	skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(), pid);
        }else if (subTopicType == Constants.SubTopicType.Encyclopedia.getValue()) {
        	skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(), pid);
        }
    	return skip;
    	
    }
    
    
    
    
}
