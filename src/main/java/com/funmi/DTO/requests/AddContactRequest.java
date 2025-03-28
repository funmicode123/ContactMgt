package com.funmi.DTO.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddContactRequest {
    @NotBlank
    private String firstName;
    @NotNull
    private String lastName;
    @NotBlank(message="Phone Number is required")
    @Pattern(regexp = "\\+234[-\\s]?([789]\\d{3})[-\\s]?(\\d{3})[-\\s]?(\\d{3})", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank
    @Email(message = "Email is required")
    private String email;
    @NotNull
    private String location;
}
