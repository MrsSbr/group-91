package org.lab7;

import org.lab7.autowired.Autowired;
import org.lab7.component.Component;
import org.lab7.methodNameGetter.MethodNameGetter;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Context {

    private final Logger logger = Logger.getGlobal();
    private final Set<Object> instances = new HashSet<>();

    public Context(String pkg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage(pkg)
                .setScanners(Scanners.TypesAnnotated)
                .filterInputsBy(new FilterBuilder().includePackage(pkg)));

        Set<Class<?>> components = reflections.get(Scanners.TypesAnnotated.with(Component.class).asClass());

        initializeInstances(components);
        initializeFields();
    }

    private void initializeInstances(Set<Class<?>> components)
            throws InvocationTargetException,
            NoSuchMethodException,
            IllegalAccessException,
            InstantiationException {

        try {
            for (Class<?> component : components) {
                Constructor<?> constructor = component.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object instance = constructor.newInstance();
                instances.add(instance);
            }

        } catch (NoSuchMethodException
                 | IllegalAccessException
                 | InvocationTargetException e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }
    }

    private void initializeFields()
            throws IllegalAccessException {

        try {
            for (Object instance : instances) {
                for (Field field : Arrays
                        .stream(instance.getClass().getDeclaredFields())
                        .filter(x -> x.isAnnotationPresent(Autowired.class))
                        .collect(Collectors.toSet())) {

                    field.setAccessible(true);

                    Object componentInstance = instances
                            .stream()
                            .filter(x -> x.getClass().equals(field.getType()))
                            .findFirst()
                            .orElseThrow();

                    field.set(instance, componentInstance);
                }
            }

        } catch (IllegalAccessException e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }
    }

    public Object get(Class<?> component) {

        return instances
                .stream()
                .filter(x -> x.getClass().equals(component))
                .findFirst()
                .orElseThrow();
    }
}