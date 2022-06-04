package pl.cogtask.librarycognifide.service;

import org.springframework.stereotype.Service;
import pl.cogtask.librarycognifide.model.dto.AuthorRatingDto;
import pl.cogtask.librarycognifide.model.dto.BookDto;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    void saveAll(final BookRecord[] bookRecords);
    Optional<BookDto> findBookByIsbn(final String isbn);
    List<BookDto> findBooksByCategory(final String category);
    List<AuthorRatingDto> getAuthorRatingList();
}
