package it.springsecurity.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringSecurityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
    private static Class<SpringSecurityApplication> applicationClass = SpringSecurityApplication.class;

}
