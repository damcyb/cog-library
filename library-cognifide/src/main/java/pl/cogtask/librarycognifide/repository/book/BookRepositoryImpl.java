package pl.cogtask.librarycognifide.repository.book;

import org.springframework.stereotype.Repository;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private List<BookRecord> bookRecordList = new ArrayList<>();

    @Override
    public void saveAll(BookRecord[] bookRecords) {
        setBookRecordList(Arrays.asList(bookRecords));
        System.out.println("asd");
    }

    @Override
    public Optional<BookRecord> findBookByIsbn(final String isbn) {
        return bookRecordList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public List<BookRecord> findBooksByCategory(String category) {
        return bookRecordList.stream()
                .filter(book -> book.getCategories() != null)
                .filter(book -> book
                        .getCategories()
                        .contains(category))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<BookRecord> getBookRecordList() {
        return bookRecordList;
    }

    private void setBookRecordList(List<BookRecord> bookRecordList) {
        this.bookRecordList = bookRecordList;
    }
}
