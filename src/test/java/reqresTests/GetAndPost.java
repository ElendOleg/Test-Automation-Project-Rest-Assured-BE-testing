package reqresTests;

import io.qameta.allure.Description;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;
import pojoReqresGET.Datum;
import pojoReqresGET.PojoReqres;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetAndPost extends TestBase {
    private RequestSpecification reqSpec;
    private ResponseSpecification respSpec;

    @BeforeClass
    public void setSpecifications() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
        respSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    @Test
    @Description("GET")
    public void testGet() {
        Response resp = given().spec(null).get("/users?page=2");
        resp.prettyPrint();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.statusCode(), 200);
        //softAssert.assertEquals(resp.getHeaders().getValue("ContentType"),"JSON");
        softAssert.assertTrue(resp.getTimeIn(TimeUnit.SECONDS) <= 5, "RespTime isn't within the limit");
        softAssert.assertEquals(resp.getBody().path("data[3].id").toString(), "10", "wrong id");
        softAssert.assertEquals(resp.getBody().path("data[3].email"), "byron.fields@reqres.in", "wrong email");
        softAssert.assertEquals(resp.getBody().path("data[3].first_name"), "Byron", "wrong name");
        softAssert.assertEquals(resp.getBody().path("data[3].last_name"), "Fields", "wrong lastName");
        softAssert.assertEquals(resp.getBody().path("data[3].avatar"), "https://reqres.in/img/faces/10-image.jpg", "wrong icon");
        softAssert.assertAll();
    }

    @Test
    @Description("GET with Pojo")
    public void testGetWithPOJO() {
        Response resp = given().spec(reqSpec).get("/users?page=2");            // получили респонс
        resp.prettyPrint();                                                      // красиво нарисовали
        PojoReqres reqres = resp.as(PojoReqres.class, ObjectMapperType.GSON);    // формируем объект типа PojoReqres - самый главный

        SoftAssert softAssert = new SoftAssert();
        List<Datum> data = reqres.getData();
        boolean isIdCorrect = true;

        for (Datum element : data) {                                 // вытаскиваем очередной объект из data
            System.out.println("в json имеется" + element.getId());
            if (element.getId() < 7 || element.getId() > 11) {       // проверяем что id в диап.от 7 до 12
                isIdCorrect = false;
            }
            softAssert.assertTrue(isIdCorrect, "Id isn't correct");  // делаем ассерт
        }
        softAssert.assertAll();                                             // подводим итоги
        //пример того, как реализовать ассерт, получая из Pojo объекта какие-то данные
        Assert.assertEquals(reqres.getSupport().getText(),
                "To keep ReqRes free, contributions towards server costs are appreciated!",
                "Text is wrong");
    }

    @Test
    @Description("POST")
    public void testPost() {
        JSONObject json = new JSONObject();
        json.put("name", "morpheus");
        json.put("job", "leader");
        given()
                .spec(reqSpec)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/users")
                .then()
                .spec(respSpec)
                .statusCode(201).log().all();
    }

}


