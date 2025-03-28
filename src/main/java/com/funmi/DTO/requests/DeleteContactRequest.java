package com.funmi.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteContactRequest {
    @NotBlank(message = "Phone number is important")
    private String phoneNumber;
}
