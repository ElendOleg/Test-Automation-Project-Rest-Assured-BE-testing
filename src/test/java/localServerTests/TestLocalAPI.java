package localServerTests;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TestLocalAPI {

    @Test
    public void getTest() {
        baseURI ="http://localhost:3000";
        given()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("firstName",equalTo("Olya"))
                .body("lastName",equalTo("Ivanova"))
                .body("idSub",equalTo(323241))
                //.body("users[0].firstName",equalTo("Oleg"))
                .log().all();
    }

    @Test
    public void postTest() {
        baseURI ="http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("firstName","Mike");
        request.put("lastName","Jikov");
        request.put("idSub", 323241);
        given()
                  .contentType(ContentType.JSON)
                  .accept(ContentType.JSON)
                  .body(request.toString())
                .when()
                  .post("/users")
                .then()
                  .statusCode(201).log().all();
    }

    @Test
    public void putTest() {
        baseURI ="http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("firstName", "Pip");
        request.put("lastName", "Pipov");
        request.put("idSub", 1345);
        given()
                  .contentType(ContentType.JSON)
                  .accept(ContentType.JSON)
                  .body(request.toString())
                .when()
                  .put("/users/8")
                .then()
                  .statusCode(200).log().all();
    }

    @Test
    public void patchTest() {
        baseURI ="http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("lastName", "Didenkov");
        given()
                  .contentType(ContentType.JSON)
                  .accept(ContentType.JSON)
                  .body(request.toString())
                .when()
                  .patch("/users/3")
                .then()
                  .statusCode(200).log().all();
    }

    @Test
    public void deleteTest() {
        baseURI ="http://localhost:3000";
        given()
                  .delete("/users/4")
                .then()
                  .statusCode(200).log().all();
    }
}
