package com.matas.springbootdemo;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 *
 *@author matas
 *@date  2018/7/21 10:24
 *@email   mataszhang@163.com
 */
@Component
public class CustomEmbeddedTomcatCustomizer implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(9999);
    }
}
