package platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceProviderConfigurer extends ProviderConfigurer
{
    public ServiceProviderConfigurer(ObjectMapper objectMapper)
    {
        super(objectMapper);

        addServiceProviders();
    }

    private void addServiceProviders()
    {

    }
}
