package platform.user.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginAuthenticationToken extends AbstractAuthenticationToken
{
    private String username;
    private SessionToken token;
    
    public LoginAuthenticationToken(String username, SessionToken token)
    {
        super(null);
        this.username = username;
        this.token = token;
        setAuthenticated(true);
    }
    
    public String getCredentials()
    {
        return token.getValue();
    }

    public String getPrincipal()
    {
        return username;
    }

    public SessionToken getToken()
    {
        return token;
    }
}
