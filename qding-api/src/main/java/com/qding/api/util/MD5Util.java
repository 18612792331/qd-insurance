package com.qding.api.util;

/**
 * Created by qd on 2017/2/23.
 */
public class MD5Util  {

    //前缀
    private static final String PREFIX = "qIng@1$%cOM";

    //后缀
    private static final String SUFFIX = "109eInS4";

    public static String md5(String str){
        return com.qding.framework.common.util.MD5Util.md5(PREFIX+str+SUFFIX);
    }

    public static Boolean checkSign(String targetRequest ,String sign){

        return  com.qding.framework.common.util.MD5Util.verify(PREFIX+targetRequest+SUFFIX,sign.trim());
    }



}
