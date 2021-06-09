package com.javacoding.springredditclone.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoding.springredditclone.dto.CommentsDto;
import com.javacoding.springredditclone.exceptions.PostNotFoundException;
import com.javacoding.springredditclone.mapper.CommentMapper;
import com.javacoding.springredditclone.model.Comment;
import com.javacoding.springredditclone.model.NotificationEmail;
import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.User;
import com.javacoding.springredditclone.repository.CommentRepository;
import com.javacoding.springredditclone.repository.PostRepository;
import com.javacoding.springredditclone.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional //  added
public class CommentService {
	
	private final PostRepository postRepository;
	
	private final UserRepository userRepository;
	
	private final AuthService authService;
	
	private final CommentMapper commentMapper;
	
	private final CommentRepository commentRepository;
	
	private final MailContentBuilder mailContentBuilder;
	
	private final MailService mailService;
	
	private static final String POST_URL ="";
	
	
	
	
	public void save(CommentsDto commentsDto) {
		
		Post post = postRepository.findById(commentsDto.getPostId())
			.orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
		
		
		Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
		
		commentRepository.save(comment);
		
		String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
		
		sendCommentNotification(message, post.getUser());
		
	}

	private void sendCommentNotification(String message, User user) {
		
		mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
		
	}

	public List<CommentsDto> getAllCommentsForPost(Long postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
		
		return commentRepository.findByPost(post)
				.stream()
				.map(commentMapper::mapToDto)
				.collect(Collectors.toList());
		
		
	}

	public List<CommentsDto> getAllCommentsForUser(String userName) {
		
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException(userName));
		
		return commentRepository.findAllByUser(user)
				.stream()
				.map(commentMapper::mapToDto)
				.collect(Collectors.toList());

	
	}

}
