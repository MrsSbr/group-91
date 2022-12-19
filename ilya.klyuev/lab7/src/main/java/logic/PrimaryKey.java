package logic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface PrimaryKey {
    String value() default "ID";
}
