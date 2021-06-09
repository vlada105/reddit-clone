package com.javacoding.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoding.springredditclone.dto.SubredditDto;
import com.javacoding.springredditclone.exceptions.SpringRedditException;
import com.javacoding.springredditclone.mapper.SubredditMapper;
import com.javacoding.springredditclone.model.Subreddit;
import com.javacoding.springredditclone.repository.SubredditRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditService {
	
	private final SubredditRepository subredditRepository;
	
	private final SubredditMapper subredditMapper;
	
	
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		
		Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		
		subredditDto.setId(save.getId());
		
		return subredditDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		
		return	subredditRepository.findAll()
				.stream()
				.map(subredditMapper::mapSubRedditToDto)
				.collect(Collectors.toList());
		
	}
	
	
	public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubRedditToDto(subreddit);
    }
	
	
	
	
	
	/* private SubredditDto mapToDto (Subreddit subreddit) {
		return SubredditDto.builder().name(subreddit.getName())
				.id(subreddit.getId())
				.numberOfPosts(subreddit.getPosts().size())
				.build();
				
	}
	
	*/
	
	
	/*

	private Subreddit mapSubredditDto(SubredditDto subredditDto) {

		return Subreddit.builder().name(subredditDto.getName())
					.description(subredditDto.getDescription())
					.build();		
	}
	
	*/
	

}
