package com.trs.cc.discountcode.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class NullAwareBean extends BeanUtilsBean {
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        if(value instanceof String) {
            if (StringUtils.isEmpty(value)) return;
        }
        if (value instanceof Float) {
            if (value.equals(0.0f)) return;
        }
        if (value instanceof List) {
            if (((List) value).isEmpty()) return;
        }
        super.copyProperty(dest, name, value);
    }
}
