package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<User> findEmployees() {
        return employeeRepository.findEmployees();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(employeeRepository.findById(id));
    }

    @Override
    public Long countEmployees() {
        return employeeRepository.countEmployees();
    }

    @Override
    public User save(User entity) throws Exception {
        return employeeRepository.save(entity);
    }

    @Override
    public User update(User entity) throws Exception {
        return null;
    }

    @Override
    public void delete(User entity) throws Exception {

    }

    @Override
    public void deleteById(Integer integer) throws Exception {
        employeeRepository.deleteById(Long.valueOf(integer));
    }

    @Override
    public void validate(User entity) throws Exception {

    }

    @Override
    public Long count() {
        return employeeRepository.count();
    }
}
