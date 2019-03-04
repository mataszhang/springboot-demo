package com.matas.springbootdemo;

import org.junit.Test;
import org.springframework.core.*;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.support.HandlerMethodInvoker;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试springmvc中 解析函数参数中的注解，和参数名称
 *
 * @author matas
 * @date 2018/7/27 17:49
 * @email mataszhang@163.com
 */
public class TestAnnotationMethodParamParse implements IHello<List<String>, Integer> {
    @RequestMapping
    public void hello(@RequestParam List<String> name, @CookieValue Integer age) {

    }

    /**
     * 测试springmvc中 解析函数参数中的注解，和参数名称
     *
     * @param
     * @return
     * @author matas
     * @date 2018/7/27 18:00
     * @see https://jinnianshilongnian.iteye.com/blog/1993608
     * @see https://blog.csdn.net/u011402896/article/details/80702047
     */
    @Test
    public void test() throws Exception {
        Class<TestAnnotationMethodParamParse> clazz = TestAnnotationMethodParamParse.class;
        Method method = clazz.getMethod("hello", Object.class, Object.class);

        List<SynthesizingMethodParameter> paramList = parse(method);

        for (SynthesizingMethodParameter methodParam : paramList) {
            int parameterIndex = methodParam.getParameterIndex();
            String methodName = method.getName();

            String parameterType = methodParam.getParameterType().getName();
            String parameterName = methodParam.getParameterName(); //通过ParameterNameDiscoverer来解析参数名称。
            //具体由LocalVariableTableParameterNameDiscoverer  Uses ObjectWeb's ASM library for analyzing class files获得

            Type genericParameterType = methodParam.getGenericParameterType();
            System.out.println("第" + parameterIndex + "个参数泛型类型=>" + genericParameterType.getTypeName());
            if (methodParam.hasMethodAnnotation(RequestMapping.class) && methodParam.hasParameterAnnotation(RequestParam.class)) {
                System.out.println(methodName + "的第" + parameterIndex + "个参数" + parameterName + "类型为" + parameterType + "，上面有" + RequestParam.class + "注解");
            }

            if (methodParam.hasMethodAnnotation(RequestMapping.class) && methodParam.hasParameterAnnotation(CookieValue.class)) {
                System.out.println(methodName + "的第" + parameterIndex + "个参数" + parameterName + "类型为" + parameterType + "，上面有" + CookieValue.class + "注解");
            }
        }
    }

    /**
     * 通过 org.springframework.core.ResolvableType来解析泛型
     */
    @Test
    public void testResolvableType() {
        ResolvableType resolvableType = ResolvableType.forClass(TestAnnotationMethodParamParse.class);
        ResolvableType anInterface = resolvableType.getInterfaces()[0];
        System.err.println(anInterface.resolveGeneric(0));
        System.err.println(anInterface.resolveGeneric(0, 0));
        System.err.println(anInterface.resolveGeneric(1));

        ResolvableType methodParameter = ResolvableType.forMethodParameter(ReflectionUtils.findMethod(TestAnnotationMethodParamParse.class, "hello", List.class, Integer.class), 0);
        System.err.println(methodParameter.hasGenerics());
        System.err.println(methodParameter.resolveGeneric(0));
    }


    /**
     * 解析方法参数的参数名，参数泛型，参数注解
     *
     * @param method
     * @return java.util.List<org.springframework.core.annotation.SynthesizingMethodParameter>
     * @author matas
     * @date 2018/7/27 19:32
     * @see HandlerMethod#initMethodParameters()  初始化  HandlerMethodParameter
     * @see InvocableHandlerMethod#getMethodArgumentValues(NativeWebRequest, ModelAndViewContainer, java.lang.Object...)
     * @see HandlerMethodInvoker#resolveHandlerArguments(java.lang.reflect.Method, java.lang.Object, NativeWebRequest, ExtendedModelMap)
     * @see LocalVariableTableParameterNameDiscoverer#getParameterNames(java.lang.reflect.Method)  通过ASM来解析参数名  Uses ObjectWeb's ASM library for analyzing class files
     */
    public static List<SynthesizingMethodParameter> parse(Method method) {
        List<SynthesizingMethodParameter> parameterList = new ArrayList<>();
        Class<?> declaringClass = method.getDeclaringClass();

        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
        //https://www.cnblogs.com/zsg88/p/7588929.html  关于method.isBridge()
        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);

        for (int i = 0; i < method.getParameterCount(); i++) {
            SynthesizingMethodParameter methodParam = new SynthesizingMethodParameter(bridgedMethod, i);
            methodParam.initParameterNameDiscovery(nameDiscoverer);
            GenericTypeResolver.resolveParameterType(methodParam, declaringClass);
            parameterList.add(methodParam);
        }

        return parameterList;
    }

}


interface IHello<T, E> {
    void hello(T name, E age);
}