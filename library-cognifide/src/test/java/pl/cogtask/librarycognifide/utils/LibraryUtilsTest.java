package pl.cogtask.librarycognifide.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.cogtask.librarycognifide.model.entity.BookRecord;
import pl.cogtask.librarycognifide.utils.parser.JsonParser;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.cogtask.librarycognifide.constants.LibraryConstants.Paths.DEFAULT_SOURCE_LINK;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LibraryUtilsTest {

    private BookRecord[] bookRecords;

    @Autowired
    LibraryUtils libraryUtils;

    @BeforeAll
    void setup() {
        bookRecords = libraryUtils.getDataFromSource(DEFAULT_SOURCE_LINK, SourceType.URL, new JsonParser());
    }

    @Test
    void parsingShouldReturnFortyBooks() {
        var books = bookRecords.clone();
        assertThat(books).hasSize(40);
    }

    @Test
    void booksIsbnAfterParsingShouldNotBeNull() {
        var books = bookRecords.clone();
        Arrays.stream(books).forEach(book -> assertThat(book.getIsbn()).isNotNull());
    }

    @Test
    void shouldParseIsbnIfIsbnFieldIsPresentInInputJson() {
        var books = bookRecords.clone();
        var foundBook = Arrays.stream(books)
                .filter(book -> book.getIsbn().equals("9781932504057"))
                .collect(Collectors.toUnmodifiableList());
        assertThat(foundBook.size()).isEqualTo(1);
    }

    @Test
    void shouldParseIdAsIsbnIfIsbnFieldIsNotPresentInInputJson() {
        var books = bookRecords.clone();
        var foundBook = Arrays.stream(books)
                .filter(book -> book.getIsbn().equals("6KdQAAAAMAAJ"))
                .collect(Collectors.toUnmodifiableList());
        assertThat(foundBook.size()).isEqualTo(1);
    }

    @Test
    void shouldMapPublishedDateIntoUnixTimestamp() {
        var books = bookRecords.clone();
        var foundBook = Arrays.stream(books)
                .filter(book -> book.getIsbn().equals("9788324677658"))
                .collect(Collectors.toUnmodifiableList());
        assertThat(foundBook.size()).isEqualTo(1);
        assertThat(foundBook.get(0).getPublishedDate()).isEqualTo(1386543600000L);
    }


}