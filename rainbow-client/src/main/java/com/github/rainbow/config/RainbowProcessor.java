package com.github.rainbow.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gz.
 * @description: RainbowProcessor
 * @date 2020-06-27 13:00:06
 */
public abstract class RainbowProcessor implements BeanPostProcessor, PriorityOrdered {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        findAllField(bean.getClass()).forEach(field->{
            processField(bean,beanName,field);
        });
        findAllMethods(bean.getClass()).forEach(method -> {
            processMethod(bean,beanName,method);
        });

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    //Spring中 数值越大顺序越后
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    protected abstract void processField(Object bean,String beanName,Field field);

    protected abstract void processMethod(Object bean,String beanName,Method method);


    private List<Field> findAllField(Class clazz){
        List<Field> list = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz,new ReflectionUtils.FieldCallback(){

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                list.add(field);
            }
        });
        return list;
    }

    private List<Method> findAllMethods(Class clazz){
        List<Method> list = new ArrayList<>();
        ReflectionUtils.doWithMethods(clazz,new ReflectionUtils.MethodCallback(){
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                list.add(method);
            }
        });
        return list;
    }
}
