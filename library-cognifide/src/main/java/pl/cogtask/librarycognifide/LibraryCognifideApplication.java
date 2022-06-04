package pl.cogtask.librarycognifide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.cogtask.librarycognifide.service.BookService;
import pl.cogtask.librarycognifide.utils.LibraryUtils;
import pl.cogtask.librarycognifide.utils.SourceType;
import pl.cogtask.librarycognifide.utils.parser.JsonParser;

import static pl.cogtask.librarycognifide.constants.LibraryConstants.Paths.DEFAULT_SOURCE_LINK;

@SpringBootApplication
public class LibraryCognifideApplication implements CommandLineRunner {

    private LibraryUtils libraryUtils;
    private BookService bookService;

    @Autowired
    public LibraryCognifideApplication(LibraryUtils libraryUtils, BookService bookService) {
        this.libraryUtils = libraryUtils;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryCognifideApplication.class, args);
    }


    @Override
    public void run(String... args) {
        var sourceLink = getSourceLink(args);
        var parsed = libraryUtils.getDataFromSource(sourceLink, SourceType.URL,  new JsonParser());
        bookService.saveAll(parsed);
    }

    private String getSourceLink(final String... args) {
        return args.length > 0 ? args[0] : DEFAULT_SOURCE_LINK;
    }
}
