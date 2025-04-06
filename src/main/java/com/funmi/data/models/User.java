package com.funmi.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String password;
}
