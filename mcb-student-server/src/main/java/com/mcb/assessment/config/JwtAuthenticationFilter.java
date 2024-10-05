package com.mcb.assessment.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.header.string}")
	private String HEADER_STRING;

	@Value("${jwt.token.prefix}")
	private String TOKEN_PREFIX;

	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(HEADER_STRING);
		String authToken = null;
		String username = null;

		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			authToken = header.replace(TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("An error occurred while fetching the username from the token.", e);
			} catch (ExpiredJwtException e) {
				logger.warn("The token has expired.", e);
			} catch (SignatureException e) {
				logger.error("Authentication failed. Invalid signature or credentials.");
			}
		} else {
			logger.warn("Bearer string not found, ignoring the header.");
		}

		// Authenticate the user if a valid token is provided
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(
						authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("Authenticated user: " + username + ". Setting security context.");

				// Set the authenticated user in the security context
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				logger.warn("Invalid token for user: " + username);
			}
		}

		chain.doFilter(request, response);
	}
}
