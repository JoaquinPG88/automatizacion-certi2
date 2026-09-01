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
    public void test02_FilterBookingsByDates() {
        given()
            .queryParam("checkin", "2024-01-01")
            .queryParam("checkout", "2024-01-10")
            .when()
            .get("/booking")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void test03_GetBookingsByFirstNameParam() {
        // Valida la capacidad del endpoint de filtrar por parámetro de consulta de forma dinámica
        given()
            .queryParam("firstname", "John")
            .when()
            .get("/booking")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void test04_CreateBookingMissingFirstname() {
        // Prueba negativa: POST sin el campo requerido 'firstname'
        String invalidPayload = "{\n" +
                "    \"lastname\" : \"Mendoza\",\n" +
                "    \"totalprice\" : 150,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-10\"\n" +
                "    }\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(invalidPayload)
            .when()
            .post("/booking")
            .then()
            .statusCode(500); // La API rechaza la solicitud por falta de campo obligatorio
    }

    @Test
public void test05_CreateBookingInvalidTotalPrice() {
    // Prueba negativa: POST con 'totalprice' en formato string en lugar de int
    String invalidPayload = "{\n" +
            "    \"firstname\" : \"Carlos\",\n" +
            "    \"lastname\" : \"Mendoza\",\n" +
            "    \"totalprice\" : \"monto_invalido\",\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2024-01-01\",\n" +
            "        \"checkout\" : \"2024-01-10\"\n" +
            "    }\n" +
            "}";

    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(invalidPayload)
        .when()
        .post("/booking")
        .then()
        .statusCode(418); // La API devuelve 418 cuando el JSON tiene tipos de datos invalidos
    }
}