package com.javacoding.springredditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.User;
import com.javacoding.springredditclone.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
