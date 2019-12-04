package utils.help;

import com.github.tsohr.JSONObject;
import utils.help.Authorization;

public class JsonObjectHelper {

    public static String generateJSONForLogin() {
        JSONObject credentials = new JSONObject();
        credentials.put("username", Authorization.username);
        credentials.put("password", Authorization.password);
        return credentials.toString();
    }
}

