package pl.cogtask.librarycognifide.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import pl.cogtask.librarycognifide.exceptions.PublishedDateDeserializationException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublishedDateDeserializer extends JsonDeserializer<Long>
{
    @Override
    public Long deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {

        String stringToFormat = jsonParser.getText();
        String pattern = checkDateFormat(stringToFormat);
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        try {
            format.parse(stringToFormat);
            Date date = format.parse(stringToFormat);
            Timestamp timeStamp = new Timestamp(date.getTime());
            return timeStamp.getTime();
        } catch (ParseException e) {
            throw new PublishedDateDeserializationException(e.getMessage());
        }
    }

    private String checkDateFormat(String dateString) {
        String pattern = "";
        String[] dateFormatIdentifier = dateString.split("-");

        if(dateFormatIdentifier.length == 1) pattern = "yyyy";
        if(dateFormatIdentifier.length == 2) pattern = "yyyy-MM";
        if(dateFormatIdentifier.length == 3) pattern = "yyyy-MM-dd";

        return pattern;

    }

}
