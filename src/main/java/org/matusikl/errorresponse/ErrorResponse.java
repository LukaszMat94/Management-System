package org.matusikl.errorresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class ErrorResponse implements Serializable {
    private String message;
    private int status;
    private ZonedDateTime dateTime;

    public ErrorResponse(String message, int status, ZonedDateTime dateTime ) {
        this.message = message;
        this.status = status;
        this.dateTime = dateTime;
    }

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
