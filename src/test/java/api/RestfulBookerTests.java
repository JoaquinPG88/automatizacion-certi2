package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestfulBookerTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void test01_GetAllBookings() {
        given()
            .when()
            .get("/booking")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    public void test02_GetBookingById() {
        // Asumiendo que el ID 1 o 2 casi siempre existen en esta API pública
        given()
            .when()
            .get("/booking/1")
            .then()
            .statusCode(200)
            .body("firstname", notNullValue())
            .body("lastname", notNullValue());
    }

    @Test
    public void test03_GetBookingsByFirstName() {
        given()
            .queryParam("firstname", "sally") // Filtro
            .when()
            .get("/booking")
            .then()
            .statusCode(200);
    }

    @Test
    public void test04_GenerateAuthToken() {
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .body("token", notNullValue());
    }

    @Test
public void test05_CreateNewBooking() {
    String newBooking = "{\n" +
            "    \"firstname\" : \"Carlos\",\n" +
            "    \"lastname\" : \"Mendoza\",\n" +
            "    \"totalprice\" : 150,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2024-01-01\",\n" +
            "        \"checkout\" : \"2024-01-10\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";

    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(newBooking)
        .when()
        .post("/booking")
        .then()
        .statusCode(200)
        .body("booking.firstname", equalTo("Carlos"))
        .body("bookingid", notNullValue());
    }
}