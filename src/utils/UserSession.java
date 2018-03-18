package utils;

public class UserSession {

    private String name;
    private String mail;
    private Problem p;


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

    public void setProblem(Problem p) {
        this.p = p;
    }

    public Problem getProblem() { return this.p; }
}
