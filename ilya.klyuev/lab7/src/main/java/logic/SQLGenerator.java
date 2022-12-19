package logic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SQLGenerator {

    private static String getFieldsNames(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .map(Field::getName)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
    }

    private static String getWhereByPrimaryKey(Object entityInstance) throws EntityException {
        PrimaryKey entity = entityInstance.getClass().getAnnotation(PrimaryKey.class);

        if (entity == null) {
            throw new EntityException("not find entity annotation");
        }

        try {
            Field primaryKey = entityInstance.getClass().getDeclaredField(entity.value());
            primaryKey.setAccessible(true);
            return "WHERE " +
                    primaryKey.getName().toUpperCase() +
                    " = " +
                    primaryKey.get(entityInstance) +
                    ";";

        } catch (NoSuchFieldException e) {
            throw new EntityException("not find primary key");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSelectQuery(Class<?> entity) {
        var fieldsNames = getFieldsNames(entity);

        return "SELECT " +
                fieldsNames +
                " FROM " +
                entity.getSimpleName().toUpperCase() +
                ";";
    }

    public static String getInsertQuery(Object entityInstance) {
        var insertQueryBuilder = new StringBuilder()
                .append("INSERT INTO ")
                .append(entityInstance.getClass().getSimpleName().toUpperCase())
                .append("(")
                .append(getFieldsNames(entityInstance.getClass()))
                .append(") VALUES(");

        var values = Arrays.stream(entityInstance.getClass().getDeclaredFields())
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(entityInstance).toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(", "));

        return insertQueryBuilder
                .append(values)
                .append(");")
                .toString();
    }

    public static String getUpdateQuery(Object entityInstance) throws EntityException {
        String primaryKey = entityInstance.getClass().getAnnotation(PrimaryKey.class).value();

        var fields = Arrays.stream(entityInstance.getClass().getDeclaredFields())
                .filter(field -> !field.getName().equalsIgnoreCase(primaryKey))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.getName().toUpperCase() + " = " + field.get(entityInstance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(", "));

        return "UPDATE " +
                entityInstance.getClass().getSimpleName().toUpperCase() +
                " SET " +
                fields +
                " " +
                getWhereByPrimaryKey(entityInstance);
    }

    public static String getDeleteQuery(Object entityInstance) throws EntityException {
        return "DELETE FROM " +
                entityInstance.getClass().getSimpleName().toUpperCase() +
                " " +
                getWhereByPrimaryKey(entityInstance);
    }
}
