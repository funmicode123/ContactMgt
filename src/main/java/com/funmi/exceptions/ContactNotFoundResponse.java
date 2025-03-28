package com.funmi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactNotFoundResponse {
    private boolean success;
    private String message;
}
