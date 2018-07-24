package com.matas.applicationListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;

/**
 * @author matas
 * @date 2018/7/20 22:23
 * @email mataszhang@163.com
 */
public class MyApplicationListener implements ApplicationListener<ApplicationPreparedEvent> {
    private static final Log logger = LogFactory.getLog(MyApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        System.err.println("com.matas.applicationListener.MyApplicationListener.onApplicationEvent()=>ApplicationPreparedEvent");
    }
}
