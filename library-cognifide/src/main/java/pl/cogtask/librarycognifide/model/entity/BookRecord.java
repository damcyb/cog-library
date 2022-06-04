package pl.cogtask.librarycognifide.model.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;
import pl.cogtask.librarycognifide.utils.PublishedDateDeserializer;

import java.util.List;
import java.util.Map;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRecord {

    @JsonAlias({"id"})
    private String isbn;

    private String title;
    private String subtitle;
    private String publisher;

    @JsonDeserialize(using = PublishedDateDeserializer.class)
    private Long publishedDate;

    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private double averageRating;
    private List<String> authors;
    private List<String> categories;

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonAlias({"id"})
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("publishedDate")
    public Long getPublishedDate() {
        return publishedDate;
    }

    @JsonProperty("publishedDate")
    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("pageCount")
    public int getPageCount() {
        return pageCount;
    }

    @JsonProperty("pageCount")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("previewLink")
    public String getPreviewLink() {
        return previewLink;
    }

    @JsonProperty("previewLink")
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    @JsonProperty("averageRating")
    public Double getAverageRating() {
        return averageRating;
    }

    @JsonProperty("averageRating")
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @JsonProperty("authors")
    public List<String> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @JsonProperty("imageLinks")
    private void unpackNestedThumbnail(Map<String,Object> imageLinks) {
        this.thumbnailUrl = (String)imageLinks.get("thumbnail");
    }

    @JsonProperty("industryIdentifiers")
    private void filterIsbnData(Object[] industryIdentifiers) {
        for (int i = 0; i < industryIdentifiers.length; i++) {
            if(industryIdentifiers[i].toString().contains("ISBN_13")) {
                this.isbn = industryIdentifiers[i].toString()
                        .split(" ")[1]
                        .replace("identifier=", "")
                        .replace("}","");
            }
        }
    }
}
