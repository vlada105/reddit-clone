package com.javacoding.springredditclone.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "Username Is Required")
	private String username;
	
	@NotBlank(message = "Password Is Required")
	private String password;
	
	@Email
	@NotEmpty(message = "Email Is Required")
	private String email;
	
	private Instant created;
	
	private boolean enabled;

}






