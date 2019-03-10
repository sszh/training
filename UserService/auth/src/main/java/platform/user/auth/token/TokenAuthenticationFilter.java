package platform.user.auth.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean
{
    @Autowired
    private TokenAuthenticator tokenAuthenticator;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        String tokenValue = req.getHeader("token");
        if (tokenValue == null && req.getCookies() != null)
        {
            for (Cookie cookie : req.getCookies())
            {
                if (cookie.getName().equals("token"))
                {
                    tokenValue = cookie.getValue();
                }
            }
        }

        tokenAuthenticator.authenticateToken(tokenValue, "");
        filterChain.doFilter(request, response);
    }

}
