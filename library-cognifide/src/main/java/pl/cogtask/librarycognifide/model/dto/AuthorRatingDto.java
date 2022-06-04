package pl.cogtask.librarycognifide.model.dto;

import lombok.Value;
import pl.cogtask.librarycognifide.model.entity.AuthorRating;

import java.io.Serializable;

@Value
public class AuthorRatingDto implements Serializable {

    String author;
    Double averageRating;

    public static AuthorRatingDto fromAuthorRatingEntity(AuthorRating authorRating) {
        return new AuthorRatingDto(
                authorRating.getAuthor(),
                authorRating.getAverageRating()
        );
    }
}
