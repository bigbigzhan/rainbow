package com.github.rainbow.annotations;

import com.github.rainbow.register.RainbowConfigCenterRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-28 23:29:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RainbowConfigCenterRegister.class)
public @interface EnableRainbowConfigCenter {
}
