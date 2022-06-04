package pl.cogtask.librarycognifide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cogtask.librarycognifide.model.response.AuthorRatingResponseModel;
import pl.cogtask.librarycognifide.model.response.BookResponseModel;
import pl.cogtask.librarycognifide.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

import static pl.cogtask.librarycognifide.constants.LibraryConstants.Paths.*;

@RestController
@RequestMapping(BOOK_CONTROLLER_PATH)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = GET_BOOK_DETAILS_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BookResponseModel> getBookDetails(@PathVariable String isbn) {
        var book = bookService.findBookByIsbn(isbn);
        if (book.isPresent()) {
            var result = BookResponseModel.fromBookDto(book.get());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = GET_BOOKS_BY_CATEGORY_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BookResponseModel>> getBooksByCategory(@PathVariable String category) {
        var bookList = bookService.findBooksByCategory(category);
        var result = bookList.stream()
                .map(BookResponseModel::fromBookDto)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = GET_AUTHOR_RATING_LIST_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AuthorRatingResponseModel>> getAuthorRatingList() {
        var authorList = bookService.getAuthorRatingList();
        var result = authorList.stream()
                .map(AuthorRatingResponseModel::fromAuthorRatingDto)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(result);
    }
}
