package com.github.rainbow.common;

import com.github.rainbow.config.SpringValueProcessor;
import com.github.rainbow.property.SpringValueDefinitionProcessor;
import com.github.rainbow.util.BeanRegistraUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-27 15:48:18
 */
public class RainbowPropertySourcesProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        BeanRegistraUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry, PropertySourcesPlaceholderConfigurer.class.getName(),PropertySourcesPlaceholderConfigurer.class);

        BeanRegistraUtil.registerBeanDefinitionIfNotExists(beanDefinitionRegistry, SpringValueProcessor.class.getName(),SpringValueProcessor.class);

        SpringValueDefinitionProcessor springValueDefinitionProcessor = new SpringValueDefinitionProcessor();
        springValueDefinitionProcessor.postProcessBeanDefinitionRegistry(beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
