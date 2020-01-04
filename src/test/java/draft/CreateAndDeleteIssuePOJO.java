package draft;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.issue.Credentials;
import pojo.issue.Issue;
import utils.help.Authorization;
import utils.help.PojoHelper;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateAndDeleteIssuePOJO {
    String projectId = "11400";
    String issueType = "Bug";

    @Test(groups = {"Regression"})
    public void createAndDeleteIssue(){

        String summary = "Summary test from POJO";
        String assignee = "Roman_Chelombitko";
        String reporter = "Roman_Chelombitko";

        Credentials credentialsPOJO = PojoHelper.generateJSONForLogin(Authorization.username, Authorization.password);


        String sessionId = given().
                header("Content-Type", ContentType.JSON).
                body(credentialsPOJO).
                when().
                post("https://jira.hillel.it/rest/auth/1/session").
                then().
                statusCode(200).contentType(ContentType.JSON).
                extract().
                path("session.value");

        System.out.printf("\nSESSION: " + sessionId);


        Issue issuePOJO = PojoHelper.generateJSONForIssue(projectId, summary, issueType, assignee, reporter);
//Create issue
        Response createIssue =
                given().
                        auth().preemptive().basic("Roman_Chelombitko", "Roman_Chelombitko").
                        header("Content-Type", "application/json").
                        body(issuePOJO).
                        when().
                        post("https://jira.hillel.it/rest/api/2/issue").
                        then().
                        extract().response();
        assertEquals(createIssue.statusCode(), 201);
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
                        body(issuePOJO).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/" + issueId).
                        then().
                        extract().response();
        assertEquals(getExistingIssue.statusCode(),404);
        System.out.println("Status code:" + getExistingIssue.statusCode());
        String responseBodyGet = getExistingIssue.then().extract().asString();
        System.out.printf("\nRESPONSE: " +  responseBodyGet);
    }
}
