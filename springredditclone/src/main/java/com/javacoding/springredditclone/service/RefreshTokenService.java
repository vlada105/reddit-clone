package com.javacoding.springredditclone.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoding.springredditclone.exceptions.SpringRedditException;
import com.javacoding.springredditclone.model.RefreshToken;
import com.javacoding.springredditclone.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	
	private final RefreshTokenRepository refreshTokenRepository;
	

	public RefreshToken generateRefreshToken() {
		
		RefreshToken refreshToken = new RefreshToken();
		
		refreshToken.setToken(UUID.randomUUID().toString());
		
		refreshToken.setCreatedDate(Instant.now());
		
		return refreshTokenRepository.save(refreshToken);
	}
	
	
	public void validateRefreshToken(String token) {
		
		refreshTokenRepository.findByToken(token)
			.orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
			
	}
	
	public void deleteRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}
	
	
	
	
	
}
