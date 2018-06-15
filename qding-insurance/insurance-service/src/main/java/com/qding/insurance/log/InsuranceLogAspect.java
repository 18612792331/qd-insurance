package com.qding.insurance.log;

import com.qding.framework.common.log.LogAspect;
import com.qding.framework.common.log.LogTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class InsuranceLogAspect extends LogAspect {
    
    @Around("(execution(* com.qding.insurance.controller..*.*(..)) || "
            +"execution(* com.qding.insurance.rpc.impl..*.*(..)))"
    )
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result =doAroundService(pjp);
        return result;
    }

    public String fetchTypeByPath(String path){
        String type = null;
        if (path.contains("insurance-admin")) {
            type = LogTypeEnum.HTTP.getValue();
        }else if (path.contains("insurance-remote"))  {
            type = LogTypeEnum.RPC.getValue();
        }
        return type;
    }
}
