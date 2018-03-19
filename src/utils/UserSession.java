package utils;

public class UserSession {

    private String name;
    private String mail;

    public UserSession(String name, String mail){
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

}
