package com.example.challenge;

public class ImageInfo {
    String path;
    String displayName;
    String dateInput;

    public ImageInfo(String path, String displayName, String dateInput) {
        this.path = path;
        this.displayName = displayName;
        this.dateInput = dateInput;
    }

    public String getPath() {
        return path;
    }

    public String getDateInput() {
        return dateInput;
    }
}
