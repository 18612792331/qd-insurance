package com.qding.api.util;

import com.qding.api.cache.redis.ApiSkipConfigCache;
import com.qding.api.constant.Constant;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.sysconfig.dto.ApiSkipDto;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by qd on 2016/1/4.
 */
public class SkipModeFitting {

    @Autowired
    private ApiSkipConfigCache apiSkipConfigCache;

    private static final Logger logger = Logger.getLogger("SkipModeFitting");

    public Map<String, String> selSkipTemplateMap(String version) {

        version = initVersion(version);
        ApiSkipDto apiSkipDto = new ApiSkipDto();
        apiSkipDto.setScopeType(1);
        apiSkipDto.setVersion(Integer.parseInt(version));
        Map<String, String> apiSkipTemplateMap = apiSkipConfigCache.getSkipAll(apiSkipDto);

        return apiSkipTemplateMap;

    }

    public String selSkipTemplate(String version, Integer skipNo) {

        try {
            version = initVersion(version);
            ApiSkipDto apiSkipDto = new ApiSkipDto();
            apiSkipDto.setScopeType(1);
            apiSkipDto.setSkipNo(skipNo);
            apiSkipDto.setVersion(Integer.parseInt(version));
            String apiSkipTemplate = apiSkipConfigCache.getByCode(apiSkipDto);
            return apiSkipTemplate;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("apiSkipTemplate==================>error");
        return "";
    }

    /**
     * 初始化版本信息符合缓存要求
     *
     * @param version
     * @return
     */
    public String initVersion(String version) {
        version = version.replace(".", "");
        version = String.format("%-6s", version).replace(' ', '0');
        return version;
    }


    /**
     * 合成url类型跳转模型
     *
     * @param
     * @param url
     * @return
     */
    public String fittingSkipUrl(Map<String, String> skipConfigMap, String url, Integer pCode, Integer isShare,String id) {

        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotEmpty(url)) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(Constant.SkipNo.URL_7000.toInteger()));
            if (QDStringUtil.isNotEmpty(apiSkipTemplate)) {
                SkipBean skipBean = new SkipBean();
                skipBean.setSkipUrl(url);
                skipBean.setSkipNo(Constant.SkipNo.URL_7000.toInteger());
                skipBean.setSkipTemplate(apiSkipTemplate);
                skipBean.setPcode(pCode);
                skipBean.setShare(isShare);
                skipBean.setIds(id);
                return getVelocityTemplate(skipBean);
            }
        }
        return "";
    }

    public String fittingSkipUrl(Map<String, String> skipConfigMap, String url, Integer pCode, Integer isShare,
                                 String shareTitle, String shareText, String imageUrl, String shareSkipUrl, String version,String id) {
        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotEmpty(url)) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(Constant.SkipNo.URL_7000.toInteger()));
            if (1 == isShare /*&& version.compareTo("2.5.0") >= 0*/) {
                apiSkipTemplate = skipConfigMap.get(String.valueOf(Constant.SkipNo.URL_7001.toInteger()));
            }
            if (QDStringUtil.isNotEmpty(apiSkipTemplate)) {
                SkipBean skipBean = new SkipBean();
                skipBean.setSkipUrl(url);
                skipBean.setSkipNo(Constant.SkipNo.URL_7000.toInteger());
                skipBean.setSkipTemplate(apiSkipTemplate);
                skipBean.setPcode(pCode);
                skipBean.setShare(isShare);
                skipBean.setIds(id);
                if (1 == isShare) {//是否支持app分享 0否 1是
                    skipBean.setSkipNo(Constant.SkipNo.URL_7001.toInteger());
                    skipBean.setShareTitle(shareTitle);
                    skipBean.setShareText(shareText);
                    skipBean.setShareImageUrl(imageUrl);
                    skipBean.setShareSkipUrl(shareSkipUrl);
                }
                return getVelocityTemplate(skipBean);
            }
        }

        return "";
    }


    /**
     * 合成url类型跳转模型
     *
     * @param version
     * @param url
     * @return
     */
    public String fittingSkipUrl(String version, String url, Integer isShare,String id) {

        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotEmpty(url)) {
            String apiSkipTemplate = selSkipTemplate(version, Constant.SkipNo.URL_7000.toInteger());
            SkipBean skipBean = new SkipBean();
            skipBean.setSkipUrl(url);
            skipBean.setSkipNo(Constant.SkipNo.URL_7000.toInteger());
            skipBean.setSkipTemplate(apiSkipTemplate);
            skipBean.setShare(isShare);
            skipBean.setIds(id);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    /**
     * 合成url类型跳转模型 临时增加pcode 不改动其他方法
     *
     * @param version
     * @param url
     * @return
     */
    public String fittingSkipUrl(String version, String url, Integer isShare,String id,Integer pcode) {

        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotEmpty(url)) {
            String apiSkipTemplate = selSkipTemplate(version, Constant.SkipNo.URL_7000.toInteger());
            SkipBean skipBean = new SkipBean();
            skipBean.setSkipUrl(url);
            skipBean.setSkipNo(Constant.SkipNo.URL_7000.toInteger());
            skipBean.setSkipTemplate(apiSkipTemplate);
            skipBean.setShare(isShare);
            skipBean.setIds(id);
            skipBean.setPcode(pcode);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    public String fittingSkipUrl(Map<String, String> skipConfigMap, SkipBean skipBean) {

        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotEmpty(skipBean.getSkipUrl())) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(Constant.SkipNo.URL_7000.toInteger()));
            skipBean.setSkipTemplate(apiSkipTemplate);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    public String fittingSkipUrl(String version,SkipBean skipBean) {

        String apiSkipTemplate = selSkipTemplate(version, Constant.SkipNo.URL_7000.toInteger());
        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotEmpty(skipBean.getSkipUrl())) {
            skipBean.setSkipTemplate(apiSkipTemplate);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }


    /**
     * 获取无参数构造的跳转模板
     *
     * @param
     * @param skipNo
     * @return
     */
    public String fittingNoParameterSkipModel(Map<String, String> skipConfigMap, Integer skipNo) {

        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotNull(skipNo)) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(skipNo));
            if (QDStringUtil.isNotEmpty(apiSkipTemplate)) {
              /*  SkipBean skipBean = new SkipBean();
                skipBean.setSkipNo(skipNo);
                skipBean.setSkipTemplate(apiSkipTemplate);*/
                return apiSkipTemplate;//getVelocityTemplate(skipBean);
            }
        }
        return "";
    }


    /**
     * 获取无参数构造的跳转模板
     *
     * @param version
     * @param skipNo
     * @return
     */
    public String fittingNoParameterSkipModel(String version, Integer skipNo) {

        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotNull(skipNo)) {
            String apiSkipTemplate = selSkipTemplate(version, skipNo);
           /* SkipBean skipBean = new SkipBean();
            skipBean.setSkipNo(skipNo);
            skipBean.setSkipTemplate(apiSkipTemplate);*/
            return apiSkipTemplate /*getVelocityTemplate(skipBean)*/;
        } else {
            return "";
        }
    }

    /**
     * 组装参数只有id的跳转模型
     *
     * @param
     * @param skipNo
     * @param ids
     * @return
     */
    public String fittingSkipModelByOnlyId(Map<String, String> skipConfigMap, Integer skipNo, String ids) {

        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotEmpty(ids) && QDStringUtil.isNotNull(skipNo)) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(skipNo));
            if (QDStringUtil.isNotEmpty(apiSkipTemplate)) {
                SkipBean skipBean = new SkipBean();
                skipBean.setSkipNo(skipNo);
                skipBean.setIds(ids);
                skipBean.setSkipTemplate(apiSkipTemplate);
                return getVelocityTemplate(skipBean);
            }
        }
        return "";
    }


    /**
     * 组装参数只有id的跳转模型
     *
     * @param version
     * @param skipNo
     * @param ids
     * @return
     */
    public String fittingSkipModelByOnlyId(String version, Integer skipNo, String ids) {

        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotEmpty(ids) && QDStringUtil.isNotNull(skipNo)) {
            String apiSkipTemplate = selSkipTemplate(version, skipNo);
            SkipBean skipBean = new SkipBean();
            skipBean.setSkipNo(skipNo);
            skipBean.setIds(ids);
            skipBean.setSkipTemplate(apiSkipTemplate);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    public String fittingSkipModelBySkipBean(Map<String, String> skipConfigMap, SkipBean skipBean) {

        if (QDStringUtil.isNotNull(skipConfigMap) && QDStringUtil.isNotNull(skipBean)&& QDStringUtil.isNotNull(skipBean.getSkipNo())) {
            String apiSkipTemplate = skipConfigMap.get(String.valueOf(skipBean.getSkipNo()));
            skipBean.setSkipTemplate(apiSkipTemplate);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    /**
     * 通过skipbean模型组装skipModel
     * @param skipBean
     * @return
     */
    public String  fittingSkipModelBySkipBean(String version,SkipBean skipBean){

        if (QDStringUtil.isNotEmpty(version) && QDStringUtil.isNotNull(skipBean.getSkipNo())) {
            String apiSkipTemplate = selSkipTemplate(version, skipBean.getSkipNo());
            skipBean.setSkipTemplate(apiSkipTemplate);
            return getVelocityTemplate(skipBean);
        } else {
            return "";
        }
    }

    /**
     * 获取模板并填充
     *
     * @param
     * @return
     */
    private String getVelocityTemplate(SkipBean skipBean) {

        if (QDStringUtil.isNotNull(skipBean) && QDStringUtil.isNotEmpty(skipBean.getSkipTemplate())) {

            String template = skipBean.getSkipTemplate();
            Integer skipNo = skipBean.getSkipNo();
            Integer version = skipBean.getVersion();
            Context context = new VelocityContext();
            StringWriter relateConfig = new StringWriter();
            try {

                switch (skipNo) {

                    case 7000: //url跳转 keys skip_config:*
                        context.put("url", skipBean.getSkipUrl());
                        context.put("pcode", skipBean.getPcode());
                        if(QDStringUtil.isEmpty(skipBean.getIds())){
                            context.put("id", "000000");//不做统计|或异常的暂用000000标记
                        } else {
                            context.put("id", skipBean.getIds());
                        }
                        if (QDStringUtil.isEmpty(skipBean.getIdentity())) {
                            context.put("identity", Constant.DETAL_IDENTITY);
                        } else {
                            context.put("identity",skipBean.getIdentity());
                        }
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 7001: //url跳转
                        context.put("url", skipBean.getSkipUrl());
                        context.put("pcode", skipBean.getPcode());
                        context.put("share", QDStringUtil.isNotNull(skipBean.getShare()) ? skipBean.getShare() : 0);
                        context.put("title", skipBean.getShareTitle());
                        context.put("text", skipBean.getShareText());
                        context.put("imgUrl", skipBean.getShareImageUrl());
                        context.put("skipUrl", skipBean.getShareSkipUrl());
                        if(QDStringUtil.isEmpty(skipBean.getIds())){
                            context.put("id", "000000");//不做统计|或异常的暂用000000标记
                        } else {
                            context.put("id", skipBean.getIds());
                        }
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 1002: //社区公告列表
                        context.put("", "");
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3003: //通过活动Id获取活动详情
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3010: //邻聚群公告详情点击模型
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 4008: //乐购订单详情点击模型
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5000: //商品列表跳转模型
                        context.put("ids", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5004://商品详情跳转模型

                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5005://首页特色商品模型
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 4009: //通过订单ID去往评论页
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5006://乐购首页（品类查询）
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5003://乐购品类查询列表
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 5007://乐购关键字搜索
                        context.put("keyWord", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3015:// 新版邻聚个人中心页
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3014:// 新版邻聚话题详情
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 4201:

                        if(QDStringUtil.isNotNull(skipBean.getProjectId())){
                            context.put("cid",skipBean.getProjectId());
                            context.put("cname",skipBean.getProjectName());
                            context.put("pcode", skipBean.getPcode());
                        }
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3019:
                        if(QDStringUtil.isNotEmpty(skipBean.getIds())){
                            String[] idArray = skipBean.getIds().split(",");
                            if(idArray.length>1){
                                context.put("id", idArray[0]);
                                context.put("themeId", idArray[1]);
                                Velocity.evaluate(context, relateConfig, "velocity", template);
                            }
                        }
                        break;
                    case 3020:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3021:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;

                    case 3022:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;
                    case 3024:
                    	 context.put("id", skipBean.getIds());
                         Velocity.evaluate(context, relateConfig, "velocity", template);
                         break;
                    case 3025:
                   	 	 context.put("id", skipBean.getIds());
                         Velocity.evaluate(context, relateConfig, "velocity", template);
                         break;
                    case 3028:
                  	 	 context.put("id", skipBean.getIds());
                  	 	 Velocity.evaluate(context, relateConfig, "velocity", template);
                  	 	 break;
                    case 3031:
                 	 	 context.put("id", skipBean.getIds());
                 	 	 Velocity.evaluate(context, relateConfig, "velocity", template);
                 	 	 break;
                    case 3032:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;
                    case 3033:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;
                    case 4206:
                        context.put("content", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;
                    case 4207:
                        context.put("id", skipBean.getIds());
                        Velocity.evaluate(context, relateConfig, "velocity", template);
                        break;
                    default:
                        //如果是服务项需要增加身份权限
                        if (Constant.SERVICE_SKIPNO_LIST.contains(skipNo)) {
                            context.put("id", skipBean.getIds());
                            context.put("identity", skipBean.getIdentity());
                            context.put("pcode",skipBean.getPcode());
                            Velocity.evaluate(context, relateConfig, "velocity", template);
                        } else {
                            Velocity.evaluate(context, relateConfig, "velocity", template);
                        }
                        break;
                }
                return relateConfig.toString();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("relateConfig==================>error");
        return "";
    }


}
