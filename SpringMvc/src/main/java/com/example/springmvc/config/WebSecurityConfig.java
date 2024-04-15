package com.example.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springmvc.service.UserDetailServiceImpl;

@SuppressWarnings("deprecation"	)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity.csrf().disable();
		
		httpSecurity.authorizeRequests().antMatchers("/login").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/product/**","/customer/**","/orders/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
		httpSecurity.authorizeRequests().antMatchers("/employee/**").access("hasRole('ROLE_ADMIN')");
		httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		httpSecurity.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/product/list")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
    }

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}


}
