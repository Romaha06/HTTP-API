package utils.help;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HTTPMethods {

    public static Response post(String urlPath, String body) {
        Response response = given().
                auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                header("Content-Type", "application/json").
                body(body).
                when().
                post(APIPathes.baseURL + urlPath);
        return response;
    }

    public static Response delete(String urlPath) {
        Response response = given().
                auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                header("Content-Type", "application/json").
                when().
                post(APIPathes.baseURL + urlPath);
        return response;
    }

    public static Response get(String urlPath) {
        Response response = given().
                auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + Authorization.JSESSIONID).
                when().
                get(APIPathes.baseURL + urlPath);
        return response;
    }

}
