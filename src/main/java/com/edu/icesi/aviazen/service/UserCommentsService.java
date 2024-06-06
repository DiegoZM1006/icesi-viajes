package com.edu.icesi.aviazen.service;

import com.edu.icesi.aviazen.domain.UserComments;
import com.edu.icesi.aviazen.dto.UserCommentsDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCommentsService extends GenericService<UserComments, Long>{

    @Query("SELECT u FROM UserComments u WHERE u.destination.id = ?1")
    List<UserCommentsDTO> getCommentsByDestination(Long destination_id);

    @Query("SELECT AVG(u.rating) FROM UserComments u WHERE u.destination.id = ?1")
    Double countRatingByDestination(Long destination_id);

}
