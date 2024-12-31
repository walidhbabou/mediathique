package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.UserRepository;
import com.mediatheque.mediatheque.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public User addUser(String email, String lastename, String password, Role role, String username) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(Role.USER);

        User user = User.builder()
                .username(username)
                .lastname(lastename)
                .email(email)
                .password(password)
                .role(role)
                .build();

        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
