package com.springBoot1.SB2.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ObjectUtil {
    private final static String SETTER_METHOD_PREFIX = "set";
    private final static String GETTER_METHOD_PREFIX = "get";

    public static void copyNotNullProperties(Object source, Object target) {
        //TODO-->
        // 1. get all fields-->done
        // 2. loop through-->done
        // 3. check if it has getter
        // 4. if it has, Invoke this and save its value
        // 5. check if target has setter with same name after using capitalize
        // 6. invoke setters pass args
        Class<?> sourceZZ = source.getClass();
        Class<?> targetZZ = target.getClass();
        Object[] fieldNames = Arrays.stream(sourceZZ.getDeclaredFields()).map(field -> StringUtils.capitalize(field.getName())).toArray();
        for (Object field : fieldNames) {
            //check if source has setter method
            try {
                Method getterMethod = sourceZZ.getMethod(GETTER_METHOD_PREFIX + field);
                String getterReturn = (String) getterMethod.invoke(source);
                if (!Objects.isNull(getterReturn)) {
                    Method settrMethod = targetZZ.getMethod(SETTER_METHOD_PREFIX + field, String.class);
                    settrMethod.invoke(target, getterReturn);
                }
            } catch (Exception exception) {
                System.out.println("Something went wrong in mapping setters.");
            }
        }
    }
}
