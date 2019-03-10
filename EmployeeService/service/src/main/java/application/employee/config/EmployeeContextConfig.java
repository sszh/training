package application.employee.config;

import application.employee.api.EmployeeServiceInterface;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import platform.config.RestConfigContext;
import platform.persistence.config.MongoConfigContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/2/24.
 */
@Configuration
@EnableMongoRepositories({"application.employee.data"})
@ComponentScan({"application.employee"})
@Import({MongoConfigContext.class, RestConfigContext.class})
@PropertySource({"classpath:employee-service.properties"})
public class EmployeeContextConfig
{
    @Autowired
    private JAXRSServerFactoryBean jaxrsServerFactoryBean;

    @Autowired
    private EmployeeServiceInterface employeeService;

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
        endpoints.add(employeeService);
        jaxrsServerFactoryBean.setServiceBean(endpoints);

        return jaxrsServerFactoryBean.create();
    }
}
