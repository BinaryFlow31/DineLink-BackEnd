package com.dinelink.entities;

public class ModeratorErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public ModeratorErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ModeratorErrorResponse() {
    }

    @Override
    public String toString() {
        return "ModeratorErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
