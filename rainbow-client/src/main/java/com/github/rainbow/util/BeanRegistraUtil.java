package com.github.rainbow.util;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Objects;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-27 15:50:46
 */
public class BeanRegistraUtil {

    public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry, String beanName, Class<?> clazz){
        if(registry.containsBeanDefinition(beanName)){
            return false;
        }

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if(Objects.equals(beanDefinition.getBeanClassName(),clazz.getName())){
                return false;
            }
        }
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(clazz).getBeanDefinition();
        registry.registerBeanDefinition(beanName,beanDefinition);
        return true;
    }
}
