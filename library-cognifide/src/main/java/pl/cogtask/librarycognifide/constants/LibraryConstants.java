package pl.cogtask.librarycognifide.constants;

public interface LibraryConstants {

    interface Paths {
        String DEFAULT_SOURCE_LINK = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
        String BOOK_CONTROLLER_PATH = "/";
        String GET_BOOK_DETAILS_PATH = "/book/{isbn}";
        String GET_BOOKS_BY_CATEGORY_PATH = "/category/{category}";
        String GET_AUTHOR_RATING_LIST_PATH = "/rating";
    }
}
