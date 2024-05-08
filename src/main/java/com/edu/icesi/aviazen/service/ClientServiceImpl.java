package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findClients() {
        return userRepository.findClients();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.ofNullable(userRepository.findById(integer));
    }

    @Override
    public User save(User entity) throws Exception {
        return userRepository.save(entity);
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
        userRepository.deleteById(Long.valueOf(integer));
    }

    @Override
    public void validate(User entity) throws Exception {

    }

    @Override
    public Long count() {
        return userRepository.count();
    }

}
