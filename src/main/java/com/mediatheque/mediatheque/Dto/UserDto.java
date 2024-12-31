package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String status;

}
