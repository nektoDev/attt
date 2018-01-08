package ru.nektodev.attt.api.dto;

public enum MediaKind {
    MOVIE("movies"),
    SERIES("series");

    private final String defaultPath;
    MediaKind(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public String getDefaultPath() {
        return defaultPath;
    }
}
