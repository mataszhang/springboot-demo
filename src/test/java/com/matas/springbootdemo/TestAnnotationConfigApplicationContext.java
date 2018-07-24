package com.matas.springbootdemo;

import com.matas.springbootdemo.bean.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessorRegistrar;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * EnableConfigurationProperties ，ConfigurationProperties 实现流程
 *
 * @author matas
 * @date 2018/7/24 13:47
 * @email mataszhang@163.com
 */

@EnableAutoConfiguration
public class TestAnnotationConfigApplicationContext {

    @Configuration
    @EnableConfigurationProperties(ConfigHolder.class)
    @PropertySource("config.properties")
    public static class AppConfig {

    }

    @ConfigurationProperties(prefix = "app")
    public static class ConfigHolder {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AppConfig{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * @param args
     * @return
     * @author matas
     * @date 2018/7/24 14:28
     * @see AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, java.lang.Object) 注册ConfigurationClassPostProcessor ，解析@Configuration
     * @see ConfigurationClassParser#processPropertySource(AnnotationAttributes) 解析@PropertySource , 放到AbstractApplicationContext->ConfigurableEnvironment(environment)->propertySources中
     * @see EnableConfigurationPropertiesImportSelector#selectImports(AnnotationMetadata) 解析@Configuration 会processImports， 调用该方法
     * @see EnableConfigurationProperties 导入配置 ConfigurationPropertiesBeanRegistrar， ConfigurationPropertiesBindingPostProcessorRegistrar
     * @see ConfigurationPropertiesBeanRegistrar  如果 @EnableConfigurationProperties的values有值，则将values对应的class注册到BeanFactory ,{@link org.springframework.boot.context.properties.EnableConfigurationPropertiesImportSelector.ConfigurationPropertiesBeanRegistrar#registerBeanDefinition(BeanDefinitionRegistry, java.lang.Class, java.lang.String)}
     * @see ConfigurationPropertiesBindingPostProcessorRegistrar 注册ConfigurationBeanFactoryMetaData ， ConfigurationPropertiesBindingPostProcessor
     * @see ConfigurationPropertiesBindingPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String) 处理 @ConfigurationProperties
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ConfigHolder configHolder = ctx.getBean(ConfigHolder.class);
        //使用springboot的@EnableAutoConfiguration

        //ctx.scan("com.matas");//org.springframework.context.annotation.ClassPathBeanDefinitionScanner.doScan()

        AnnotationConfigApplicationContext springBootCtx = new AnnotationConfigApplicationContext(TestAnnotationConfigApplicationContext.class);

        System.out.println(configHolder);
        System.out.println(springBootCtx.getBeanDefinitionCount());
    }
}
