package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    public List<User> findClients() {
        return clientRepository.findClients();
    }

    @Override
    public List<User> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.ofNullable(clientRepository.findById(integer));
    }

    @Override
    public Optional<User> findByCardNumber(Integer cardNumber) {
        return clientRepository.findByCardNumber(cardNumber);
    }

    @Override
    public User save(User entity) throws Exception {
        return clientRepository.save(entity);
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
        clientRepository.deleteById(Long.valueOf(integer));
    }

    @Override
    public void validate(User entity) throws Exception {

    }

    @Override
    public Long count() {
        return clientRepository.count();
    }

}
