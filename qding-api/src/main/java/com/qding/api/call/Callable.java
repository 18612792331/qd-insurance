package com.qding.api.call;

import com.alibaba.fastjson.JSONObject;
import com.qding.api.annotation.HTTP;
import com.qding.api.constant.Constant;
import com.qding.api.process.GlobalInstance;
import com.qding.api.process.InvokeCallable;
import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.process.security.CallableSecurity;
import com.qding.api.process.security.SecurityObject;
import com.qding.api.process.security.UserToken;
import com.qding.api.process.security.UserTokenCallableSecurity;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.HttpMethod;
import com.qding.api.util.MD5Util;
import com.qding.api.util.SkipModeFitting;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.SmartValidate;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.common.ModelResult;
import com.qding.neighbor.v3.dto.PageResultDto;
import com.qding.trade.rpc.service.result.dto.ChannelDto;
import com.qding.trade.rpc.service.result.dto.ClientDto;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author lichao
 */
public class Callable extends InvokeCallable {

    @Autowired
    private Mapper mapper;

    @Autowired
    private SkipModeFitting skipMode;

    private static final String requestKey = "body";

    /**
     * 接口认证
     *
     * @return
     * @throws ServiceException
     */
    protected CallableSecurity getCallableSecurity() throws ServiceException {

        return GlobalInstance.getUserTokenCallableSecurity();

    }

    public String call(AbstractProtocolPrint print,
                       Method targetMethod, HttpServletRequest request,
                       HttpServletResponse response, BaseRequest baseRequest,String methodAlias) throws Exception {

        if (isNotSupportHttpMethod(request.getMethod(), targetMethod)) {

            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "http method " + request.getMethod() + " not support ");
        }

        HTTP http = targetMethod.getAnnotation(HTTP.class);

        SecurityObject securityObject = null;

        String  qdPlatform = "";

        Integer version = UserTokenCallableSecurity.initVersion(baseRequest);

        //检测请求版本是否符合最小版本
        if (http.checkVersion()) {
            checkMinVersion(version);
        }

        //验证请求是否非法
        if(http.isNeedSign()) {
            Boolean checkSign = checkSign (request);
            if (!checkSign){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "当前为非法请求");
            }
        }

        //验证底层当前社区是否存在
        if(http.isNeadProject()){
            if(QDStringUtil.isNull(baseRequest.getAppUser()) || QDStringUtil.isNull(baseRequest.getAppUser().getProjectId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "社区ID不能为空");
            }
        }


        //验证用户状态
        UserTokenCallableSecurity.checkMemberStatus(request,http.isNeadToken());
        try{
            qdPlatform =  baseRequest.getAppDevice().getQdPlatform().toLowerCase();
         } catch ( Exception e) {

         }

        if (!Constant.QD_PLATFORM_QDPAD.equals(qdPlatform) && !http.isLogin()) {
                securityObject = getCallableSecurity().checkCallableSecurity(print, targetMethod, request, response,baseRequest);
         }

        Object[] targetMethodArguments = beforeInvoke(print, targetMethod, securityObject, request, response);

        Object returnValue = invoke(targetMethod, targetMethodArguments, securityObject,request, response,version);

        String returnString = afterInvoke(print, returnValue).toString();

        return returnString;
    }


    private void checkMinVersion(Integer curVersion ) throws Exception{

        String minVersion = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_API_VERSION.getGroupName(),Constant.Dict_K_V_Enum.DICT_API_VERSION.getDictKey());
        if ( curVersion<Integer.parseInt(minVersion)){
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(),  "无效的请求！");
        }
    }


    public boolean isNotSupportHttpMethod(String httpMethod, Method method) {
        HTTP http = method.getAnnotation(HTTP.class);

        HttpMethod[] supportMethod = http.supportMethod();
        for (HttpMethod m : supportMethod) {
            if (m.is(httpMethod)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected Object afterInvoke(AbstractProtocolPrint print, Object value) {
        if (value == null) {
            return "";
        }

        if (NOSERIALIZABLE.contains(value.getClass())) {

            return value;
        }

        return print.out(value);
    }

    @Override
    protected Object[] beforeInvoke(AbstractProtocolPrint print,
                                    Method targetMethod, Object securityObject, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        Class<?>[] argumentClasses = targetMethod.getParameterTypes();

        Object[] arguments = new Object[argumentClasses.length];

        for (int i = 0; i < argumentClasses.length; i++) {

            Class<?> argumentClass = argumentClasses[i];

            if (BaseRequest.class.isAssignableFrom(argumentClass)) {

                String value = (String) GlobalInstance.getTransportSecurity().getParameter(request, requestKey);

                if (value == null || value.trim().length() == 0) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "missing arguments body");
                }

                value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");

                value = URLDecoder.decode(value, "utf-8");

                Object in = print.in(value, argumentClass);

                SmartValidate.validate(in);

                arguments[i] = in;
            } else if (HttpServletRequest.class.isAssignableFrom(argumentClass)) {
                arguments[i] = request;
            } else if (HttpServletResponse.class.isAssignableFrom(argumentClass)) {
                arguments[i] = response;
            } else if (SecurityObject.class.isAssignableFrom(argumentClass)) {
                arguments[i] = securityObject;
            }
        }

        return arguments;
    }

    @Override
    protected Object invoke(Method targetMethod, Object[] arguments,SecurityObject securityObject,
                            HttpServletRequest request, HttpServletResponse response, Integer version)
            throws Exception {



        Response returnValue = (Response) targetMethod.invoke(this, arguments);
        try{
            if (version>= Constant.TOKEN_VERSION && QDStringUtil.isNotNull(securityObject)
                    && QDStringUtil.isNotNull(returnValue) && QDStringUtil.isNotNull(returnValue.getData())){
                String userToken = GlobalInstance.getUserTokenCallableSecurity().generatorToken((UserToken)securityObject,version);
                returnValue.getData().setBaseToken(userToken);
            }
        }catch (Exception e){

        }

        return returnValue;
    }

    private static final Set<Class<?>> NOSERIALIZABLE = new HashSet<>();

    static {

        NOSERIALIZABLE.add(int.class);
        NOSERIALIZABLE.add(long.class);
        NOSERIALIZABLE.add(float.class);
        NOSERIALIZABLE.add(double.class);
        NOSERIALIZABLE.add(char.class);
        NOSERIALIZABLE.add(boolean.class);
        NOSERIALIZABLE.add(byte.class);
        NOSERIALIZABLE.add(short.class);
        NOSERIALIZABLE.add(Short.class);
        NOSERIALIZABLE.add(Byte.class);
        NOSERIALIZABLE.add(Boolean.class);
        NOSERIALIZABLE.add(Character.class);
        NOSERIALIZABLE.add(Double.class);
        NOSERIALIZABLE.add(Float.class);
        NOSERIALIZABLE.add(Long.class);
        NOSERIALIZABLE.add(Integer.class);
        NOSERIALIZABLE.add(String.class);

    }

    private static final Logger logger = Logger.getLogger("callable");


    protected  void checkAndContinue (PageResultDto pageResult) throws  ServiceException {

        ReturnInfo returnInfo = pageResult.getReturnInfo();
        int code = returnInfo.getCode();

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {
                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception : class --->")
                        .append(pageResult.getClass())
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(pageResult))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }

            throw new ServiceException(code, returnInfo.getMessage());
        }

    }

    protected void checkAndContinue(BaseResponse response) throws ServiceException {

        ReturnInfo returnInfo = response.getReturnInfo();

        int code = returnInfo.getCode();

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {
                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception : class --->")
                        .append(response.getClass())
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(response))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }
            throw new ServiceException(code, returnInfo.getMessage());
        }

    }

    protected void checkAndContinue(ClientDto client) throws ServiceException {

        int code = client.getCode();

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {
                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception : class --->")
                        .append(client.getClass())
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(client))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }


            throw new ServiceException(code, client.getMessage());
        }

    }

    protected void checkAndContinue(ChannelDto client) throws ServiceException {

        int code = client.getCode();

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {
                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception : class --->")
                        .append(client.getClass())
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(client))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }


            throw new ServiceException(code, client.getMessage());
        }

    }

    protected  void checkEntity ( Object o) throws ServiceException {
        if (QDStringUtil.isNull(o)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "信息获取异常");
        }
    }

    protected void checkAndContinue(ModelResult modelResult) throws ServiceException {

        int code = modelResult.getCode();

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {
                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception : class --->")
                        .append(modelResult.getClass())
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(modelResult))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }


            throw new ServiceException(code, modelResult.getMessage());
        }

    }


    /**
     * instead of @see {@link #checkAndContinue(BaseResponse)}
     *
     * @param rpcResponse
     * @param responseData
     * @return
     */
    @Deprecated
    protected <T extends ResponseData> Response<T> standardResponse(BaseResponse rpcResponse, T responseData) {
        Response<T> response = new Response<T>();
        int code = rpcResponse.getReturnInfo().getCode();

        if (code != 200) {
            responseData.setMessage(rpcResponse.getReturnInfo().getMessage());
        }
        response.setCode(code);
        response.setData(responseData);
        return response;
    }

    private ReturnInfo toReturnInfo(Exception e) {
        logger.error(e.getMessage(), e);
        ReturnInfo returnInfo = null;
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            returnInfo = se.getReturnInfo();
        } else {
            e.printStackTrace();
            returnInfo = new ReturnInfo(HttpStatus.INTERNAL_SERVER_ERROR);
            returnInfo.setMessage("当前服务不可用，请稍后再试");
        }

        return returnInfo;
    }

    public Response<ResponseData> handleException(Exception e) {

        return handleException(ResponseData.class, e);

    }

    public <T extends ResponseData> Response<T> handleException(Class<T> clazz, Exception e) {
        T data = null;
        try {
            data = clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return handleException(data, e);
    }

    public <T extends ResponseData> Response<T> handleException(T data, Exception e) {
        ReturnInfo returnInfo = toReturnInfo(e);
        Response<T> response = new Response<T>();
        data.setMessage(returnInfo.getMessage());
        response.setCode(returnInfo.getCode());
        response.setData(data);
        return response;
    }

    private Boolean checkSign(HttpServletRequest request) {

        String sourceSign ="";
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<String> sortedKey = new ArrayList<>(parameterMap.keySet());
        Collections.sort(sortedKey, new Comparator<String>() {
            @Override
                public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuffer beforeMd5String = new StringBuffer();
        for(String key : sortedKey) {
            String[] values = parameterMap.get(key);
            if ("signCode".equals(key)) {
                if(values.length<1){
                    return  false;
                }
                sourceSign = values[0];
                continue;
            }

            for(String value : values) {
                //跳过value的空的参数
                if(QDStringUtil.isEmpty(value)) { continue;}
                beforeMd5String.append(key).append(value.replace(" ","").replace("\n","").toString());
            }
        }

        return  MD5Util.checkSign(beforeMd5String.toString(),sourceSign);

    }

    protected void checkAndContinue(Map<String, Object> map) throws ServiceException {

        String json = JSONObject.toJSONString(map);
        JSONObject jsonObj = JSONObject.parseObject(json);
        JSONObject dataJson = JSONObject.parseObject(jsonObj.getString("data"));
        String message = dataJson.getString("message");
        Integer code = Integer.parseInt(jsonObj.getString("code"));

        if (HttpStatus.OK.getStatusCode() != code) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            if (stackTrace.length > 1) {

                StringBuffer log = new StringBuffer();
                log.append(" RPC Exception :")
                        .append(" response --->")
                        .append(ToStringBuilder.reflectionToString(map))
                        .append(" methodName --->")
                        .append(stackTrace[1].getMethodName())
                        .append(" line --->")
                        .append(stackTrace[1].getLineNumber());

                logger.info(log.toString());
            }

            if (code == 1000) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), message); //只针对绑定房屋验证返回信息（凡红恩）
            }
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode(), "当前服务不可用，请稍后再试");
        }

    }


    public <T> List<T> transforList(Class<T> clazz, List<?> sources) {
        List<T> list = new ArrayList<>();
        if (sources == null) {
            return list;
        }
        for (Object o : sources) {
            T t = transfor(clazz, o);
            list.add(t);
        }
        return list;
    }

    public void transfor(Object target, Object source) {
        if (source == null || target == null) {
            return;
        }
        mapper.map(source, target);
    }

    public <T> T transfor(Class<T> target, Object source) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, target);
    }
}
