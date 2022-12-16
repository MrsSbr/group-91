package org.lab7;

import org.lab7.autowired.Autowired;
import org.lab7.component.Component;
import org.lab7.methodNameGetter.MethodNameGetter;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Context {

    private final Logger logger = Logger.getGlobal();
    private final Set<Object> instances = new HashSet<>();

    public Context() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage("org.lab7")
                .setScanners(Scanners.TypesAnnotated, Scanners.FieldsAnnotated));

        Set<Class<?>> components = reflections.get(Scanners.TypesAnnotated.with(Component.class).asClass());

        initializeInstances(components);
        initializeFields(components);
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
                 | InstantiationException
                 | InvocationTargetException e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }
    }

    private void initializeFields(Set<Class<?>> components)
            throws IllegalAccessException {

        try {
            for (Class<?> component : components) {
                for (Field field : component.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        field.setAccessible(true);

                        Object componentInstance = instances
                                .stream()
                                .filter(x -> x.getClass().equals(component))
                                .findFirst();

                        field.set(component, componentInstance);
                    }
                }
            }

        } catch (IllegalAccessException
                 | ExceptionInInitializerError e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }
    }

    public Object get(Class<?> component) {
        return instances
                .stream()
                .filter(x -> x.getClass().equals(component))
                .findFirst();
    }
}
