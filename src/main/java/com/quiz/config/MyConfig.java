package com.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class MyConfig {
	@Autowired
	public AuthenticationSuccessHandler customSuccessHandler;
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailServiceImple();
	}
	@Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { 
        return authenticationConfiguration.getAuthenticationManager();
    }
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        
	       
	        .authorizeHttpRequests(auth -> 
	          auth.requestMatchers("/user/**").hasRole("USER")
	              .requestMatchers("/admin/**").hasRole("ADMIN")
	             .requestMatchers("/**").permitAll()
	              .anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
	        .formLogin((formLogin) ->
            formLogin
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/signin")
                    .loginProcessingUrl("/dologin")
                    .successHandler(customSuccessHandler)
                    );
	        									
	          									
	        
	       
  	  

	   

	    http.authenticationProvider(authProvider());
	    
	    return http.build();
	 }
	 @Bean
	    DaoAuthenticationProvider authProvider(){
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(this.getUserDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

}
