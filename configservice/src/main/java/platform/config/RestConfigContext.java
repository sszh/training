package platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.spring.JAXRSServerFactoryBeanDefinitionParser.SpringJAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({ObjectMapperConfigContext.class})
public class RestConfigContext
{
    @Autowired
    private ObjectMapper objectMapper;

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public JAXRSServerFactoryBean jaxrsServerFactoryBean()
    {
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new SpringJAXRSServerFactoryBean();
        jaxrsServerFactoryBean.setAddress("/");
        jaxrsServerFactoryBean.setProviders(providerConfigurer().getProviders());
        return jaxrsServerFactoryBean;
    }

    @Bean
    public ServiceProviderConfigurer providerConfigurer()
    {
        ServiceProviderConfigurer configurer = new ServiceProviderConfigurer(objectMapper);
        return configurer;
    }
}
