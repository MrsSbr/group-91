package org.lab7.component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface Component {}