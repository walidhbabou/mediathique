package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class UserDetailServiceimpl implements UserDetailsService {
    private AcountService accountService;
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepo.findByEmailOrUsername(identifier, identifier);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with identifier: " + identifier);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

}