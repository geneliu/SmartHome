package ml.smart_ideas.smarthome.ws.model;

/**
 * Created by dudo on 11/14/15.
 */
public class UserModel {
    private String error;
    private String name;
    private String surname;
    private String username;
    private String password;

    public UserModel() {
    }

    public UserModel(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
    public UserModel(String error, String name, String surname, String username, String password) {
        this.error = error;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
