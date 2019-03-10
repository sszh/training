package platform.user.config;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import platform.config.RestConfigContext;
import platform.persistence.config.MongoConfigContext;
import platform.user.api.UserServiceInterface;
import platform.user.auth.config.EnableAuthentication;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories({"platform.user.model"})
@ComponentScan({"platform.user"})
@Import({MongoConfigContext.class, RestConfigContext.class})
@PropertySource({"classpath:user-service.properties"})
@EnableAuthentication
public class UserConfigContext
{
    @Autowired
    private JAXRSServerFactoryBean jaxrsServerFactoryBean;

    @Autowired
    private UserServiceInterface userService;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer propConfigurer = new PropertySourcesPlaceholderConfigurer();
        propConfigurer.setLocalOverride(true);
        return propConfigurer;
    }

    @Bean
    public Server server()
    {
        List<Object> endpoints = new ArrayList<Object>();
        endpoints.add(userService);
        jaxrsServerFactoryBean.setServiceBean(endpoints);
        return jaxrsServerFactoryBean.create();
    }
}
