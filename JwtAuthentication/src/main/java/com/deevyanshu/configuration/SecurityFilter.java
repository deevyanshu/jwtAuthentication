package com.deevyanshu.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deevyanshu.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=request.getHeader("Authorization");
		if(token!=null)
		{
			String username=util.getUsername(token);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails user= detailsService.loadUserByUsername(username);
				boolean isValid=util.validateToken(token, user.getUsername());
				if(isValid)
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
							UsernamePasswordAuthenticationToken(username, user.getPassword(),user.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
