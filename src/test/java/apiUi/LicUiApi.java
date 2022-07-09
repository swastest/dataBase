package apiUi;

import com.codeborne.selenide.WebDriverRunner;
import config.LinkOwner;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.devtools.v85.cachestorage.model.Header;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;

public class LicUiApi {
@Test
    void test001() {
    LinkOwner conf = ConfigFactory.create(LinkOwner.class);
    Map<String, String> user = new HashMap<>();
    user.put("password", "1234");
    user.put("phone", "79999999999");
    String tokenValue = given()
            .body(user)
            .contentType(ContentType.JSON)
            .when().log().all()
            .post(conf.url()+"/auth/signin")
            .then().log().all()
            .extract().body().path("accessToken");
    System.out.println(tokenValue);

    String headerName = "Authorization";
    open(conf.urlUi()+"/wp-content/themes/liveinclean/img/sun.png");
    Cookie authCookie = new Cookie(headerName, tokenValue);
    Header he = new Header(headerName,tokenValue);
    WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
    open(conf.urlUi()+"/front/statistics-orders");



    /*

    ===1
@Test
public void Proxy() {
Configuration.proxyHost = ClientUtil.getConnectableAddress().getHostAddress();
Configuration.proxyEnabled = true;
open("");

SelenideProxyServer selenideProxy = getSelenideProxy();
selenideProxy.addRequestFilter("proxy-usages.request", (request, contents, messageInfo) -> {
request.headers().add("User-Agent", "hacker");
return null;
});

open("https://www.google.ru/");
}


    ===2
    тут человек с хедерами игрался
    https://github.com/selenide/selenide/blob/master/modules/proxy/src/test/java/integration/proxy/ProxyServerUsageTest.java

    */
    sleep(10000);

}



}
