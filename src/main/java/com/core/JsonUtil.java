package com.core;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Created by hellow on 2016/12/12.
 */
public class JsonUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();

    private static TypeFactory TYPE_FACTORY = objectMapper.getTypeFactory();


    public static ObjectMapper getObjectMapper(){


        return objectMapper;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return TYPE_FACTORY.constructParametricType(collectionClass, elementClasses);
    }


}
