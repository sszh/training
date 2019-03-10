package platform.user.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenAuthenticationToken extends AbstractAuthenticationToken
{
    private UserDetails userDetails;
    private SessionToken token;

    public TokenAuthenticationToken(UserDetails userDetails, SessionToken token, UserDetails managerDetails)
    {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
        this.token = token;
        this.setDetails(managerDetails);
    }

    public String getCredentials()
    {
        return token.getValue();
    }

    public UserDetails getPrincipal()
    {
        return userDetails;
    }

    public SessionToken getToken()
    {
        return token;
    }
}
