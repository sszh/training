package platform.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfigContext
{
    @Bean
    public ObjectMapper objectMapper() throws Exception
    {
        ObjectMapper jacksonMapper = new ObjectMapper();
        return jacksonMapper;
    }
}
