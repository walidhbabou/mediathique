package com.mediatheque.mediatheque.Dto;

import lombok.Data;

@Data
public class AccountRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String message;
}
