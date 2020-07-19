package com.github.rainbow.property;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-27 15:18:35
 */
public class SpringValueRegistry {

    private  Multimap<String, SpringValue> registry = LinkedListMultimap.create();

    public void register(String key,SpringValue springValue){
        registry.put(key,springValue);
    }

    public Collection<SpringValue> get(String key){
        return registry.get(key);
    }
}
