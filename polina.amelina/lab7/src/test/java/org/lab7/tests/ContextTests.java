package org.lab7.tests;

import org.junit.Test;
import org.lab7.Context;
import org.lab7.data.goodData.ComponentAutowiredField;
import org.lab7.data.goodData.ComponentNoFields;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ContextTests {

    @Test
    public void contextGetsCorrectValues() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // given
        Context context;

        // when
        context = new Context("org.lab7.data.goodData");

        // then
        assertTrue(context.get(ComponentNoFields.class) instanceof ComponentNoFields);
        assertTrue(context.get(ComponentAutowiredField.class) instanceof ComponentAutowiredField);
        assertNotNull(((ComponentAutowiredField) context.get(ComponentAutowiredField.class)).service1);
    }

    @Test
    public void contextFailsNoDefaultConstructor() {
        assertThrows(NoSuchMethodException.class, () -> new Context("org.lab7.data.badData"));
    }
}