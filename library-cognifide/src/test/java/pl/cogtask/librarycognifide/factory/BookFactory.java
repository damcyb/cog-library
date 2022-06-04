package pl.cogtask.librarycognifide.factory;

import com.github.javafaker.Faker;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookFactory {

    public static BookRecord createBook(
            List<String> authorNameList,
            Double averageRating
    ) {
        var faker = new Faker();
        var book = new BookRecord();

        var authors = authorNameList != null ? authorNameList : List.of(faker.name().fullName());
        var rating = averageRating != null ? averageRating : faker.random().nextInt(1, 5);

        book.setIsbn(UUID.randomUUID().toString());
        book.setAuthors(authors);
        book.setAverageRating(rating);
        book.setCategories(List.of("Computers"));

        return book;
    }
}
