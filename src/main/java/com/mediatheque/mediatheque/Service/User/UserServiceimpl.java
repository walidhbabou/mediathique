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
    public User addUser(String username, String lastname, String email, String password, String status) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(Role.USER); // Assurez-vous que `Role.USER` est défini dans votre projet.

        User user = User.builder()
                .username(username)
                .lastname(lastname)
                .email(email)
                .password(password)
                .status(status)
                .roles(roles) // Vérifiez que `typeUsers` est un champ de `User`.
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
