package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.domain.Destination;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.domain.UserComments;
import com.edu.icesi.aviazen.dto.UserCommentsDTO;
import com.edu.icesi.aviazen.service.ClientService;
import com.edu.icesi.aviazen.service.DestinationService;
import com.edu.icesi.aviazen.service.UserCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users_comments")
@RequiredArgsConstructor
public class UserCommentsController {

    private final UserCommentsService userCommentsService;
    private final DestinationService destinationService;
    private final ClientService clientService;

    @PostMapping(value = "saveComment")
    public ResponseEntity<?> saveComment(@RequestHeader("Authorization") String token, @RequestBody UserCommentsDTO userComments) {
        try {
            Destination destination = destinationService.findById(userComments.getDestination_id()).get();
            User user = clientService.findById(Math.toIntExact(userComments.getUser_id())).get();

            System.out.println(userComments);

            UserComments userCommentToSave = UserComments.builder()
                    .destination(destination)
                    .user(user)
                    .text_comment(userComments.getText_comment())
                    .rating(userComments.getRating())
                    .comment_date(userComments.getComment_date())
                    .build();

            userCommentsService.save(userCommentToSave);
            return ResponseEntity.ok(userComments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("No se pudo guardar el comentario");
        }
    }

    @GetMapping(value = "getCommentsByDestination/{id}")
    public ResponseEntity<?> getCommentsByDestination(@RequestHeader("Authorization") String token, @PathVariable("id") Long destination_id) {
        try {
            return ResponseEntity.ok(userCommentsService.getCommentsByDestination(destination_id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("No se pudo obtener los comentarios");
        }
    }

    @GetMapping(value = "countRatingByDestination/{id}")
    public ResponseEntity<?> countRatingByDestination(@RequestHeader("Authorization") String token, @PathVariable("id") Long destination_id) {
        try {
            return ResponseEntity.ok(userCommentsService.countRatingByDestination(destination_id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("No se pudo obtener el promedio de los comentarios");
        }
    }

}
