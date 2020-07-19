package com.github.rainbow.property;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-27 15:14:42
 */
public class SpringValue {
    private MethodParameter methodParameter;
    private Field field;
    private Object bean;
    private String beanName;
    private String key;
    private String placeholder;
    private Class<?> targetType;
    private Type genericType;
    private boolean isJson;

    public SpringValue(String key, String placeholder, Object bean, String beanName, Field field, boolean isJson) {
        this.bean = bean;
        this.beanName = beanName;
        this.field = field;
        this.key = key;
        this.placeholder = placeholder;
        this.targetType = field.getType();
        this.isJson = isJson;
        if(isJson){
            this.genericType = field.getGenericType();
        }
    }

    public SpringValue(String key, String placeholder, Object bean, String beanName, Method method, boolean isJson) {
        this.bean = bean;
        this.beanName = beanName;
        this.methodParameter = new MethodParameter(method, 0);
        this.key = key;
        this.placeholder = placeholder;
        Class<?>[] paramTps = method.getParameterTypes();
        this.targetType = paramTps[0];
        this.isJson = isJson;
        if(isJson){
            this.genericType = method.getGenericParameterTypes()[0];
        }
    }

    public void update(Object newVal) throws IllegalAccessException, InvocationTargetException {
        if(isField()){
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(bean,newVal);
            field.setAccessible(accessible);
        }else {
            methodParameter.getMethod().invoke(bean,newVal);
        }
    }

    public MethodParameter getMethodParameter() {
        return methodParameter;
    }

    public Field getField() {
        return field;
    }



    public Object getBean() {
        return bean;
    }

    public Boolean isField(){
        return this.field != null;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getKey() {
        return key;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public Type getGenericType() {
        return genericType;
    }

    public boolean isJson() {
        return isJson;
    }

    @Override
    public String toString() {
        return "SpringValue{" +
                "methodParameter=" + methodParameter +
                ", field=" + field +
                ", bean=" + bean +
                ", beanName='" + beanName + '\'' +
                ", key='" + key + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", targetType=" + targetType +
                ", genericType=" + genericType +
                ", isJson=" + isJson +
                '}';
    }
}
