package logic;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SQLGenerator {

    // генерация запроса на получение
    public static String getSelectQuery(Class<?> entity) throws TableException {
        return "SELECT " +
                getFieldsNames(entity) +
                " FROM " +
                getEntityName(entity) +
                ";";
    }

    // генерация запроса на вставку
    public static String getInsertQuery(Object entityInstance) throws TableException {
        var insertQueryBuilder = new StringBuilder()
                .append("INSERT INTO ")
                .append(getEntityName(entityInstance))
                .append("(")
                .append(getFieldsNames(entityInstance.getClass()))
                .append(") VALUES(");

        var values = Arrays.stream(entityInstance.getClass().getDeclaredFields())
                .map(field -> getFieldValue(field, entityInstance))
                .collect(Collectors.joining(", "));

        return insertQueryBuilder
                .append(values)
                .append(");")
                .toString();
    }

    // генерация запроса на обновление
    public static String getUpdateQuery(Object entityInstance) throws TableException {
        var primaryKeys = getPrimaryKeys(entityInstance.getClass());

        var fields = Arrays.stream(entityInstance.getClass().getDeclaredFields())
                .filter(field -> !primaryKeys.contains(field))
                .map(field -> getFieldNameWithValue(field, entityInstance))
                .collect(Collectors.joining(", "));

        return "UPDATE " +
                getEntityName(entityInstance) +
                " SET " +
                fields +
                " " +
                getWhereStatementByFields(primaryKeys, entityInstance);
    }

    // генерация запроса на удаление
    public static String getDeleteQuery(Object entityInstance) throws TableException {
        return "DELETE FROM " +
                getEntityName(entityInstance) +
                " " +
                getWhereStatementByPrimaryKeys(entityInstance);
    }

    // получить имя сущности в верхнем регистре
    private static String getEntityName(Class<?> entity) throws TableException {
        Table table = entity.getAnnotation(Table.class);
        if (table == null) {
            throw new TableException("This is not table");
        }
        return "\"" +
                doubleCharacter(table.name().toUpperCase(), '"') +
                "\"";
    }

    // получить имя сущности по её экземляру в верхнем регистре
    private static String getEntityName(Object entity) throws TableException {
        return getEntityName(entity.getClass());
    }

    // получить имя атрибута в вехнем регистре
    private static String getFieldName(Field field) {
        return field.getName().toUpperCase();
    }

    // удвоить все вхождения символа в строке
    private static String doubleCharacter(String str, char c) {
        return str.chars()
                .mapToObj(x -> ((char) x) == c ? String.valueOf(c) + c : String.valueOf((char) x))
                .collect(Collectors.joining());
    }

    // получить значение атрибута
    private static String getFieldValue(Field field, Object entityInstance) {
        // получаем значение
        field.setAccessible(true);
        Object value;
        try {
            value = field.get(entityInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (field.getType() != String.class) {
            return value.toString();
        }

        // если строка, то добавялем апострофы в начале и конце, экранируем апострофы в середине
        return "'" +
                doubleCharacter((String) value, '\'') +
                "'";
    }

    private static String getFieldNameWithValue(Field field, Object entityInstance) {
        return getFieldName(field) + " = " + getFieldValue(field, entityInstance);
    }

    // получить первичные ключи сущности
    private static Set<Field> getPrimaryKeys(Class<?> entity) throws TableException {
        var result = Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .collect(Collectors.toSet());
        if (result.isEmpty()) {
            throw new TableException("Primary key not found");
        }
        return result;
    }

    // получить имена атрибутов, перечисленных через запятую
    private static String getFieldsNames(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .map(SQLGenerator::getFieldName)
                .collect(Collectors.joining(", "));
    }

    // получить выражение WHERE, используя в качестве условия значения заданных полей
    private static String getWhereStatementByFields(Set<Field> fields, Object entityInstance) {
        return "WHERE " +
                fields.stream()
                        .map(field -> getFieldNameWithValue(field, entityInstance))
                        .collect(Collectors.joining(", "))
                + ";";
    }

    // получить выражение WHERE, используя в качетсве условия значения первичных ключей
    private static String getWhereStatementByPrimaryKeys(Object entityInstance) throws TableException {
        var primaryKeys = getPrimaryKeys(entityInstance.getClass());
        return getWhereStatementByFields(primaryKeys, entityInstance);
    }
}