package com.fonon.landingserver.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public final class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    public static String[] getNullPropertyNames(Object source, String... ignore) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        for (String ignoreProperty : ignore) {
            emptyNames.add(ignoreProperty);
        }
        return emptyNames.toArray(new String[0]);
    }
}
