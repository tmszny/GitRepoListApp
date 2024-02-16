package com.example.GitRepoListApp.data;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(HttpClientErrorException e) {
        this.status = e.getStatusCode().value();
        this.message = e.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

