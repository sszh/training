package platform.user.auth.token;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator
{
    private long sessionLength = 0;

    public TokenGenerator()
    {

    }

    public TokenGenerator(long defaultSessionLength)
    {
        this.sessionLength = defaultSessionLength;
    }

    public SessionToken createToken(String userId)
    {
        return new SessionToken(userId, sessionLength);
    }
}
