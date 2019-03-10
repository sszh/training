package platform.user.auth;

import org.springframework.security.core.GrantedAuthority;

public class RoleAuthority implements GrantedAuthority
{
    private final String role;

    public RoleAuthority(String role)
    {
        this.role = role;
    }

    public String getAuthority()
    {
        return role;
    }
}
