package com.deevyanshu.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private String secret="JWT";
	
	public String generateToken(String username)
	{
		return Jwts.builder().setSubject(username).setIssuer("Deev").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15l)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
	
	public Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
	
	public Date getExpiryDate(String token)
	{
		return getClaims(token).getExpiration();
	}
	
	public String getUsername(String token)
	{
		return getClaims(token).getSubject();
	}
	
	public boolean isTokenExpired(String token)
	{
		Date exp=getExpiryDate(token);
		return exp.before(new Date(System.currentTimeMillis()));
	}
	
	public boolean validateToken(String token,String username)
	{
		String tokenUsername=getUsername(token);
		
		return (tokenUsername.equals(username)) && !isTokenExpired(token);
	}

}
