package com.edu.icesi.aviazen.dto;

import com.edu.icesi.aviazen.domain.UserComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentsDTO {

    private Long id;
    private Long user_id;
    private Long destination_id;
    private String text_comment;
    private Double rating;
    private Date comment_date;
    private String name_user;

    public UserCommentsDTO(UserComments userComments, String name_user) {
        this.id = userComments.getId();
        this.user_id = Long.valueOf(userComments.getUser().getId());
        this.destination_id = Long.valueOf(userComments.getDestination().getId());
        this.text_comment = userComments.getText_comment();
        this.rating = userComments.getRating();
        this.comment_date = userComments.getComment_date();
        this.name_user = name_user;
    }

}
