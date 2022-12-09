package com.lab.reflection;

import com.lab.annotation.StringCommand;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class TestReflection {

    public static Set<Method> getAnnotatedMethodsFrom(Class<?> classType) {
        return Arrays.stream(classType.getDeclaredMethods()).collect(Collectors.toSet());
    }


    public static <T> Class<? extends T> findImplementationOf(Class<T> classType) {
        Objects.requireNonNull(classType);
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("com.lab")
                        .setScanners(Scanners.SubTypes));

        return reflections.getSubTypesOf(classType)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Implementation not found"));
    }

    public static <T> T getInstanceOf(Class<T> classType){
        try {
            return classType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
