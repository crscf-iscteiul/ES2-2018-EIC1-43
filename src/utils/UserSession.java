package utils;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSession.
 */
public class UserSession {

    /** The name. */
    private String name;
    
    /** The mail. */
    private String mail;

    /**
     * Instantiates a new user session.
     *
     * @param name the name
     * @param mail the mail
     */
    public UserSession(String name, String mail){
        this.name = name;
        this.mail = mail;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

}
