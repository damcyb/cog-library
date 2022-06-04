package pl.cogtask.librarycognifide.utils.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.cogtask.librarycognifide.model.entity.BookRecord;

@Component
public class JsonParser implements Parser {

    private BookRecord[] bookRecordArray;
    private ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public BookRecord[] parse(String textToParse) {
        var jsonNode = mapper.readTree(textToParse);
        parseJsonOnBookRecordList(jsonNode);
        return bookRecordArray.clone();
    }

    private void parseJsonOnBookRecordList(final JsonNode jsonNode) throws JsonProcessingException {
        bookRecordArray = mapper.readValue(jsonNode.get("items").toString(), BookRecord[].class);
        for(int i = 0; i < bookRecordArray.length; i++) {
            var item = jsonNode.get("items").get(i);
            bookRecordArray[i] = parseBookWithIsbn(item , bookRecordArray[i]);
        }
    }

    private BookRecord parseBookWithIsbn(final JsonNode item, final BookRecord bookRecord) throws JsonProcessingException {
        String tmpIsbn = bookRecord.getIsbn();
        var bookRecordWithDetails = mapper.readValue(item.get("volumeInfo").toString(), BookRecord.class);
        if(bookRecordWithDetails.getIsbn() == null) {
            bookRecordWithDetails.setIsbn(tmpIsbn);
        }
        return bookRecordWithDetails;
    }
}
