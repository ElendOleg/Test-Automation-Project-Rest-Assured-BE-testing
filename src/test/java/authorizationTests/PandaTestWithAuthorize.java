package authorizationTests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reqresTests.TestBase;
import utils.PandaLogic;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PandaTestWithAuthorize extends TestBase {
    PandaLogic pandaLogic;

    @BeforeClass
    public void setUp() {
        pandaLogic = PandaLogic.authorizeToPandascope();
    }

    @Test
    public void test() {
        Response resp = given()
                .spec(PandaLogic.getReqSpec())
                .get("/lives");
        resp.prettyPrint();
        assertEquals(resp.statusCode(), 200);
    }
}
