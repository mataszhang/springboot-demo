package com.matas.springbootdemo;

import com.matas.conf.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyImportSelector.class)
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        System.out.println("默认加载SpringbootDemoApplication所在包下面的@Configuration");
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
}
