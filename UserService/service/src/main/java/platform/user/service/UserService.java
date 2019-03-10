package platform.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.user.api.UserServiceInterface;
import platform.user.auth.token.SessionToken;
import platform.user.auth.token.TokenGenerator;
import platform.user.auth.token.store.TokenStore;
import platform.user.model.LoginRequest;
import platform.user.model.LoginResponse;
import platform.user.model.User;
import platform.user.model.UserRepository;

import java.util.Collection;

@Service
public class UserService implements UserServiceInterface
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private TokenStore tokenStore;

    public void createUser(User user)
    {
        userRepository.save(user);
    }

    public Collection<User> getUsers()
    {
        return userRepository.findAll();
    }

    public LoginResponse login(LoginRequest loginRequest)
    {
        User user = auth(loginRequest.getUsername(), loginRequest.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        if(user != null)
        {
            loginResponse.setUsername(user.getUsername());
            SessionToken sessionToken = tokenGenerator.createToken(user.getUsername());
            loginResponse.setToken(sessionToken.getValue());
            tokenStore.addToken(sessionToken);
        }
        return loginResponse;
    }

    private User auth(String username, String password)
    {
        User user = null;
        User dbUser = userRepository.getByUsername(username);
        if(dbUser.getPassword().equals(password))
        {
            user = dbUser;
        }
        return user;
    }
}
