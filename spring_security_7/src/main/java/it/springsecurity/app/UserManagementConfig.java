package it.springsecurity.app;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import it.springsecurity.passwords.Sha512PasswordEncoder;

@Configuration
public class UserManagementConfig {
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Sha512PasswordEncoder();
		//return new Pbkdf2PasswordEncoder("secret", 30000, 256);
		// return new BCryptPasswordEncoder(4);
		//return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
	}
}