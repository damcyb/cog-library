package pl.cogtask.librarycognifide.model.dto;

import lombok.Value;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.io.Serializable;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Value
public class BookDto implements Serializable {

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

    public static BookDto fromBookEntity(final BookRecord bookRecord) {

        requireNonNull(bookRecord.getIsbn());

        return new BookDto(
                bookRecord.getIsbn(),
                bookRecord.getTitle(),
                bookRecord.getSubtitle(),
                bookRecord.getPublisher(),
                bookRecord.getPublishedDate(),
                bookRecord.getDescription(),
                bookRecord.getPageCount(),
                bookRecord.getThumbnailUrl(),
                bookRecord.getLanguage(),
                bookRecord.getPreviewLink(),
                bookRecord.getAverageRating(),
                bookRecord.getAuthors(),
                bookRecord.getCategories()
        );
    }
}
