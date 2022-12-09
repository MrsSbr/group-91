package com.lab.tests;

import com.lab.logic.StringConverter;
import com.lab.reflection.TestReflection;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.lab.data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationTests {

    public static final Class<?> classType = StringConverter.class;

    @Test
    public void getCamelCaseMethod() {
        // given
        String expectedMethodName = CAMEL_METHOD_NAME;

        // when
        String actualMethodName = TestReflection
                .getNameAnnotatedMethodWith(classType.getMethods(), CAMEL_COMMAND_VALUE);

        // then
        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public void getSnakeCaseMethod() {
        // given
        String expectedMethodName = SNAKE_METHOD_NAME;

        // when
        String actualMethodName = TestReflection
                .getNameAnnotatedMethodWith(classType.getMethods(), SNAKE_COMMAND_VALUE);

        // then
        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public void getKebabCaseMethod() {
        // given
        String expectedMethodName = KEBAB_METHOD_NAME;

        // when
        String actualMethodName = TestReflection
                .getNameAnnotatedMethodWith(classType.getMethods(), KEBAB_COMMAND_VALUE);

        // then
        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public void getSomethingCaseMethod() {
        // given
        String expectedMethodName = NOT_FOUND;

        // when
        String actualMethodName = Objects.requireNonNull(TestReflection
                .getNameAnnotatedMethodWith(classType.getMethods(), SOMETHING_COMMAND_VALUE));

        // then
        assertEquals(expectedMethodName, actualMethodName);
    }
}
