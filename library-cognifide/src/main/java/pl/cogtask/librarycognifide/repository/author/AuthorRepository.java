package pl.cogtask.librarycognifide.repository.author;

import org.springframework.stereotype.Repository;
import pl.cogtask.librarycognifide.model.entity.AuthorRating;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.util.List;

@Repository
public interface AuthorRepository {
    void rateAuthors(final List<BookRecord> bookRecordList);
    List<AuthorRating> findAll();
}
