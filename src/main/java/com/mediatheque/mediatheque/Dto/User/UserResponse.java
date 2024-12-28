package com.mediatheque.mediatheque.Dto.User;

import lombok.Builder;

@Builder
public record UserResponse(String username, String lastname, String email, String status) {
}