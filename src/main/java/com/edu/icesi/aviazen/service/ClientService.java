package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientService extends GenericService<User, Integer>{

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'CLIENT'")
    List<User> findClients();

    Optional<User> findById(Integer id);

    Optional<User> findByCardNumber(Integer cardNumber);

}
