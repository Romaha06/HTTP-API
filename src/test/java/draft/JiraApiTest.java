package draft;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JiraApiTest {

    @Feature("Get Existing Issue")
    @Test(groups = {"Regression"})
    public void getExistingIssue() {

        Response response =
                given().
                        auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                        when().
                        get("http://jira.hillel.it/rest/api/2/issue/QAAUT8-1385").
                        then().
                        extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("QAAUT8-1385", response.path("key"));
        String responseBody = response.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBody);
    }


    @Feature("Create Issue")
    @Test(groups = {"Regression"})
    public void createIssue() {

        String issueJson = "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"id\": \"11400\"\n" +
                "        },\n" +
                "        \"summary\": \"Test API\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \"Bug\"\n" +
                "        },\n" +
                "        \"assignee\": {\n" +
                "            \"name\": \"Roman_Chelombitko\"\n" +
                "        },\n" +
                "        \"reporter\": {\n" +
                "            \"name\": \"Roman_Chelombitko\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Response response =
                given().
                        auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                        header("Content-Type","application/json").
                        body(issueJson).
                        when().
                        post("https://jira.hillel.it/rest/api/2/issue").
                        then().
                        extract().response();
        assertEquals(201, response.statusCode());
        String responseBodyCreate = response.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodyCreate);
    }


    @Feature("Delete Issue")
    @Test(groups = {"Regression"})
    public void deleteIssue(){
        Response response =
                given().
                        auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                        when().
                        delete("https://jira.hillel.it/rest/api/2/issue/67271").
                        then().
                        extract().response();
        assertEquals(204, response.statusCode());
    }


}
