package com.guilherme.quarkapi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterConfig extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = recoverToken(request);

		if (token != null) {
			try {
				String userName = tokenService.validateToken(token);
				UserDetails user = userRepository.findByUserName(userName);

				if (user != null) {
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}

			} catch (Exception e) {
				SecurityContextHolder.clearContext();
			}
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return null;
		}
		return authHeader.replace("Bearer ", "").trim();
	}
}
