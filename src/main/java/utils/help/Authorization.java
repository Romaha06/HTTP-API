package utils.help;

import io.restassured.http.ContentType;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class Authorization {

    public static String JSESSIONID;
    public static String username = "Roman_Chelombitko";
    public static String password = "Roman_Chelombitko";
    static final Logger logger = Logger.getLogger(String.valueOf(Authorization.class));

    public static void loginToJIRA() {

        String loginJson = JsonObjectHelper.generateJSONForLogin();
        JSESSIONID =
                given().
                        header("Content-Type", ContentType.JSON).
                        body(loginJson).
                        when().
                        post("https://jira.hillel.it/rest/auth/1/session").
                        then().
                        statusCode(200).contentType(ContentType.JSON).
                        extract().
                        path("session.value");

        logger.info("\nAUTHORIZATION TOKEN: " + Authorization.JSESSIONID);
    }
}
