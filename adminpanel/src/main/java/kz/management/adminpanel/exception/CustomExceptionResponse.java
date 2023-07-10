package kz.management.adminpanel.exception;

import java.time.LocalDateTime;

public class CustomExceptionResponse {
    private String message;
    private LocalDateTime timeStamp;

    public CustomExceptionResponse(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
