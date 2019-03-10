package platform.user.model;

import org.springframework.data.mongodb.core.mapping.Document;
import platform.persistence.model.ModelBase;

@Document(collection = "User")
public class User extends ModelBase
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
