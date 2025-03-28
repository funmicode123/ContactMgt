package com.funmi.services;

import com.funmi.DTO.response.DeleteContactResponse;

public interface DeleteContactService {
    DeleteContactResponse deleteContact(String phoneNumber);
}
