package ru.nektodev.attt.model;

public class TransmissionException extends Exception {
    public TransmissionException() {
    }

    public TransmissionException(String message) {
        super(message);
    }

    public TransmissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransmissionException(Throwable cause) {
        super(cause);
    }

    public TransmissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
