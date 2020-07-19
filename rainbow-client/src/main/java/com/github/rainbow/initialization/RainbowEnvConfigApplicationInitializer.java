package com.github.rainbow.initialization;

import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;

/**
 * @author Gz.
 * @description: rainbow配置数据加载类
 * @date 2020-07-07 22:59:28
 */
public class RainbowEnvConfigApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.fillSystemPropertyFromEnvironment(environment);
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        this.fillSystemPropertyFromEnvironment(environment);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void fillSystemPropertyFromEnvironment(ConfigurableEnvironment environment){
        RainbowConfig.setProperties(RainbowConstants.RAINBOW_ADDRESS, Objects.requireNonNull(environment.getProperty(RainbowConstants.RAINBOW_ADDRESS, String.class)));
        RainbowConfig.setProperties(RainbowConstants.RAINBOW_ENV, Objects.requireNonNull(environment.getProperty(RainbowConstants.RAINBOW_ENV, String.class)));
        RainbowConfig.setProperties(RainbowConstants.RAINBOW_APPNAME, Objects.requireNonNull(environment.getProperty(RainbowConstants.RAINBOW_APPNAME, String.class)));
        RainbowConfig.setProperties(RainbowConstants.RAINBOW_GROUPSNAME, Objects.requireNonNull(environment.getProperty(RainbowConstants.RAINBOW_GROUPSNAME, String.class)));
        RainbowConfig.setProperties(RainbowConstants.RAINBOW_CONFIG_LOCAL_PATH, Objects.requireNonNull(environment.getProperty(RainbowConstants.RAINBOW_CONFIG_LOCAL_PATH, String.class)));



    }
}
