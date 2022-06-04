package pl.cogtask.librarycognifide.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import pl.cogtask.librarycognifide.model.dto.AuthorRatingDto;

import java.io.Serializable;

@Value
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AuthorRatingResponseModel implements Serializable {
    String author;
    Double averageRating;

    public static AuthorRatingResponseModel fromAuthorRatingDto(final AuthorRatingDto dto) {
        return new AuthorRatingResponseModel(
                dto.getAuthor(),
                dto.getAverageRating()
        );
    }
}
