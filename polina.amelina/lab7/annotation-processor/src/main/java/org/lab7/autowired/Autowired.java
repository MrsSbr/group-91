package org.lab7.autowired;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
public @interface Autowired {}