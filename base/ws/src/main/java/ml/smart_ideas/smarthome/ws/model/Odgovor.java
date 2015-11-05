package ml.smart_ideas.smarthome.ws.model;

/**
 * Created by Admin on 5.11.2015..
 */
public class Odgovor {
    private String error;
    private String username;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void ch(String error)
    {
        this.error=error;
    }
}
