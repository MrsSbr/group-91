package com.lab.logic;

import com.lab.annotation.StringCommand;

public class StringConverter {

    @StringCommand("camelCase")
    public String convertToCamelCase(String str) {
        return "camelCase";
    }

    @StringCommand("snake_case")
    public String convertToSnakeCase(String str) {
        return "snake_case";
    }

    @StringCommand("kebab-case")
    public String convertToKebabCase(String str) {
        return "kebab-case";
    }

}
