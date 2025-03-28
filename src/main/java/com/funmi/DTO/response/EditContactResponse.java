package com.funmi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditContactResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String location;
    private boolean success;
    private String message;
}
