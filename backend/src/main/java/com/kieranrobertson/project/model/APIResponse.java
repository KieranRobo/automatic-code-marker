package com.kieranrobertson.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {

    private String code;
    private String message;
}
