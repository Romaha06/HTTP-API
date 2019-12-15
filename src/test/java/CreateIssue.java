
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.help.JiraAPISteps;

import static org.testng.Assert.assertEquals;

public class CreateIssue {


    @Test
    public void createIssue(){

        /* create issue */
        Response response = JiraAPISteps.createIssue();
        assertEquals(response.statusCode() , 201);
        String issueId = response.then().extract().path("id");

        /* get issue to confirm that it exists*/
        JiraAPISteps.getIssue(issueId);


    }
}
