package pojo.issue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {

    @JsonProperty("username") //annotation is used to update variable name to case required by server
    public String userName; // must be public
    public String password;

    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
