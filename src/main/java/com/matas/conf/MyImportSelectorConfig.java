package com.matas.conf;

import com.matas.springbootdemo.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试自定义导入配置
 *
 * @author matas
 * @date 2018/7/20 10:27
 * @email mataszhang@163.com
 */
@Configuration
@ConditionalOnProperty(name = "enabled.myconfig", havingValue = "true")  //基于条件的自动配置
public class MyImportSelectorConfig {
    @Bean
    User user() {
        System.err.println("MyImportSelectorConfig => create a user");
        return new User();
    }

}
