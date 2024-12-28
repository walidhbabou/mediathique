package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AcountServiceimpl implements AcountService {
    private UserRepository userRepo;
    @Override
    public User addNewUser(String email, String lastename, String password, String status, String username) {
        User user = userRepo.findByUsername(username);
        if(user != null) throw new RuntimeException("This user already exist");
        User user1 = new User();
        user1.setEmail(email);
        user1.setLastname(lastename);
        user1.setPassword(new BCryptPasswordEncoder().encode(password));
        user1.setStatus(status);
        user1.setUsername(username);

        User savedUser = userRepo.save(user1);
        return savedUser;    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
