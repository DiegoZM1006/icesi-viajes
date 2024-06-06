package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.UserComments;
import com.edu.icesi.aviazen.dto.UserCommentsDTO;
import com.edu.icesi.aviazen.repository.UserCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
public class UserCommentsServiceImpl implements UserCommentsService {

    @Autowired
    private UserCommentsRepository userCommentsRepository;

    @Override
    public List<UserComments> findAll() {
        return null;
    }

    @Override
    public Optional<UserComments> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public UserComments save(UserComments entity) throws Exception {
        return userCommentsRepository.save(entity);
    }

    @Override
    public UserComments update(UserComments entity) throws Exception {
        return null;
    }

    @Override
    public void delete(UserComments entity) throws Exception {

    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public void validate(UserComments entity) throws Exception {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<UserCommentsDTO> getCommentsByDestination(Long destination_id) {
        return userCommentsRepository.getCommentsByDestination(destination_id);
    }

    @Override
    public Double countRatingByDestination(Long destination_id) {
        return userCommentsRepository.countRatingByDestination(destination_id);
    }

}
