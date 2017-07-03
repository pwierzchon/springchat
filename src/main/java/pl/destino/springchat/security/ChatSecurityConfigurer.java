package pl.destino.springchat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ChatSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthFilter tokenAuthFilter;
	
	@Autowired
	private TokenAuthProvider tokenAuthProvider;
		
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
        http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()        
        .authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/*.html", "/node_modules/**","/").permitAll()
        .antMatchers("/search","/add","/user","/findall").hasRole("USER")
        .and().authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
        .csrf().disable()
        .authenticationProvider(tokenAuthProvider)
        .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class);
        
       
    }
	
}
