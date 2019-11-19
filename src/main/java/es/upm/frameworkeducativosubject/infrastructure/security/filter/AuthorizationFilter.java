package es.upm.frameworkeducativosubject.infrastructure.security.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationFilter implements RequestInterceptor {

    private static final String AUTHENTICATION_HEADER = "authorization";

    @Override
    public void apply(RequestTemplate template) {
        propagateAuthorizationHeader(template);
    }

    private void propagateAuthorizationHeader(RequestTemplate template) {

        if (!template.headers().containsKey(AUTHENTICATION_HEADER)) {
            template.header(AUTHENTICATION_HEADER, SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}
