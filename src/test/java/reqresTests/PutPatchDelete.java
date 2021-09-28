package reqresTests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutPatchDelete extends TestBase {

    public JSONObject getExpectedJSON() {
        JSONObject jsonExpected = new JSONObject();
        jsonExpected.put("name", "morpheus");
        jsonExpected.put("job", "zion resident");
        return jsonExpected;
    }

    @Test
    @Description("PUT")
    public void testPut() {
        baseURI = "https://reqres.in/api";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(getExpectedJSON().toString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    @Description("PATCH")
    public void testPatch() {
        baseURI = "https://reqres.in/api";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(getExpectedJSON().toString())
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    @Description("DELETE")
    public void testDelete() {
        baseURI = "https://reqres.in/api";
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("name", "morpheus");
        jsonPut.put("job", "zion resident");

        given()
                .delete("/users/2")
                .then()
                .statusCode(204).log().all();
    }
}
