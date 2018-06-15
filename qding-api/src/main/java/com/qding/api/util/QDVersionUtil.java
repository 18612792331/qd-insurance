package com.qding.api.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 千丁App版本管理
 * Created by jinhaishan on 17/4/19.
 */
public class QDVersionUtil {

    private static Map<String, Integer> versions = Maps.newHashMap();

    public static final Integer VERSION_270 = 270;
    public static final Integer VERSION_280 = 280;
    public static final Integer VERSION_300 = 300;
    public static final Integer VERSION_301 = 301;
    public static final Integer VERSION_310 = 310;
    public static final Integer VERSION_320 = 320;
    public static final Integer VERSION_322 = 322;
    public static final Integer VERSION_330 = 330;
    public static final Integer VERSION_331 = 331;
    public static final Integer VERSION_332 = 332;
    static {
        versions.put("2.7.0", VERSION_270);
        versions.put("2.8.0", VERSION_280);
        versions.put("3.0.0", VERSION_300);
        versions.put("3.0.1", VERSION_301);
        versions.put("3.1.0", VERSION_310);
        versions.put("3.2.0", VERSION_320);
        versions.put("3.2.2", VERSION_322);
        versions.put("3.3.0", VERSION_330);
        versions.put("3.3.1", VERSION_331);
        versions.put("3.3.2", VERSION_332);
    }

    /**
     * 根据app入参提供的版本号，获取整型版本号
     * @param version
     * @return
     */
    public static Integer getVersionCode(String version)
    {
        Integer versionCode =  versions.get(version.trim());
        if(versionCode == null)
        {
            versionCode = Integer.parseInt(version.trim().replaceAll("\\.", ""));
        }
        Preconditions.checkNotNull(versionCode);
        return versionCode;
    }

}
