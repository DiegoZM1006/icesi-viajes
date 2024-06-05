package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthResponse;
import com.edu.icesi.aviazen.auth.AuthService;
import com.edu.icesi.aviazen.auth.RegisterRequest;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final ClientService clientService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "allClients")
    public ResponseEntity<List<User>> allClients(@RequestHeader("Authorization") String token)
    {
        List<User> clients = clientService.findClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "addClient")
    public ResponseEntity<AuthResponse> register(@RequestHeader("Authorization") String token, @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "searchClient/{id}")
    public ResponseEntity<?> searchClient(@RequestHeader("Authorization") String token, @PathVariable Integer id)
    {
        Optional<User> client = clientService.findById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "searchClientByCardNumber/{id}")
    public ResponseEntity<?> searchClientByCardNumber(@RequestHeader("Authorization") String token, @PathVariable Integer id)
    {
        Optional<User> client = clientService.findByCardNumber(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "updateClient/{id}")
    public ResponseEntity<?> updateClient(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody RegisterRequest user) throws Exception {
        Optional<User> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            User client = optionalClient.get();
            client.setName(user.getName());
            client.setLastname(user.getLastname());
            client.setEmail(user.getEmail());
            client.setPhone_number(user.getPhone_number());
            client.setAddress(user.getAddress());
            client.setUsername(user.getUsername());
            client.setPassword(passwordEncoder.encode(user.getPassword()));
            clientService.save(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "deleteClient/{id}")
    public ResponseEntity<?> deleteClient(@RequestHeader("Authorization") String token, @PathVariable Integer id) throws Exception {
        Optional<User> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            clientService.deleteById(id);
            return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "countClients")
    public ResponseEntity<Long> countClients(@RequestHeader("Authorization") String token) {
        Long clients = clientService.countClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

}
