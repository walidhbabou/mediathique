package com.mediatheque.mediatheque.Controller;
import com.mediatheque.mediatheque.Dto.User.UserLoginRequest;
import com.mediatheque.mediatheque.Dto.User.UserRegisterRequest;
import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Service.User.UserServiceimpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private UserServiceimpl userService;
    private PasswordEncoder passwordEncoder;
//    private CartService cartService;
    @PostMapping("/login")
    public Map<String, String> login(UserLoginRequest userLoginRequest){
        System.out.println("login");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password()));
        System.out.println(authentication);
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(userLoginRequest.email())
                .claim("scope", scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("accessToken", jwt);
    }
    @PostMapping("/signup")

    public Map<String, String> signup(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest.email() == null || userRegisterRequest.password().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Changement de l'ordre des paramètres dans la méthode addUser
        User user = userService.addUser(
                userRegisterRequest.email(),
                userRegisterRequest.lastename(),
                passwordEncoder.encode(userRegisterRequest.password()),
                userRegisterRequest.username(),
                userRegisterRequest.status()
        );

        User user1 = userService.getUserByUsername(userRegisterRequest.username());

        if(user != null) {
            Instant instant = Instant.now();

            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuedAt(instant.plus(10, ChronoUnit.MINUTES))
                    .subject(userRegisterRequest.username())
                    .claim("scope", "user")
                    .build();

            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS512).build(),
                    jwtClaimsSet
            );
            String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
            return Map.of("accessToken", jwt);
        }
        return Map.of("accessToken", null);
    }


}
