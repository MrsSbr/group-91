package logic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneratorSql {

    public static String getTableName(Class<?> object) {
        return object.getAnnotation(TableName.class).name();
    }

    public static String getFieldsName(Class<?> object) {
        return Arrays.stream(object.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.joining(", "));
    }

    public static String getFieldValue(Field field, Object values) {
        field.setAccessible(true);
        Object value;
        try {
            value = field.get(values);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value.toString();
    }

    public static String getFieldsValues(Object values) {
        return Arrays.stream(values.getClass().getDeclaredFields())
                .map(field -> getFieldValue(field, values))
                .collect(Collectors.joining(", "));
    }

    public static String getSelectQuery(Class<?> object) {
        return "SELECT " + getFieldsName(object) + " FROM \"" + getTableName(object) + "\"";
    }

    public static String getInsertQuery(Object values) {
        return "INSERT INTO \"" + getTableName(values.getClass()) + "\" VALUES (" + getFieldsValues(values) + ")";
    }

    public static String getSetString(Object values) {
        return Arrays.stream(values.getClass().getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(IdPK.class))
                .map(f -> getEqualityString(f, values, f.getName()))
                .collect(Collectors.joining(", "));
    }

    public static String getUpdateQuery(Object values) {
        return "UPDATE \"" + getTableName(values.getClass()) + "\" SET " + getSetString(values) + " WHERE " + getCondition(values);
    }

    public static String getEqualityString(Field field, Object value, String name) {
        return name + " = " + getFieldValue(field, value);
    }

    public static String getCondition(Object values) {
        return Arrays.stream(values.getClass().getDeclaredFields())
                .filter(p -> p.isAnnotationPresent(IdPK.class))
                .map(f -> getEqualityString(f, values, f.getName()))
                .collect(Collectors.joining(", "));
    }

    public static String getDeleteQuery(Object values) {
        return "DELETE FROM \"" + getTableName(values.getClass()) + "\" WHERE " + getCondition(values);
    }
}
