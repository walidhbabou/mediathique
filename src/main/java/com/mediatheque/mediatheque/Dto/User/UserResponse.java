package com.mediatheque.mediatheque.Dto.User;

import com.mediatheque.mediatheque.model.Role;
import lombok.Builder;

@Builder
public record UserResponse(Long id,String username, String lastname, String email, Role role) {
}