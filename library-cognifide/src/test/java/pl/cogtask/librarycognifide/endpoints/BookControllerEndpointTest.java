package pl.cogtask.librarycognifide.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerEndpointTest {

    @LocalServerPort
    int port;

    @BeforeEach
    final void setup() {
        RestAssured.port = port;
    }

    @Test
    final void getBookDetailsShouldReturnBookWhenIsbnIsPresentInLibrary() {
        String isbn = "9780080568782";
        String endpointUrl = "book/{isbn}";
        given().pathParam("isbn", isbn)
                .when().get(endpointUrl)
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .contentType(ContentType.JSON)
                .assertThat()
                .body("isbn", equalTo(isbn));
    }

    @Test
    final void getBookDetailsShouldReturn404WhenIsbnIsNotPresentInLibrary() {
        String isbn = "non-existing isbn";
        String endpointUrl = "book/{isbn}";
        given().pathParam("isbn", isbn)
                .when().get(endpointUrl)
                .then()
                .statusCode(404)
                .time(lessThan(1000L));
    }

    @Test
    final void getBookByCategoryShouldReturnListOfBooksIfThereAreAny() {
        String category = "Computers";
        String endpointUrl = "category/{category}";
        given().pathParam("category", category)
                .when().get(endpointUrl)
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .assertThat()
                .body("", hasSize(greaterThan(0)));
    }

    @Test
    final void getBookByCategoryShouldReturnEmptyListOfBooksIfThereAreNotAny() {
        String category = "non-existing category";
        String endpointUrl = "category/{category}";
        given().pathParam("category", category)
                .when().get(endpointUrl)
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .assertThat()
                .body("", hasSize(0));
    }

    @Test
    final void getAuthorsRatingShouldReturnOnlyRatedAuthors() {
        String endpointUrl = "rating";
        Response response = given()
                .when()
                .get(endpointUrl)
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<HashMap<String, Float>> responseList = response.jsonPath().get();
        responseList.forEach(elem -> {
            assertNotNull(elem.get("author"));
            assertThat(elem.get("averageRating"), not(equalTo(0.0)));
        });
    }
}
