package com.cg.bench.food_recipe.auth_service.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	private JWTokenProvider jwTokenProvider;
	
	public JWTokenFilterConfigurer(JWTokenProvider jwTokenProvider) {
		this.jwTokenProvider = jwTokenProvider;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		JWTokenFilter customFilter = new JWTokenFilter(jwTokenProvider);
	    builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
