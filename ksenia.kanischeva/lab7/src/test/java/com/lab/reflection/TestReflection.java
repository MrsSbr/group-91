package com.lab.reflection;

import com.lab.annotation.StringCommand;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lab.data.TestData.NOT_FOUND;


public class TestReflection {

    public static Set<Method> getAnnotatedMethodsFrom(Class<?> classType) {
        return Arrays.stream(classType.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(StringCommand.class)).collect(Collectors.toSet());
    }

    public static String getNameAnnotatedMethodWith(Method[] methods, String value) {
        Method annotatedMethod;
        try {
            annotatedMethod = Arrays.stream(methods)
                    .filter(method -> method.getAnnotation(StringCommand.class).value().equals(value))
                    .findFirst()
                    .orElseThrow();
        } catch (NullPointerException exception) {
            return NOT_FOUND;
        }
        return annotatedMethod.getName();
    }
}
