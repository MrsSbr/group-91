package com.lab.logic;

import com.lab.annotation.StringCommand;

public class StringConverter {

    @StringCommand("camelCase")
    public static String convertToCamelCase(String str) {
        return "camelCase";
    }

    @StringCommand("snake_case")
    public static String convertToSnakeCase(String str) {
        return "snake_case";
    }

    @StringCommand("kebab-case")
    public static String convertToKebabCase(String str) {
        return "kebab-case";
    }

    @StringCommand("")
    public static String incorrectMethod(String str) {
        return "";
    }
}
