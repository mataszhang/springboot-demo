package com.matas.springbootdemo.log.annnotation;

import java.lang.annotation.*;

/**
 * @author matas
 * @date 2018/7/26 7:06
 * @email mataszhang@163.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SysLog {
    String value() default "";
}
