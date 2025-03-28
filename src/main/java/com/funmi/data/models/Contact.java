package com.funmi.data.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotNull
    private String lastName;
    @NotBlank(message = "Phone number is required")
    @Indexed(unique = true)
    private String phoneNumber;
    @Email(message = "Email is required")
    private String email;
    @NotNull
    private String location;
}
