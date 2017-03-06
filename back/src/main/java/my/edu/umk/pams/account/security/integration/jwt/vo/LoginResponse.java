package my.edu.umk.pams.account.security.integration.jwt.vo;

/**
 * Created by shazin on 12/7/16.
 */
public class LoginResponse {

    private String token;
    private String code;
    private int status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
