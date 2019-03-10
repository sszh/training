package platform.user.auth.token;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SessionToken
{
    @Id
    private UUID value;
    private DateTime expirationTime;
    private String userId;
    private long sessionLength;

    public SessionToken()
    {

    }

    protected SessionToken(String userId, long sessionLength)
    {
        this.value = UUID.randomUUID();
        this.expirationTime = new DateTime();
        this.sessionLength = sessionLength;
        this.extendExpirationTime();
        this.userId = userId;
    }

    public boolean isExpired()
    {
        return this.expirationTime.isBeforeNow();
    }

    public DateTime getExpirationTime()
    {
        return expirationTime;
    }

    public void extendExpirationTime()
    {
        DateTime extended = (new DateTime()).plus(this.sessionLength);
        this.expirationTime = extended;
    }

    public String getValue()
    {
        return value.toString();
    }

    public String getUserId()
    {
        return userId;
    }
}
