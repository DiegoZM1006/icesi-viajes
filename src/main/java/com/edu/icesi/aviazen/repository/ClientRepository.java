package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'CLIENT'")
    List<User> findClients();

    User findById(Integer id);

}
