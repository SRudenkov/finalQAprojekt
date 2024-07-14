package com.idacademy.Enums;

public enum Capability {
    BROWSER("browser", "chrome");

    private String key;
    private String defaultValue;
    Capability(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
}
