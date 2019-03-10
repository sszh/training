package platform.user.auth.token.store;

import platform.user.auth.token.SessionToken;

public interface TokenStore
{
    public boolean isValidToken(String tokenValue);
    public void addToken(SessionToken token);
    public void removeToken(SessionToken token);
    public SessionToken getTokenByValue(String tokenValue);
    public void updateToken(SessionToken token);
}
