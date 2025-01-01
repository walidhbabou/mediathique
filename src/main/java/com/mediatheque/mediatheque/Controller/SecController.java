package com.mediatheque.mediatheque.Controller;
import com.mediatheque.mediatheque.Dto.User.UserLoginRequest;
import com.mediatheque.mediatheque.Dto.User.UserRegisterRequest;
import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Service.User.AcountServiceimpl;
import com.mediatheque.mediatheque.Service.User.UserServiceimpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@AllArgsConstructor
public class SecController {
    private AuthenticationManager authenticationManager;
    private JwtEncoder jwtEncoder;
    private AcountServiceimpl userService;
    private PasswordEncoder passwordEncoder;
//    private CartService cartService;
@PostMapping("/login")
public Map<String, String> login(@RequestBody UserLoginRequest userLoginRequest) {
    // Authentifie l'utilisateur
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password())
    );

    // Récupère le rôle de l'utilisateur
    String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("USER");

    // Génère un token JWT
    Instant instant = Instant.now();
    JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
            .issuedAt(instant)
            .expiresAt(instant.plus(10, ChronoUnit.MINUTES)) // Token valide pendant 10 minutes
            .subject(userLoginRequest.email())
            .claim("scope", role)
            .build();
    System.out.println(role);

    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
            JwsHeader.with(MacAlgorithm.HS256).build(),
            jwtClaimsSet
    );
    System.out.println("Email: " + userLoginRequest.email());
    System.out.println("Password: " + userLoginRequest.password());
    System.out.println("Role: " + role);
    String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    return Map.of("accessToken", jwt, "role", role);
}
    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            System.out.println("Signup request received: " + userRegisterRequest);

            if (userRegisterRequest.email() == null || userRegisterRequest.password().isEmpty()) {
                System.out.println("Email or password is null or empty");
                throw new IllegalArgumentException("Password cannot be null or empty");
            }

            System.out.println("Attempting to add new user...");
            User user = userService.addNewUser(
                    userRegisterRequest.email(),
                    userRegisterRequest.lastname(),
                    userRegisterRequest.password(),
                    userRegisterRequest.role(),
                    userRegisterRequest.username()
            );

            if (user != null) {
                System.out.println("User successfully added: " + user);

                Instant instant = Instant.now();

                JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                        .issuedAt(instant.plus(10, ChronoUnit.MINUTES))
                        .subject(userRegisterRequest.username())
                        .claim("scope", user.getRole().name())
                        .build();

                JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS256).build(),
                        jwtClaimsSet
                );
                String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
                return Map.of("accessToken", jwt);
            } else {
                System.out.println("Failed to add user");
            }
        } catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            e.printStackTrace();
        }
        return Map.of("accessToken", null);
    }
}
