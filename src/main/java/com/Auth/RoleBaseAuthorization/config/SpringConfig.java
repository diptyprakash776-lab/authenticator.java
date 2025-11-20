package com.Auth.RoleBaseAuthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin=User.builder().username("di").password(passwordEncoder().encode("123")).roles("ADMIN").build();
		UserDetails user=User.builder().username("de").password(passwordEncoder().encode("12")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(authorize->authorize.requestMatchers(HttpMethod.GET).hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST).hasRole("USER")
						.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
						.anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).build();
	}
}
