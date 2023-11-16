package com.live.demo.tutorial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comments, Long> {
	
	List<Comments> findTutorialById(Long postId);
	
	@Transactional
	void deleteByTutorialId(long tutorialId);
	
	

}
