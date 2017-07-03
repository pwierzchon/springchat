package pl.destino.springchat.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import pl.destino.springchat.entity.ChatRole;
import pl.destino.springchat.entity.ChatUser;
import pl.destino.springchat.service.IUserService;
import pl.destino.springchat.util.ChatException;

@Component
public class TokenAuthProvider implements AuthenticationProvider {
	
	@Autowired
	private IUserService userService;

	 
	@Override
	public Authentication authenticate(Authentication authToken)
			throws AuthenticationException {
		
		String username = authToken.getPrincipal().toString();
		String password = authToken.getCredentials().toString();
		
		ChatUser user = null;
		UsernamePasswordAuthenticationToken newToken = null;
        
    	try {
    		user = userService.getUserByUsername(username);
    	}
    	catch(ChatException e)	{
    		e.printStackTrace();
    	}
    	
    	if (user == null){
    		throw new BadCredentialsException("Invalid username");
    	}
    	else	{
	    	List<ChatRole> roles = user.getRoles();
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (ChatRole role : roles)	{
	        	GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
	        	authorities.add(authority);
	        }
	    	
	        
	        if (password.equals(user.getUserPassword())) {
	        	newToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
	        }
	        else	{
				throw new BadCredentialsException("Invalid password");
			}
    	}
		return newToken;
	}

	@Override
	public boolean supports(Class<?> arg0) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0));

	}

	
}
