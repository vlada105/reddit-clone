package com.javacoding.springredditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.Subreddit;
import com.javacoding.springredditclone.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAllBySubreddit(Subreddit subreddit);
	
	
	List<Post> findByUser(User user);
	

}
