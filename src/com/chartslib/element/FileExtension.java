package com.chartslib.element;

public enum FileExtension {
    PDF("pdf"),PNG("png"),BMP("bmp"),JPG("jpg");

    private final String value;
    FileExtension(String value){this.value = value;}
    public String getValue() {
        return value;
    }
}
