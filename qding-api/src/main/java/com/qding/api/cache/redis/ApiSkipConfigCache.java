package com.qding.api.cache.redis;

import com.qding.api.cache.JSONSerial;
import com.qding.api.constant.Constant;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.redis.ShardedJedisClient;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.sysconfig.dto.ApiSkipDto;
import com.qding.sysconfig.rpc.service.ApiSkipConfigRpcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by stefan on 15-12-27.
 */
public class ApiSkipConfigCache extends JSONSerial {

    @Resource(name = "sharedJedisClient")
    private ShardedJedisClient client;

    @Autowired
    private ApiSkipConfigRpcService apiSkipConfigRpcService;

    private final static String SKIP_CONFIG = "api_skip_config:%s:%s";

    private static Logger logger = Logger.getLogger(ApiSkipConfigCache.class);


    public String getByCode(ApiSkipDto apiSkipDto) {
        String key = String.format(SKIP_CONFIG,apiSkipDto.getScopeType(), apiSkipDto.getVersion());
        String value = null;
        try{
            value = client.hget(key, String.valueOf(apiSkipDto.getVersion()));
            logger.info("select skipconfig from redis ........value:"+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(value == null) {
                Map<String, String>  skipConfigMap = getSkipAll(apiSkipDto);
                if(skipConfigMap!=null && skipConfigMap.size()>0){
                    value = skipConfigMap.get(String.valueOf(apiSkipDto.getSkipNo()));
                }
            }
        }
        return value;
    }

    public  Map<String, String>  getSkipAll(ApiSkipDto apiSkipDto){

        String key = String.format(SKIP_CONFIG,apiSkipDto.getScopeType(), apiSkipDto.getVersion());
        Map<String, String>  skipConfigMap = null;
        try{
            skipConfigMap = client.hgetall(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (QDStringUtil.isNull(skipConfigMap)||skipConfigMap.size()==0){
                skipConfigMap = getSkipConfigMap(apiSkipDto.getVersion(),apiSkipDto.getScopeType());
                try{
                    String expirat =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_2.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_2.getDictKey());
                    String result = client.hmset(key, skipConfigMap);
                    client.expire(key,Integer.parseInt(expirat));
                    logger.info("select  skipconfig to from db");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                logger.info("select  skipconfig to from cache");
            }
        }
        return skipConfigMap;
    }

    private Map<String, String> getSkipConfigMap(Integer version,Integer scopeType){

        ApiSkipDto apiSkipDto = new ApiSkipDto();
        apiSkipDto.setScopeType(scopeType);
        apiSkipDto.setVersion(version);
        Map<String, String> skipTemplateMap = apiSkipConfigRpcService.findSkipConfigMap(apiSkipDto);
        return skipTemplateMap;
    }


    public Long del(Integer version,Integer scopeType) throws Exception{
        Long count = client.del(String.format(SKIP_CONFIG,scopeType, version));
        return count;
    }

}