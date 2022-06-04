package pl.cogtask.librarycognifide.repository.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.cogtask.librarycognifide.factory.BookFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    final void findAllShouldReturnAllAuthorRatingWithoutDuplicates() {
        //given
        var bookOne = BookFactory.createBook(List.of("John Davies"), 5.0);
        var bookTwo = BookFactory.createBook(List.of("Gary Ferdinand"), 3.0);
        var bookThree = BookFactory.createBook(List.of("Gary Ferdinand"), 4.0);

        //when
        authorRepository.rateAuthors(List.of(bookOne, bookTwo, bookThree));
        var authorRatingList = authorRepository.findAll();

        //then
        assertThat(authorRatingList).hasSize(2);
        assertThat(authorRatingList.get(1).getAverageRating()).isEqualTo(3.5);
        assertThat(authorRatingList.get(0).getAverageRating()).isEqualTo(5.0);
    }
}