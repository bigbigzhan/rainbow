package com.github.rainbow.config;

import com.github.rainbow.property.SpringValue;
import com.github.rainbow.property.SpringValueDefinition;
import com.github.rainbow.property.SpringValueDefinitionProcessor;
import com.github.rainbow.property.SpringValueRegistry;
import com.github.rainbow.util.PlaceholderHelper;
import com.github.rainbow.util.SpringInjector;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Gz.
 * @description: SpringValueProcessor
 * @date 2020-06-27 13:31:44
 */
public class SpringValueProcessor extends RainbowProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SpringValueProcessor.class);

    private static Multimap<String, SpringValueDefinition> beanName2SpringValueDefinitions =
            LinkedListMultimap.create();

    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    private final PlaceholderHelper placeholderHelper;

    private final SpringValueRegistry springValueRegistry;


    public SpringValueProcessor() {
        this.placeholderHelper = SpringInjector.getInstance(PlaceholderHelper.class);
        this.springValueRegistry = SpringInjector.getInstance(SpringValueRegistry.class);
    }


    /**
     *  get SpringFrameWork @Value Annotation value.if the value can not be achieved, return.
     * @param bean
     * @param beanName
     * @param field
     */
    @Override
    protected void processField(Object bean, String beanName, Field field) {
        Value value = field.getAnnotation(Value.class);
        if(null == value){
            return;
        }
        Set<String> keys = placeholderHelper.extractPlanceholderKeys(value.value());
        if(keys.isEmpty()){
            return;
        }

        keys.forEach(key->{
            SpringValue springValue = new SpringValue(key,value.value(),bean,beanName,field,false);
            //把配置相同key存入 map中
            // 配置项key 作为map的key class文件作为list
            //例如Map<String,List<Bean> keyMap = new HashMap();
            springValueRegistry.register(key,springValue);
            logger.info("key=[{}],value=[{}]",key,springValue);
        });

    }

    @Override
    protected void processMethod(Object bean, String beanName, Method method) {
   //    logger.error("error processMethod is not impl");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanName2SpringValueDefinitions = SpringValueDefinitionProcessor
                .getBeanName2SpringValueDefinitions();
        RainbowConfigHelper.getInstance().setConfigurableListableBeanFactory(configurableListableBeanFactory);
    }


    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        processPropertyValues(registry);
    }

    private void processPropertyValues(BeanDefinitionRegistry registry) {
        if(!initialized.compareAndSet(false,true)){
            return;
        }

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            List<PropertyValue> propertyValueList = propertyValues.getPropertyValueList();
            for (PropertyValue propertyValue : propertyValueList) {
                Object value = propertyValue.getValue();
                if(!(value instanceof TypedStringValue)){
                    continue;
                }
                String placeholder = ((TypedStringValue) value).getValue();
                Set<String> keys = placeholderHelper.extractPlaceholderKeys(placeholder);

                if(keys.isEmpty()){
                    continue;
                }
                keys.forEach(key->{
                    beanName2SpringValueDefinitions.put(beanDefinitionName,new SpringValueDefinition(key,placeholder,propertyValue.getName()));
                });

            }
        }
    }
}
