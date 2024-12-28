package com.mediatheque.mediatheque.Dto.User;

public record UserRegisterRequest(String email, String lastename, String password, String status, String username) {
}
