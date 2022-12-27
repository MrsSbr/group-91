package logic;

import data.annotations.NotNull;
import data.annotations.PrimaryKey;
import data.annotations.Table;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SQLQueryGenerator {

    public static String getSelectQuery(Class<?> entity) throws TableException {
        return "SELECT " +
                getFieldsNames(entity) +
                " FROM " +
                getEntityName(entity) +
                ";";
    }

    public static String getInsertQuery(Object obj) throws TableException {
        String fieldsWithValues = Arrays.stream(obj.getClass().getDeclaredFields())
                .map(field -> getFieldValue(field, obj))
                .collect(Collectors.joining(", "));

        return "INSERT INTO " +
                getEntityName(obj) +
                " (" +
                getFieldsNames(obj.getClass()) +
                ") VALUES(" +
                fieldsWithValues +
                ");";
    }

    public static String getUpdateQuery(Object obj) throws TableException {
        Set<Field> primaryKeys = getPrimaryKeys(obj.getClass());

        String fields = Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> !primaryKeys.contains(field))
                .map(field -> getFieldNameWithValue(field, obj))
                .collect(Collectors.joining(", "));

        return "UPDATE " +
                getEntityName(obj) +
                " SET " +
                fields +
                " " +
                getWhereUsingFields(primaryKeys, obj);
    }

    public static String getDeleteQuery(Object obj) throws TableException {
        return "DELETE FROM " +
                getEntityName(obj) +
                " " +
                getWhereUsingPrimaryKeys(obj);
    }

    private static String getEntityName(Class<?> entity) throws TableException {
        Table table = entity.getAnnotation(Table.class);
        if (table == null) {
            throw new TableException("This is not table");
        }

        return '\"' + StringEscapeUtils.escapeSql(table.name().toUpperCase()) + '\"';
    }

    private static String getEntityName(Object obj) throws TableException {
        return getEntityName(obj.getClass());
    }

    private static String getFieldName(Field field) {
        return field.getName().toUpperCase();
    }

    private static String getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        Object value;
        try {
            value = field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (field.getType() != String.class) {
            return value.toString();
        }

        return "'" + StringEscapeUtils.escapeSql((String) value) + "'";
    }

    private static String getFieldNameWithValue(Field field, Object obj) {
        return getFieldName(field) + " = " + getFieldValue(field, obj);
    }

    private static Set<Field> getPrimaryKeys(Class<?> entity) throws TableException {
        Set<Field> result = Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .collect(Collectors.toSet());
        if (result.isEmpty()) {
            throw new TableException("Primary key not found");
        }

        return result;
    }

    //Если выбрасывать экспешены, то нужен отдельный AnnotationProcessor
    private static Set<Field> getNotNullFields(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .collect(Collectors.toSet());
    }

    private static String getFieldsNames(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .map(SQLQueryGenerator::getFieldName)
                .collect(Collectors.joining(", "));
    }

    private static String getWhereUsingFields(Set<Field> fields, Object obj) {
        return "WHERE " +
                fields.stream()
                        .map(field -> getFieldNameWithValue(field, obj))
                        .collect(Collectors.joining(", "))
                + ";";
    }

    private static String getWhereUsingPrimaryKeys(Object obj) throws TableException {
        return getWhereUsingFields(getPrimaryKeys(obj.getClass()), obj);
    }
}
