package com.matas.springbootdemo;

import com.matas.selector.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.StringUtils;

import java.util.List;

@Import(MyImportSelector.class)
@SpringBootApplication
public class SpringbootDemoApplication implements ApplicationListener<ApplicationReadyEvent> {

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

    /**
     * 启动后，修改日志级别
     *
     * @param event
     * @return void
     * @author matas
     * @date 2018/8/2 21:46
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LoggingSystem loggingSystem = LoggingSystem.get(this.getClass().getClassLoader());
        List<LoggerConfiguration> loggerConfigurations = loggingSystem.getLoggerConfigurations();
        loggerConfigurations.stream().filter(config -> StringUtils.startsWithIgnoreCase(config.getName(), "org.springframework.web.servlet")).forEach(config -> loggingSystem.setLogLevel(config.getName(), LogLevel.DEBUG));
    }
}
