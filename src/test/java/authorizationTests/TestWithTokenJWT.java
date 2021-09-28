package authorizationTests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import reqresTests.TestBase;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class TestWithTokenJWT extends TestBase {

    private String token = "";

    @Test
    public void setUpAuthWithJWT() {


        token = given()
                .accept("application/json")
                .auth().basic("mshop", "secret")
                .params(getUserCreds())
                .when()
                .post("https://de2.mshop.csolab.ru/tvpp/oauth/token")
                .getBody().jsonPath().get("access_token");

        given()
                .contentType(ContentType.JSON)
                .header("authorization", "Bearer " + token)
                .when()
                .get("https://de2.mshop.csolab.ru/tvpp/user?profileType=WEB_PROFILE");
    }

    private Map<String, String> getUserCreds() {
        Map<String, String> userCreds = new HashMap() {
            {
                put("username", "mstest3");
                put("password", "rewq4321!");
                put("vo", "TPG02");
                put("forceLogin", "true");
                put("grant_type", "password");
            }
        };
        return userCreds;
    }
}