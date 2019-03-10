package platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class ProviderConfigurer
{
    private List<Object> providers;
    private ObjectMapper objectMapper;

    public List<Object> getProviders()
    {
        return providers;
    }

    protected ObjectMapper getObjectMapper()
    {
        return objectMapper;
    }

    protected ProviderConfigurer(ObjectMapper objectMapper)
    {
        this.objectMapper = (objectMapper != null ? objectMapper : createObjectMapper());

        providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider(this.objectMapper));
        providers.addAll(createParameterConversionProviders());
    }

    private static ObjectMapper createObjectMapper()
    {
        ObjectMapper objectMapper = null;
        try
        {
            objectMapper = new ObjectMapperConfigContext().objectMapper();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objectMapper;
    }

    private static List<Object> createParameterConversionProviders()
    {
        List<Object> providers = new ArrayList<Object>();
        return providers;
    }
}
