package com.matas.springbootdemo;

import com.matas.conf.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

@Import(MyImportSelector.class)
@SpringBootApplication
public class SpringbootDemoApplication {

    /**
     * 测试SpringFactoriesLoader
     *
     * @param
     * @return void
     * @author matas
     * @date 2018/7/19 20:39
     * @see EnableAutoConfiguration
     * @see AutoConfigurationImportSelector#getCandidateConfigurations(AnnotationMetadata, AnnotationAttributes)  通过SpringFactoriesLoader从classpath中搜索所有的META-INF/spring.factories，然后将EnableAutoConfiguration配置项导入
     */
    private static void testLoadConfig() {
        System.out.println("默认加载SpringbootDemoApplication所在包下面的@Configuration");
        List<String> configs = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, EnableAutoConfiguration.class.getClassLoader());
        System.out.println(configs.size() + "=>" + configs);
    }

    public static void main(String[] args) {
        testLoadConfig();
        //SpringApplication.run(SpringbootDemoApplication.class, args);
        SpringApplication sa = new SpringApplication(SpringbootDemoApplication.class);
        sa.run(args);
    }
}
