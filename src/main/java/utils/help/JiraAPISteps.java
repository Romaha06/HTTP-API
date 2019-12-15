package utils.help;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import json.objects.JiraJSONObjects;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class JiraAPISteps {

    public static Response createIssue() {
        String issueJson = JiraJSONObjects.newIssueJSON();
        Response response =  HTTPMethods.post(APIPathes.issue, issueJson);
        assertEquals(response.statusCode(),201);
        String responseBodyCreate = response.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodyCreate);
        return response;
    }

    public static String getIssue(String issueId) {
        Response response = HTTPMethods.get(APIPathes.issue + issueId);
          Assert.assertEquals(response.then().extract().statusCode(), 200);
          Assert.assertTrue(response.then().extract().contentType().contains(ContentType.JSON.toString()));
        String responseBodyGet = response.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodyGet);
        return response.then().extract().asString();

    }

}
