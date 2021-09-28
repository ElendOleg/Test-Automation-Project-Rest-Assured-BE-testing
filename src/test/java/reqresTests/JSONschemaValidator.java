package reqresTests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class JSONschemaValidator extends TestBase {

    @Test
    @Description("GET Reqres web")
    public void testReqresGet() {
        baseURI = "https://reqres.in/api";
        given()
                  .get("/users?page=2")
                .then()
                  .assertThat().body(matchesJsonSchemaInClasspath("\\validation_schemas\\schema_reqres.json"))
                  .statusCode(200);
    }

    @Test
    @Description("GET Local API")
    public void getLocalTest() {

        given()
                .get("http://localhost:3000/users/2")
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("\\validation_schemas\\schema_local_get_users.json"));
    }


    @Test
    @Description("GET Local API")
    public void getHttpBinTest() {
        given()
                .get("http://httpbin.org/get")
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("\\validation_schemas\\schema_httpBin_get.json"))
                .statusCode(200).log().all();
    }

    @Test
    public void postTest() {
        baseURI ="http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("firstName","Ututu");
        request.put("lastName","Itutututu");
        request.put("idSub", 23);

        given()
                  .contentType(ContentType.JSON)
                  .accept(ContentType.JSON)
                  .body(request.toString())
                .when()
                  .post("/users")
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("\\validation_schemas\\schema_local_post.json"))
                  .statusCode(201).log().all();
    }
}
