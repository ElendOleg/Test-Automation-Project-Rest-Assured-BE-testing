//npm config set https-proxy http://user:password@proxy-for-https:8080
//json-server --watch db.json

package reqresTests;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static io.restassured.specification.ProxySpecification.host;

public class TestBase {

    @BeforeClass
    public void setUpSettings() {
        RestAssured.proxy = host("proxy.t-systems.ru").withPort(3128);
        RestAssured.filters(new AllureRestAssured()); // вынести для всего
    }

   /*@AfterClass
    public void setUpTeardown() {
        try {
            String command = "cmd.exe /c start "+"allure serve allure-results";
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
