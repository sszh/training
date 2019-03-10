package platform.persistence.config;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by admin on 2019/2/17.
 */
public class StandarMongoTemplate extends MongoTemplate
{
    public StandarMongoTemplate(Mongo mongo, String databaseName)
    {
        super(mongo, databaseName);
    }

    public StandarMongoTemplate(MongoDbFactory mongoDbFactory)
    {
        super(mongoDbFactory);
    }
}
