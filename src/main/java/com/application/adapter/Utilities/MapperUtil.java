package com.application.adapter.Utilities;

import org.springframework.beans.BeanUtils;

public class MapperUtil {

    public static<T, P> void convertObject(T t,P p) {
        BeanUtils.copyProperties(t, p);
    }

    public static<T, P> P mappingObject(T t, P p) {
        BeanUtils.copyProperties(t, p);
        return p;
    }

}
