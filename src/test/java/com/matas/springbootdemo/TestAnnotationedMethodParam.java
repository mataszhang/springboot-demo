package com.matas.springbootdemo;

import org.junit.Test;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.support.HandlerMethodInvoker;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Method;

/**
 * @author matas
 * @date 2018/7/27 17:49
 * @email mataszhang@163.com
 */
public class TestAnnotationedMethodParam {

    @RequestMapping
    public void hello(@RequestParam String name, @CookieValue String address) {

    }

    /**
     * 测试springmvc 解析函数参数中的注解
     *
     * @param
     * @return
     * @author matas
     * @date 2018/7/27 18:00
     * @see HandlerMethodInvoker#resolveHandlerArguments(java.lang.reflect.Method, java.lang.Object, NativeWebRequest, org.springframework.ui.ExtendedModelMap)
     */
    @Test
    public void test() {
        Class<TestAnnotationedMethodParam> clazz = TestAnnotationedMethodParam.class;

        Method method = ClassUtils.getMethod(clazz, "hello", String.class,String.class);
        Class<?>[] paramTypes = method.getParameterTypes();

       // PrioritizedParameterNameDiscoverer nameDiscoverer = new PrioritizedParameterNameDiscoverer();
        for (int i = 0; i < paramTypes.length; i++) {
            SynthesizingMethodParameter methodParam = new SynthesizingMethodParameter(method, i);
            //methodParam.initParameterNameDiscovery(nameDiscoverer);
            //GenericTypeResolver.resolveParameterType(methodParam, clazz);

            RequestParam parameterAnnotation = methodParam.getParameterAnnotation(RequestParam.class);

            String parameterName = methodParam.getParameterName();
            if (methodParam.hasParameterAnnotation(RequestParam.class)) {
                System.out.println(parameterName + "有" + RequestParam.class);
            }

            if (methodParam.hasParameterAnnotation(CookieValue.class)) {
                System.out.println(parameterName + "有" + CookieValue.class);
            }

        }
    }
}
