package platform.user.auth.token.store;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import platform.user.auth.token.SessionToken;

public class PersistentTokenStore implements TokenStore
{
    @Autowired
    private TokenRepository tokenRepository;

    public boolean isValidToken(String tokenValue)
    {
        SessionToken token = null;
        try
        {
            token = tokenRepository.findByValue(UUID.fromString(tokenValue));
        }
        catch (Exception e)
        {
            return false;
        }

        if ((token == null))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void addToken(SessionToken token)
    {
        if (token != null)
        {
            tokenRepository.save(token);
        }
    }

    public void removeToken(SessionToken token)
    {
        if (token != null)
        {
            tokenRepository.delete(token);
        }
    }

    public SessionToken getTokenByValue(String tokenValue)
    {
        try
        {
            SessionToken token = tokenRepository.findByValue(UUID.fromString(tokenValue));
            return token;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void updateToken(SessionToken token)
    {
        if (token != null)
        {
            tokenRepository.save(token);
        }
    }
}
