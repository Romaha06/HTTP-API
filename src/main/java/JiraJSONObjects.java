import com.github.tsohr.JSONObject;

public class JiraJSONObjects {


    public static String newIssueJSON(){
        JSONObject newIssueJSON = new JSONObject();
        JSONObject fields = new JSONObject();
        fields.put("summary", "Test API");
        JSONObject issueType = new JSONObject();
        issueType.put("name", "Bug");
        JSONObject project = new JSONObject();
        project.put("id", "11400");
        JSONObject assignee = new JSONObject();
        assignee.put("name", "Roman_Chelombitko");

        fields.put("issuetype", issueType);
        fields.put("project", project);
        fields.put("assignee", assignee);

        newIssueJSON.put("fields", fields);
        return newIssueJSON.toString();
    }

    public static String credentialsJSON(){
        JSONObject credentialsJSON = new JSONObject();
        credentialsJSON.put("username", "Roman_Chelombitko");
        credentialsJSON.put("password", "Roman_Chelombitko");
        return credentialsJSON.toString();
    }
}
