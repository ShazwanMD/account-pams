package my.edu.umk.pams.account.identity.model;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcUser extends AcPrincipal {

    String DEFAULT_PASSWORD = "abc123";

    String getUsername();

    void setUsername(String username);

    String getRealName();

    void setRealName(String firstName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    AcActor getActor();

    void setActor(AcActor actor);
}
