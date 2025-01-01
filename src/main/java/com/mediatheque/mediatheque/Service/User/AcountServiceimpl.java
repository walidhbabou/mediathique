package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.Admin;
import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.UserRepository;
import com.mediatheque.mediatheque.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AcountServiceimpl implements AcountService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User addNewUser(String email, String lastname, String password, Role role, String username) {
        System.out.println("Attempting to add new user with username: " + username);

        User user = userRepo.findByUsername(username);
        if (user != null) {
            System.out.println("User already exists with username: " + username);
            throw new RuntimeException("This user already exists");
        }

        User newUser = new User();
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        newUser.setLastname(lastname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setRole(role);

        System.out.println("New user created: " + newUser);

        switch (role) {
            case ADMIN:
                Admin admin = new Admin();
                admin.setUser(newUser);
                newUser.setAdmin(admin);
                System.out.println("Admin entity created and associated with user");
                break;
            case LECTEUR:
                Lecteur lecteur = new Lecteur();
                lecteur.setUser(newUser);
                newUser.setLecteur(lecteur);
                System.out.println("Lecteur entity created and associated with user");
                break;
            case EMPLOYEE:
                Employe employe = new Employe();
                employe.setUser(newUser);
                newUser.setEmploye(employe);
                System.out.println("Employe entity created and associated with user");
                break;
            default:
                System.out.println("Invalid role: " + role);
                throw new IllegalArgumentException("Invalid role");
    }
        User savedUser = userRepo.save(newUser);
        System.out.println("User saved to database: " + savedUser);

        return savedUser;
}
    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }}
