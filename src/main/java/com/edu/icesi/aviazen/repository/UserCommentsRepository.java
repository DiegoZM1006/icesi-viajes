package com.edu.icesi.aviazen.repository;

import com.edu.icesi.aviazen.domain.UserComments;
import com.edu.icesi.aviazen.dto.UserCommentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCommentsRepository extends JpaRepository<UserComments, Long> {

    @Query("SELECT new com.edu.icesi.aviazen.dto.UserCommentsDTO(u, u.user.name) FROM UserComments u WHERE u.destination.id = ?1")
    List<UserCommentsDTO> getCommentsByDestination(Long destination_id);

    @Query("SELECT AVG(u.rating) FROM UserComments u WHERE u.destination.id = ?1")
    Double countRatingByDestination(Long destination_id);

}
