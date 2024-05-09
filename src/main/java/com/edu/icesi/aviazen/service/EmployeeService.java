package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.User;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService extends GenericService<User, Integer>{

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'ADMIN' or u.role = 'AGENT'")
    List<User> findEmployees();

    Optional<User> findById(Integer id);

}
