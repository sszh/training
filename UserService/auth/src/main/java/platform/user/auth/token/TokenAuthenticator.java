package platform.user.auth.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import platform.user.auth.UserDetails;
import platform.user.model.User;

@Component
public class TokenAuthenticator
{
    public void authenticateToken(String tokenValue, String managerTokenValue)
    {
        SessionToken token = null;
        SessionToken managerToken = null;
        try
        {
            if (tokenValue == null)
            {
                throw new AuthenticationCredentialsNotFoundException("Token not found");
            }

            SecurityContext context = SecurityContextHolder.getContext();
            UserDetails userDetails = new UserDetails(new User());

            UserDetails managerDetails = null;
            TokenAuthenticationToken authentication = new TokenAuthenticationToken(userDetails, token, managerDetails);
            // Authenticated
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException failed)
        {
            SecurityContextHolder.clearContext();
        }
    }
}
