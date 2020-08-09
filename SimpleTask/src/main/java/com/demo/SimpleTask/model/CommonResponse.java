package com.demo.SimpleTask.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonResponse<T> {
    T entity;
    HttpStatus status;

    public CommonResponse(T entity, HttpStatus status) {
        this.entity = entity;
        this.status = status;
    }
}
