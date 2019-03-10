package platform.user.auth.token.store;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import platform.user.auth.token.SessionToken;

@Repository
public interface TokenRepository extends MongoRepository<SessionToken, String>
{
    public SessionToken findByValue(UUID uuid);
}
