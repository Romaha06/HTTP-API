import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class CreateAndDeleteIssue {


    @Test
    public void createAndDeleteIssue() {

        String credentialsJSON = JiraJSONObjects.credentialsJSON();
        String issueJson = JiraJSONObjects.newIssueJSON();


//Authorization
        String sessionId = given().
                header("Content-Type", "application/json").
                body(credentialsJSON).
                when().
                post("https://jira.hillel.it/rest/auth/1/session").
                then().
                extract().
                path("session.value");

        System.out.println("\nSESSION: " + sessionId);

//Create issue
        Response createIssue =
                given().
                auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                header("Content-Type", "application/json").
                body(issueJson).
                when().
                post("https://jira.hillel.it/rest/api/2/issue").
                then().
                extract().response();
        assertEquals(201, createIssue.statusCode());
        System.out.println("Status code:" + createIssue.statusCode());
        String issueId =  createIssue.then().extract().path("id");
        String responseBodyCreate = createIssue.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodyCreate);

//Delete issue
        Response deleteIssue =
        given().
                auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                when().
                delete("https://jira.hillel.it/rest/api/2/issue/" + issueId).
                then().
                extract().response();
        assertEquals(deleteIssue.statusCode(), 204);
        System.out.println("\nStatus code:" + deleteIssue.statusCode());

//Get Existing Issue
        Response getExistingIssue =
        given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(issueJson).
                when().
                get("https://jira.hillel.it/rest/api/2/issue/" + issueId).
                then().
                extract().response();
        assertEquals(getExistingIssue.statusCode(),404);
        System.out.println("Status code:" + getExistingIssue.statusCode());
        String responseBodyGet = getExistingIssue.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodyGet);
    }
}
