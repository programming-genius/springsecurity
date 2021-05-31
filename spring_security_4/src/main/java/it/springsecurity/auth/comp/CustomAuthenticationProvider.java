package it.springsecurity.auth.comp;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// authentication logic here
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		if ("john".equals(username) && "1234".equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
		} else {
			throw new AuthenticationCredentialsNotFoundException("Error!");

		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		// Type of the Authentication implementation here
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
	}
}
