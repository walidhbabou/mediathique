package com.mediatheque.mediatheque.Dto.User;

import com.mediatheque.mediatheque.model.Role;
import lombok.Builder;

@Builder
public record UserResponse(String username, String lastname, String email, Role role) {
}