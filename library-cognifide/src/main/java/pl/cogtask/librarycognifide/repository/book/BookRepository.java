package pl.cogtask.librarycognifide.repository.book;

import org.springframework.stereotype.Repository;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    void saveAll(final BookRecord[] bookRecords);
    Optional<BookRecord> findBookByIsbn(final String isbn);
    List<BookRecord> findBooksByCategory(final String category);
}
