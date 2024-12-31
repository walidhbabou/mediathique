package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.Admin;
import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.UserRepository;
import com.mediatheque.mediatheque.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AcountServiceimpl implements AcountService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User addNewUser(String email, String lastname, String password, Role role, String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) throw new RuntimeException("This user already exists");

        User newUser = new User();
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        newUser.setLastname(lastname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setRole(role);

        switch (role) {
            case LECTEUR:
                Lecteur lecteur = new Lecteur();
                lecteur.setUser(newUser);
                newUser.setLecteur(lecteur);
                break;
            case EMPLOYEE:
                Employe employe = new Employe();
                employe.setUser(newUser);
                newUser.setEmploye(employe);
                break;
            case ADMIN:
                Admin admin = new Admin();
                admin.setUser(newUser);
                newUser.setAdmin(admin);
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }

        User savedUser = userRepo.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
