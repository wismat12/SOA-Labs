package model;

public class DaneOsobowe {

    private String login;

    private String password;

    private String name;

    private String surname;

    private String token;

    public DaneOsobowe(String login, String password, String name, String surname, String token) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
