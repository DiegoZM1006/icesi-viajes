package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'ADMIN' or u.role = 'AGENT'")
    List<User> findEmployees();

    User findById(Integer id);

}
