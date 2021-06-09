package com.javacoding.springredditclone.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.javacoding.springredditclone.dto.SubredditDto;
import com.javacoding.springredditclone.model.Post;
import com.javacoding.springredditclone.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
	
	
	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubRedditToDto(Subreddit subreddit);
	
	default Integer mapPosts(List<Post> numberOfPosts) {
		return numberOfPosts.size();
	}
	
	@InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
	
}
