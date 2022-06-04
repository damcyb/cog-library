package pl.cogtask.librarycognifide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cogtask.librarycognifide.model.dto.AuthorRatingDto;
import pl.cogtask.librarycognifide.model.dto.BookDto;
import pl.cogtask.librarycognifide.model.entity.BookRecord;
import pl.cogtask.librarycognifide.repository.author.AuthorRepository;
import pl.cogtask.librarycognifide.repository.book.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAll(final BookRecord[] bookRecords) {
        bookRepository.saveAll(bookRecords);
        authorRepository.rateAuthors(List.of(bookRecords));
    }

    @Override
    public Optional<BookDto> findBookByIsbn(final String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .map(BookDto::fromBookEntity);
    }

    @Override
    public List<BookDto> findBooksByCategory(final String category) {
        return bookRepository.findBooksByCategory(category)
                .stream()
                .map(BookDto::fromBookEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<AuthorRatingDto> getAuthorRatingList() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorRatingDto::fromAuthorRatingEntity)
                .collect(Collectors.toUnmodifiableList());
    }
}
