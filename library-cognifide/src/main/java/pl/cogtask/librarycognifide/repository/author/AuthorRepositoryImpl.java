package pl.cogtask.librarycognifide.repository.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.cogtask.librarycognifide.model.entity.AuthorRating;
import pl.cogtask.librarycognifide.model.entity.BookRecord;
import java.util.*;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{

    private List<AuthorRating> authorRatingList;

    @Autowired
    public AuthorRepositoryImpl(List<AuthorRating> authorRating) {
        this.authorRatingList = authorRating;
    }

    @Override
    public List<AuthorRating> findAll() {
        return this.authorRatingList;
    }

    @Override
    public void rateAuthors(List<BookRecord> bookRecordList) {
        Set<String> authorSet = createAuthorSet(bookRecordList);
        Map<String, List<Double>> authorRatingMap = createAuthorRatingMap(authorSet, bookRecordList);
        List<AuthorRating> authorRatingList = new ArrayList<>();
        for (Map.Entry<String, List<Double>> author : authorRatingMap.entrySet()) {
            List<Double> ratingList = authorRatingMap.get(author.getKey());
            Double average = calculateAverageRating(ratingList);
            authorRatingList.add(new AuthorRating(author.getKey(), average));
        }
        this.authorRatingList = authorRatingList;
    }

    private Set<String> createAuthorSet(final List<BookRecord> bookRecordList) {
        Set<String> authorSet = new HashSet<>();
        bookRecordList.stream()
                .filter(book -> book.getAuthors() != null)
                .forEach(book -> authorSet.addAll(book.getAuthors()));
        return authorSet;
    }

    private Map<String, List<Double>> createAuthorRatingMap(final Set<String> authorSet, final List<BookRecord> bookRecordList) {
        Map<String, List<Double>> authorRatingMap = putAuthorsKey(authorSet);
        for (BookRecord book: bookRecordList) {
            if(book.getAverageRating() != null && book.getAuthors() != null) {
                for (String bookAuthor: book.getAuthors()) {
                    authorRatingMap.get(bookAuthor).add(book.getAverageRating());
                }
            }
        }
        return authorRatingMap;
    }

    private Map<String, List<Double>> putAuthorsKey(final Set<String> authorSet) {
        Map<String, List<Double>> authorRatingMap = new HashMap<>();
        authorSet.forEach(author -> authorRatingMap.put(author, new ArrayList<>()));
        return authorRatingMap;
    }

    private Double calculateAverageRating(final List<Double> ratingList) {
        return ratingList.stream()
                .mapToDouble(rating -> rating)
                .average()
                .orElse(0.0);
    }
}
