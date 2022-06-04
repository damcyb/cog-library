package pl.cogtask.librarycognifide.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.cogtask.librarycognifide.model.entity.BookRecord;
import pl.cogtask.librarycognifide.utils.parser.Parser;

@Component
public class LibraryUtils {

    public BookRecord[] getDataFromSource(
            final String source,
            final SourceType type,
            final Parser parser
    ) {
        if (type == SourceType.URL) {
            return parseFromUrl(source, parser);
        }
        throw new UnsupportedOperationException("No such source type " + type);
    }

    private BookRecord[] parseFromUrl(final String url, final Parser parser) {
        var data = getDataFromUrl(url);
        return parser.parse(data);
    }

    private String getDataFromUrl(final String url) {
        var restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
