package it.springsecurity.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import it.springsecurity.error.handlers.SecurityAccessDeniedHandler;

@Configuration
@ComponentScan({ "it.springsecurity.conf.comp", "it.springsecurity.service", "it.springsecurity.controller" })
@EnableJpaRepositories(basePackages = "it.springsecurity.repository")
@EntityScan(basePackages = "it.springsecurity.users")
@EnableAsync
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/main", true);
		 // Tutti gli utenti autenticati possono accedere
		http.authorizeRequests().mvcMatchers("/403").hasAnyAuthority("READER","GUEST","CREATOR");
		// Per tutti gli altri url se il ruolo dell'utente non è compreso tra quelli elencati l'accesso è respinto e gestito dall'handler
		http.authorizeRequests().anyRequest().hasAnyAuthority("READER", "GUEST").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}

	@Bean
	public SecurityAccessDeniedHandler accessDeniedHandler() {
		return new SecurityAccessDeniedHandler();
	}
}