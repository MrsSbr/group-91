package com.lab.tests;

import com.lab.logic.StringConverter;
import com.lab.reflection.TestReflection;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;


import static com.lab.data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTests {
    private final Class<?> classImpl = TestReflection.findImplementationOf(StringConverter.class);
    private final Set<Method> converterMethods = TestReflection.getAnnotatedMethodsFrom(classImpl);
    private final Object instanceCl = TestReflection.getInstanceOf(classImpl);

    @Test
    public void convertStringToCamelCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = CAMEL_STRING;

        // when
        Method camelMethod = converterMethods.stream()
                .filter(method -> method.getName().toLowerCase().contains(CAMEL_METHOD))
                .findFirst()
                .orElse(null);
        assert camelMethod != null;
        String actualString = camelMethod.invoke(instanceCl, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }


    @Test
    public void convertStringToSnakeCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = SNAKE_STRING;

        // when
        Method snakeMethod = converterMethods.stream()
                .filter(method -> method.getName().toLowerCase().contains(SNAKE_METHOD))
                .findFirst()
                .orElse(null);
        assert snakeMethod != null;
        String actualString = snakeMethod.invoke(instanceCl, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void convertStringToKebabCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = KEBAB_STRING;

        // when
        Method kebabMethod = converterMethods.stream()
                .filter(method -> method.getName().toLowerCase().contains(KEBAB_METHOD))
                .findFirst()
                .orElse(null);
        assert kebabMethod != null;
        String actualString = kebabMethod.invoke(instanceCl, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void convertStringToSomethingCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = NOT_FOUND;

        // when
        Method someMethod = converterMethods.stream()
                .filter(method -> method.getName().contains(SOMETHING_STRING))
                .findFirst()
                .orElse(null);
        String actualString = someMethod != null ? someMethod.invoke(instanceCl, SOMETHING_STRING).toString() : NOT_FOUND;

        // then
        assertEquals(expectedString, actualString);
    }


}
