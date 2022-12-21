package logic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
public @interface PrimaryKey {
}
