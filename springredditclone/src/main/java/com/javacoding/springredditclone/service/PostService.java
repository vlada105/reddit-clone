package com.javacoding.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoding.springredditclone.dto.PostRequest;
import com.javacoding.springredditclone.dto.PostResponse;
import com.javacoding.springredditclone.exceptions.PostNotFoundException;
import com.javacoding.springredditclone.exceptions.SubredditNotFoundException;
import com.javacoding.springredditclone.mapper.PostMapper;
import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.Subreddit;
import com.javacoding.springredditclone.model.User;
import com.javacoding.springredditclone.repository.PostRepository;
import com.javacoding.springredditclone.repository.SubredditRepository;
import com.javacoding.springredditclone.repository.UserRepository;

import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;


//import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
//@Slf4j
@Transactional
public class PostService {	
	
	
	private final SubredditRepository subredditRepository;
	private final AuthService authService;
	private final PostMapper postMapper;
	
	private final PostRepository postRepository;
	
	private final UserRepository userRepository;
	
	
	
	public void save(PostRequest postRequest) {
		
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
		.orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
		
		User currentUser = authService.getCurrentUser();
		
		postMapper.map(postRequest, subreddit, currentUser);
		
		postRepository.save(postMapper.map(postRequest, subreddit, currentUser));	
		
	}
	
	
	@Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }
	
	
	
	@Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
	
	
	 @Transactional(readOnly = true)
	    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
	        Subreddit subreddit = subredditRepository.findById(subredditId)
	                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
	        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
	        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
	    }
	
	 
	 @Transactional(readOnly = true)
	    public List<PostResponse> getPostsByUsername(String username) {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException(username));
	        return postRepository.findByUser(user)
	                .stream()
	                .map(postMapper::mapToDto)
	                .collect(Collectors.toList());
	    }
	

}










