package com.springboot.University.DTO;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private int status;

    public ErrorResponseDTO(String message, String path, int status) {
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
}
