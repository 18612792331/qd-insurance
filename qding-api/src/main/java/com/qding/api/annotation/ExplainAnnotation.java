package com.qding.api.annotation;

/**
 * Created by qd on 2016/1/14.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//上面这个参数是必须的
public @interface ExplainAnnotation {

    String explain() default "";
    String desc() default "";
}