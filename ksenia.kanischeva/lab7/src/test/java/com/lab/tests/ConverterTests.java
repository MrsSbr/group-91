package com.lab.tests;

import com.lab.annotation.StringCommand;
import com.lab.logic.StringConverter;
import com.lab.reflection.TestReflection;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;


import static com.lab.data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTests {
    private final Set<Method> converterMethods = TestReflection.getAnnotatedMethodsFrom(StringConverter.class);

    @Test
    public void convertStringToCamelCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = CAMEL_STRING;

        // when
        Method camelMethod = converterMethods.stream()
                .filter(method -> method.getAnnotation(StringCommand.class).value().equals(CAMEL_COMMAND_VALUE))
                .findFirst()
                .orElse(null);
        assert camelMethod != null;
        String actualString = camelMethod.invoke(StringConverter.class, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }


    @Test
    public void convertStringToSnakeCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = SNAKE_STRING;

        // when
        Method snakeMethod = converterMethods.stream()
                .filter(method -> method.getAnnotation(StringCommand.class).value().equals(SNAKE_COMMAND_VALUE))
                .findFirst()
                .orElse(null);
        String actualString = snakeMethod.invoke(StringConverter.class, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void convertStringToKebabCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = KEBAB_STRING;

        // when
        Method kebabMethod = converterMethods.stream()
                .filter(method -> method.getAnnotation(StringCommand.class).value().equals(KEBAB_COMMAND_VALUE))
                .findFirst()
                .orElse(null);
        String actualString = kebabMethod.invoke(StringConverter.class, SOMETHING_STRING).toString();

        // then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void convertStringToSomethingCase() throws InvocationTargetException, IllegalAccessException {
        // given
        String expectedString = NOT_FOUND;

        // when
        Method someMethod = converterMethods.stream()
                .filter(method -> method.getAnnotation(StringCommand.class).value().equals(SOMETHING_COMMAND_VALUE))
                .findFirst()
                .orElse(null);
        String actualString = someMethod != null ? someMethod.invoke(StringConverter.class, SOMETHING_STRING).toString() : NOT_FOUND;

        // then
        assertEquals(expectedString, actualString);
    }


}
