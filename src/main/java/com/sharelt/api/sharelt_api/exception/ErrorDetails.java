package com.sharelt.api.sharelt_api.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private String message;
    private List<String> errors;
    private String details;
    private LocalDateTime timestamp;

}
