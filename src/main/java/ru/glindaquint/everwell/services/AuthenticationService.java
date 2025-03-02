package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.dto.auth.JwtAuthenticationResponse;
import ru.glindaquint.everwell.dto.auth.RestoreRequest;
import ru.glindaquint.everwell.dto.auth.SignInRequest;
import ru.glindaquint.everwell.dto.auth.SignUpRequest;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.types.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public String signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        return jwtService.generateToken(user);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public String signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        return jwtService.generateToken(user);
    }

    public boolean restorePassword(RestoreRequest request) {
        return userService.restorePassword(request.getEmail(), passwordEncoder.encode(request.getPassword()));
    }
}