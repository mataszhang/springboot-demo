package com.matas.springbootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author matas
 * @date 2018/8/7 18:39
 * @email mataszhang@163.com
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean testFilter() {
        System.err.println("*******************");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TestFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Slf4j
    static class TestFilter implements Filter {
        @Override
        public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
            log.info("com.matas.springbootdemo.config.FilterConfig.TestFilter.init().............");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            log.info("com.matas.springbootdemo.config.FilterConfig.TestFilter.doFilter().............");
            chain.doFilter(request,response);
        }

        @Override
        public void destroy() {

        }
    }
}
