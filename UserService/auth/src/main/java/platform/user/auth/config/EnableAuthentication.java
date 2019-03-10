package platform.user.auth.config;

import org.springframework.context.annotation.Import;

@Import({AuthConfigContext.class})
public @interface EnableAuthentication
{
}
