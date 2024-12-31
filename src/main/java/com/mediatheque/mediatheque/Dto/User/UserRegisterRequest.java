package com.mediatheque.mediatheque.Dto.User;

import com.mediatheque.mediatheque.model.Role;

public record UserRegisterRequest(String email, String lastname, String password, Role role, String status, String username) {
}
