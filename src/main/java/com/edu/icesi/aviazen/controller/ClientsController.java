package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthResponse;
import com.edu.icesi.aviazen.auth.AuthService;
import com.edu.icesi.aviazen.auth.RegisterRequest;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.jwt.JwtService;
import com.edu.icesi.aviazen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "allClients")
    public ResponseEntity<List<User>> allClients(@RequestHeader("Authorization") String token)
    {
        List<User> clients = userRepository.findClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "addClient")
    public ResponseEntity<AuthResponse> register(@RequestHeader("Authorization") String token, @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "searchClient/{id}")
    public ResponseEntity<User> searchClient(@RequestHeader("Authorization") String token, @PathVariable Integer id)
    {
        User client = userRepository.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping(value = "updateClient/{id}")
    public ResponseEntity<User> updateClient(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody RegisterRequest user)
    {
        User client = userRepository.findById(id);
        client.setName(user.getName());
        client.setLastname(user.getLastname());
        client.setEmail(user.getEmail());
        client.setPhone_number(user.getPhone_number());
        client.setAddress(user.getAddress());
        client.setUsername(user.getUsername());
        client.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
