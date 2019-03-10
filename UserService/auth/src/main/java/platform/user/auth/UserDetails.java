package platform.user.auth;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import platform.user.model.User;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails
{
    private final User user;

    public UserDetails(User user)
    {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new RoleAuthority("ROLE_ADMIN"));//TODO
        return authorities;
    }

    public String getPassword()
    {
        return user.getPassword();
    }

    public String getUsername()
    {
        return user.getUsername();
    }

    public boolean isAccountNonExpired()
    {
        return true;
    }

    public boolean isAccountNonLocked()
    {
        return true;
    }

    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public boolean isEnabled()
    {
        return true;
    }
}
