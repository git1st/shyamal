package com.shyamal.powerledger.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shyamal.powerledger.model.CustomUserDetail;
import com.shyamal.powerledger.service.CustomUserDetailService;
import com.shyamal.powerledger.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	public JWTService jwtService;
	@Autowired
	public CustomUserDetailService customUserDetailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException {
		
		String header=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(header!=null && header.startsWith("Bearer "))
		{
			token = header.substring(7);
            username = jwtService.extractUsername(token);
		}
		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 	UserDetails userDetails=customUserDetailService.loadUserByUsername(username);
	            if (jwtService.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	}

}
