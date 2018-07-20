package com.matas.conf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 导入指定配置
 *
 * @author matas
 * @date 2018/7/20 10:26
 * @email mataszhang@163.com
 */
public class MyImportSelector implements ImportSelector, BeanFactoryAware {
    BeanFactory beanFactory;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String myConfigName = MyImportSelectorConfig.class.getName();
        System.err.println("com.matas.conf.MyImportSelector.selectImports()=>返回 "+myConfigName);
        return new String[]{myConfigName};
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("com.matas.conf.MyImportSelector.setBeanFactory()");
        this.beanFactory = beanFactory;
    }
}
