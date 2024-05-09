package com.edu.icesi.aviazen.auth;

import com.edu.icesi.aviazen.domain.Role;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.jwt.JwtService;
import com.edu.icesi.aviazen.repository.ClientRepository;
import com.edu.icesi.aviazen.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User userDetails = clientRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .username(request.getUsername())
                .name(userDetails.getName())
                .role(userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone_number(request.getPhone_number())
                .card_number(request.getCard_number())
                .role(Role.CLIENT)
                .build();

        clientRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse registerEmployee(RegisterRequest request) {
        Role roleUser = Role.AGENT;

        if (request.getRole() != null) {
            if (request.getRole().equals("ADMIN")) {
                roleUser = Role.ADMIN;
            }
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone_number(request.getPhone_number())
                .card_number(request.getCard_number())
                .role(roleUser)
                .build();

        employeeRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
