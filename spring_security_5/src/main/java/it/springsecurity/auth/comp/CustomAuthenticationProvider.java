package it.springsecurity.auth.comp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// authentication logic here
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		UserDetails userDetails = null;
		try {
			userDetails = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			throw new AuthenticationCredentialsNotFoundException("User not found!");
		}
		String encodedPassword = passwordEncoder.encode(password);
		if (!passwordEncoder.matches(encodedPassword, userDetails.getPassword())) {
			throw new AuthenticationCredentialsNotFoundException("Password does not match!");
		}
		return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		// Type of the Authentication implementation here
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
	}
}
