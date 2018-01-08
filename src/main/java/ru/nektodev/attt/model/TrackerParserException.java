package ru.nektodev.attt.model;

public class TrackerParserException extends Exception {
    public TrackerParserException() {
    }

    public TrackerParserException(String message) {
        super(message);
    }

    public TrackerParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackerParserException(Throwable cause) {
        super(cause);
    }

    public TrackerParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
