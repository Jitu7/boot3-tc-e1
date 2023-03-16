package com.example.boot3testinge1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String message;
    private LocalDateTime timstamp;
}
