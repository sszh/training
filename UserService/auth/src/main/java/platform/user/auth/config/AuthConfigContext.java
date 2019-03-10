package platform.user.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import platform.user.auth.UserDetailsAuthenticationProvider;
import platform.user.auth.token.TokenAuthenticationEntryPoint;
import platform.user.auth.token.TokenAuthenticationFilter;

import javax.servlet.Filter;
import java.util.*;


@Configuration
@ImportResource("classpath:auth.xml")
public class AuthConfigContext
{
    @Value("${auth.uri.whitelist:none}")
    private String authUriWhitelist;

    @Bean(name = "expressionHandler")
    public DefaultMethodSecurityExpressionHandler expressionHandler()
    {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public FilterChainProxy filterChainProxy()
    {
        List<SecurityFilterChain> filterChains = new ArrayList<SecurityFilterChain>();

        List<Filter> insecureFilters = insecureFilters();

        if (StringUtils.isNotBlank(authUriWhitelist) && !authUriWhitelist.equals("none"))
        {
            for (String authUri : authUriWhitelist.split(","))
            {
                SecurityFilterChain whitelistFilterChain = new DefaultSecurityFilterChain(new AntPathRequestMatcher(
                        authUri), insecureFilters);
                filterChains.add(whitelistFilterChain);
            }
        }

        List<Filter> secureFilters = new ArrayList<Filter>();
        secureFilters.add(tokenAuthenticationFilter());
        secureFilters.add(tokenExceptionTranslationFilter());
        secureFilters.add(filterSecurityInterceptor());

        SecurityFilterChain filterChain = new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"),
                secureFilters);
        filterChains.add(filterChain);

        return new FilterChainProxy(filterChains);
    }

    @Bean(name = "insecureFilters")
    public List<Filter> insecureFilters()
    {
        List<Filter> insecureFilters = new ArrayList<Filter>();
        return insecureFilters;
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter()
    {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public ExceptionTranslationFilter tokenExceptionTranslationFilter()
    {
        return new ExceptionTranslationFilter(authenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint()
    {
        return new TokenAuthenticationEntryPoint();
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor()
    {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());//An AuthenticationManager is required
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());//An AccessDecisionManager is required
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());//required
        filterSecurityInterceptor.setValidateConfigAttributes(false);
        return filterSecurityInterceptor;
    }

    @Bean
    public ProviderManager authenticationManager()
    {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
        authenticationProviders.add(userDetailsAuthenticationProvider());
        ProviderManager providerManager = new ProviderManager(authenticationProviders);
        return providerManager;
    }

    @Bean
    public UserDetailsAuthenticationProvider userDetailsAuthenticationProvider()
    {
        return new UserDetailsAuthenticationProvider();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager()
    {
        return new AffirmativeBased(Arrays.<AccessDecisionVoter> asList(new AuthenticatedVoter()));
        //return new AffirmativeBased(Arrays.asList(new AuthenticatedVoter()));
    }

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource()
    {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        requestMap.put(AnyRequestMatcher.INSTANCE, SecurityConfig.createList(AuthenticatedVoter.IS_AUTHENTICATED_FULLY,
                AuthenticatedVoter.IS_AUTHENTICATED_REMEMBERED));
        return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
    }
}
