package com.itmcs.roo.mirisk.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTokenFilter extends GenericFilterBean {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomUserDetailsService userDetailsService;

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		DecodedJWT token = null;
		if (null != (token = jwtTokenProvider.verifyToken(jwtTokenProvider.resolveToken((HttpServletRequest) req)))) {
			Authentication auth = new UsernamePasswordAuthenticationToken(token.getClaim("username").asString(),
					token.getClaim("password").asString());
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(req, res);
		} else if (jwtTokenProvider.authenticateUsingCredentials((HttpServletRequest) req, (HttpServletResponse) res)) {
			filterChain.doFilter(req, res);
		} else {

			res.setContentType("application/json");
			res.getWriter().write("Access Denied! Please login again...");
		}
	}
}
