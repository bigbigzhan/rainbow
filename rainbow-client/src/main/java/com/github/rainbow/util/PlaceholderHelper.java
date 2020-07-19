package com.github.rainbow.util;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-27 13:51:59
 */
public class PlaceholderHelper {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";
    private static final String VALUE_SEPARATOR = ":";
    private static final String SIMPLE_PLACEHOLDER_PREFIX = "{";
    private static final String EXPRESSION_PREFIX = "#{";
    private static final String EXPRESSION_SUFFIX = "}";

    public Set<String> extractPlanceholderKeys(String propertyString){
        Set<String> placeHolderKeys = new HashSet<>();

        if(!isNormalPlaceholder(propertyString) && !isExpressionWithPlaceholder(propertyString)){
            return placeHolderKeys;
        }
        Stack<String> stack = new Stack();
        stack.push(propertyString);

        while (!stack.isEmpty()){
            String str = stack.pop();
            int startIndex = str.indexOf(PLACEHOLDER_PREFIX);
            if(startIndex == -1){
                placeHolderKeys.add(str);
                continue;
            }
            int endIndex = findPlaceholderEndIndex(str,startIndex);
            if (endIndex == -1){
                continue;
            }

            String placeholderCandidate = str.substring(startIndex + PLACEHOLDER_PREFIX.length(),endIndex);

            if(placeholderCandidate.startsWith(PLACEHOLDER_PREFIX)){
                stack.push(placeholderCandidate);
            }else {
                int separatorIndex = placeholderCandidate.indexOf(VALUE_SEPARATOR);

                if(separatorIndex == -1){
                    stack.push(placeholderCandidate);
                }else {
                    stack.push(placeholderCandidate.substring(0,separatorIndex));
                    String defaultValue =  normalizeToPlaceholder(placeholderCandidate.substring(separatorIndex + VALUE_SEPARATOR.length()));
                    if(!Strings.isNullOrEmpty(defaultValue)){
                        stack.push(defaultValue);
                    }

                }
            }
            // has remaining part, e.g. ${a}.${b}
            if (endIndex + PLACEHOLDER_SUFFIX.length() < str.length() - 1) {
                String remainingPart = normalizeToPlaceholder(str.substring(endIndex + PLACEHOLDER_SUFFIX.length()));
                if (!Strings.isNullOrEmpty(remainingPart)) {
                    stack.push(remainingPart);
                }
            }
        }
    return placeHolderKeys;
    }

    private String normalizeToPlaceholder(String strVal) {
        int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
        if (startIndex == -1) {
            return null;
        }
        int endIndex = strVal.lastIndexOf(PLACEHOLDER_SUFFIX);
        if (endIndex == -1) {
            return null;
        }

        return strVal.substring(startIndex, endIndex + PLACEHOLDER_SUFFIX.length());
    }
    private int findPlaceholderEndIndex(String str,int startIndex){
        int index = startIndex + PLACEHOLDER_PREFIX.length();
        int withinNestedPlaceholder = 0;
        while (index < str.length()){
            if(StringUtils.substringMatch(str,index,PLACEHOLDER_SUFFIX)){
                if(withinNestedPlaceholder > 0){
                    withinNestedPlaceholder -- ;
                    index = index + PLACEHOLDER_SUFFIX.length();
                }else {
                    return index;
                }
            }else if(StringUtils.substringMatch(str,index,SIMPLE_PLACEHOLDER_PREFIX)){
                withinNestedPlaceholder ++;
                index = index + SIMPLE_PLACEHOLDER_PREFIX.length();
            }else {
                index ++;
            }
        }
        return -1;
    }
    private boolean isNormalPlaceholder(String propertyString){
        return propertyString.startsWith(PLACEHOLDER_PREFIX) && propertyString.endsWith(PLACEHOLDER_SUFFIX);
    }
    private boolean isExpressionWithPlaceholder(String propertyString){
        return propertyString.startsWith(EXPRESSION_PREFIX) && propertyString.endsWith(EXPRESSION_SUFFIX) && propertyString.contains(PLACEHOLDER_PREFIX);
    }

    public Set<String> extractPlaceholderKeys(String propertyString) {
        Set<String> placeholderKeys = Sets.newHashSet();

        if (!isNormalPlaceholder(propertyString) && !isExpressionWithPlaceholder(propertyString)) {
            return placeholderKeys;
        }

        Stack<String> stack = new Stack<>();
        stack.push(propertyString);

        while (!stack.isEmpty()) {
            String strVal = stack.pop();
            int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
            if (startIndex == -1) {
                placeholderKeys.add(strVal);
                continue;
            }
            int endIndex = findPlaceholderEndIndex(strVal, startIndex);
            if (endIndex == -1) {
                // invalid placeholder?
                continue;
            }

            String placeholderCandidate = strVal.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);

            // ${some.key:other.key}
            if (placeholderCandidate.startsWith(PLACEHOLDER_PREFIX)) {
                stack.push(placeholderCandidate);
            } else {
                // some.key:${some.other.key:100}
                int separatorIndex = placeholderCandidate.indexOf(VALUE_SEPARATOR);

                if (separatorIndex == -1) {
                    stack.push(placeholderCandidate);
                } else {
                    stack.push(placeholderCandidate.substring(0, separatorIndex));
                    String defaultValuePart =
                            normalizeToPlaceholder(placeholderCandidate.substring(separatorIndex + VALUE_SEPARATOR.length()));
                    if (!Strings.isNullOrEmpty(defaultValuePart)) {
                        stack.push(defaultValuePart);
                    }
                }
            }

            // has remaining part, e.g. ${a}.${b}
            if (endIndex + PLACEHOLDER_SUFFIX.length() < strVal.length() - 1) {
                String remainingPart = normalizeToPlaceholder(strVal.substring(endIndex + PLACEHOLDER_SUFFIX.length()));
                if (!Strings.isNullOrEmpty(remainingPart)) {
                    stack.push(remainingPart);
                }
            }
        }

        return placeholderKeys;
    }

    public Object resolvePropertyValue(ConfigurableBeanFactory beanFactory,String beanName,String placeholder){
        String resolveEmbeddedValue = beanFactory.resolveEmbeddedValue(placeholder);
        BeanDefinition bd = beanFactory.containsBean(beanName) ? beanFactory.getMergedBeanDefinition(beanName) : null;
        return evaluateBeanDefinitionToString(beanFactory,resolveEmbeddedValue,bd);
    }
    private Object evaluateBeanDefinitionToString(ConfigurableBeanFactory beanFactory,String value,BeanDefinition beanDefinition){
        if(beanFactory.getBeanExpressionResolver() == null){
            return value;
        }
        Scope scope = beanDefinition != null ? beanFactory.getRegisteredScope(beanDefinition.getScope()) : null;
        return beanFactory.getBeanExpressionResolver().evaluate(value,new BeanExpressionContext(beanFactory,scope));
    }



}
