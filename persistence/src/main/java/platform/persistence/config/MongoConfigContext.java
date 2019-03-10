package platform.persistence.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * Created by admin on 2019/2/17.
 */
@Configuration
public class MongoConfigContext
{
    @Value("${mongo-host}")
    private String serverHost;

    @Value("${mongo-dbname}")
    private String databaseName;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException
    {
        return new SimpleMongoDbFactory(new MongoClient(serverHost), databaseName);
    }

    @Bean
    public StandarMongoTemplate mongoTemplate() throws UnknownHostException
    {
        return new StandarMongoTemplate(mongoDbFactory());
    }
}
