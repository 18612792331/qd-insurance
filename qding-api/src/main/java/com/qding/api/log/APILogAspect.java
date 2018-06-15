package com.qding.api.log;


import com.qding.framework.common.log.APILogger;
import com.qding.framework.common.log.LogAspect;
import com.qding.framework.common.log.LogTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * 日志输出切面实现：
 * 类型：http/rpc/resouce:
 * Created by qd on 2015/11/25.
 */
@Aspect
public class APILogAspect extends LogAspect {

    /**
     *
     * @Title
     * @Description: 环绕触发
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.qding.api.controllers.*.handler(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result =doAroundService(pjp);
        APILogger.clearCurrRecord(); //清除线程中的缓存
        return result;
    }

    public String fetchTypeByPath(String path){
        String type = null;
        if (path.contains("api")) {
            type = LogTypeEnum.HTTP.getValue();
        }
        return type;
    }

}
