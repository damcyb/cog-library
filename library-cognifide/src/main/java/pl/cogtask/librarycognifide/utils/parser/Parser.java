package pl.cogtask.librarycognifide.utils.parser;

import pl.cogtask.librarycognifide.model.entity.BookRecord;

public interface Parser {
    BookRecord[] parse(String textToParse);
}
