package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;

import java.util.UUID;

public interface UserService {
    public User addUser(String email, String lastename, String password, String status, String username);
    public User getUserById(Long id);
    public User getUserByUsername(String username);
}
