package pl.cogtask.librarycognifide.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Value;
import pl.cogtask.librarycognifide.model.dto.BookDto;

import java.io.Serializable;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Value
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookResponseModel implements Serializable {

    String isbn;
    String title;
    String subtitle;
    String publisher;
    Long publishedDate;
    String description;
    int pageCount;
    String thumbnailUrl;
    String language;
    String previewLink;
    Double averageRating;
    List<String> authors;
    List<String> categories;

    public static BookResponseModel fromBookDto(final BookDto dto) {
        requireNonNull(dto.getIsbn());

        return new BookResponseModel(
                dto.getIsbn(),
                dto.getTitle(),
                dto.getSubtitle(),
                dto.getPublisher(),
                dto.getPublishedDate(),
                dto.getDescription(),
                dto.getPageCount(),
                dto.getThumbnailUrl(),
                dto.getLanguage(),
                dto.getPreviewLink(),
                dto.getAverageRating(),
                dto.getAuthors(),
                dto.getCategories()
        );
    }

}