package com.idacademy.utils;

public enum PropertyFile {
    CONFIG("config.properties");

    private String pathToFile;

    PropertyFile(String pathToFile) {  this.pathToFile = pathToFile; }

    public String getPathToFile() {
        return pathToFile;
    }
}
