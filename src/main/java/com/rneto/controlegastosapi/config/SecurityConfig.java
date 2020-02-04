package com.rneto.controlegastosapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * Define de onde vem as senha e ralcionados
	 * no password foi add {noop} para Auth Basic, se fosse BCrypt {bcrypt}, ...
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin").roles("ROLE");
	}
	
	/**
	 * Define tipo de autenticaçao, e onde vai ser solicitado..
	 * No momen é autenticaçao basica
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()  								// define quais requisições com autorizaçao
				.antMatchers("/grupos").permitAll() 			// define url que nao precisa de autenticacao
				.anyRequest().authenticated() 					// define tudas as requicoes com autenticacao
				.and()
			.httpBasic().and()									// define o tipo de autenticacao
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()	// desabilita a criacao de session
			.csrf().disable();									// desabilita javascript injection 
	}
}
