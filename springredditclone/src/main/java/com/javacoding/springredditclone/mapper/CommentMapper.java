package com.javacoding.springredditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.javacoding.springredditclone.dto.CommentsDto;
import com.javacoding.springredditclone.model.Comment;
import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
	
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "text", source = "commentsDto.text")
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(target = "post", source = "post")
	@Mapping(target = "user", source = "user")
	public Comment map(CommentsDto commentsDto, Post post, User user);
	
	
	@Mapping(target = "postId", expression ="java(comment.getPost().getPostId())")
	@Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
	public CommentsDto mapToDto(Comment comment);	
	
}
