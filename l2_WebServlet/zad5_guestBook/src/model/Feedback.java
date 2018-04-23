package model;

public class Feedback {

    private String name;

    private String email;

    private String content;

    private String login;

    public Feedback(String name, String email, String content, String login) {
        this.name = name;
        this.email = email;
        this.content = content;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
