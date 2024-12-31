package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.model.Role;

import java.util.UUID;

public interface UserService {
    public User addUser(String email, String lastename, String password, Role role, String username);
    public User getUserById(Long id);
    public User getUserByUsername(String username);
}
