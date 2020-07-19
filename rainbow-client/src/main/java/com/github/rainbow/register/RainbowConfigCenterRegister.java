package com.github.rainbow.register;

import com.github.rainbow.config.RainbowServerConfig;
import com.github.rainbow.config.SpringValueProcessor;
import com.github.rainbow.util.BeanRegistraUtil;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-28 23:25:27
 */
public class RainbowConfigCenterRegister  implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        BeanRegistraUtil.registerBeanDefinitionIfNotExists(registry, PropertySourcesPlaceholderConfigurer.class.getName(),PropertySourcesPlaceholderConfigurer.class);

        BeanRegistraUtil.registerBeanDefinitionIfNotExists(registry, SpringValueProcessor.class.getName(),SpringValueProcessor.class);

    }

}
