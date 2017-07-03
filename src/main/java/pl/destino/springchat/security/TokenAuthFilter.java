package pl.destino.springchat.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Value("${auth.token.name}")
    private String tokenName;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.getContext();

        try {
            String authToken = request.getHeader(tokenName);

            if (authToken != null) {
                String[] credentials = authToken.split(":");

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
                SecurityContextHolder.getContext().setAuthentication(token);

            }
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
