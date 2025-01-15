    package com.mediatheque.mediatheque.config;

    import com.mediatheque.mediatheque.Service.User.UserDetailServiceimpl;
    import com.nimbusds.jose.jwk.source.ImmutableSecret;
    import org.apache.catalina.filters.CorsFilter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.ProviderManager;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
    import org.springframework.security.oauth2.jwt.JwtDecoder;
    import org.springframework.security.oauth2.jwt.JwtEncoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import javax.crypto.spec.SecretKeySpec;
    import java.util.Arrays;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {
        private final String secretKey;

        private final UserDetailServiceimpl userDetailServicesImpl;

        @Autowired
        public SecurityConfig(@Value("${jwt.secret}") String secretKey, UserDetailServiceimpl userDetailServicesImpl) {
            this.secretKey = secretKey;
            this.userDetailServicesImpl = userDetailServicesImpl;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(ar -> ar
                            .requestMatchers("/login", "/signup", "/public/**").permitAll()
                            .requestMatchers("/Mediatheque/Document/**").permitAll()
                            .requestMatchers("/Mediatheque/abo/**").permitAll()
                            .requestMatchers("/Mediatheque/Journal/**").permitAll()
                            .requestMatchers("/api/microfilms/**").permitAll()
                            .requestMatchers("/Journal/**").permitAll()
                            .requestMatchers("/livre/**").permitAll()
                            .requestMatchers("/Mediatheque/CdRom/**").permitAll()
                            .requestMatchers("/api/emprunts/**").permitAll()
                            .requestMatchers("/Mediatheque/lecteur/**").permitAll()
                            .requestMatchers("/Mediatheque/user/**").permitAll()
                            .requestMatchers("/Mediatheque/equipe-benevol/**").permitAll()
                            .requestMatchers("/consult/**").permitAll()
                            .requestMatchers("/api/requests/**").permitAll()
                            .requestMatchers("/api/reclamations/**").permitAll()
                            .requestMatchers("/api/employes/**").permitAll()
                            .requestMatchers("/api/accountrequests/**").permitAll()
                            .requestMatchers("/dashboard-admin/**").hasRole("ADMIN")
                            .requestMatchers("/dashboard-lecteur/**").hasRole("LECTEUR")
                            .requestMatchers("/dashboard-employe/**").hasRole("EMPLOYEE")
                            .requestMatchers("/auth/**").authenticated()
                            .anyRequest().denyAll()
                    )
                    .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
                    .build();
        }
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://localhost:5500"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            configuration.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
        @Bean
        public JwtEncoder jwtEncoder() {
            return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
        }

        @Bean
        public JwtDecoder jwtDecoder() {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
            return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userDetailServicesImpl);
            return new ProviderManager(daoAuthenticationProvider);
        }

    }
