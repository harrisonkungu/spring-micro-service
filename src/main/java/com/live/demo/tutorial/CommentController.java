package com.live.demo.tutorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.live.demo.error.ResourceNotFoundException;



@RestController
@RequestMapping("/api/comments")
public class CommentController {
	 @Autowired
	  private TutorialRepository tutorialRepository;

	  @Autowired
	  private CommentRepository commentRepository;

	  @GetMapping("/tutorials/{tutorialId}/comments")
	  public ResponseEntity<List<Comments>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
	    if (!tutorialRepository.existsById(tutorialId)) {
	      throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
	    }

	    List<Comments> comments = commentRepository.findTutorialById(tutorialId);
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	  }

	  @GetMapping("/comments/{id}")
	  public ResponseEntity<Comments> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
		  Comments comment = commentRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

	    return new ResponseEntity<>(comment, HttpStatus.OK);
	  }

	  @PostMapping("/tutorials/{tutorialId}/comments")
	  public ResponseEntity<Comments> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
	      @RequestBody Comments commentRequest) {
	    Comments comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
	      commentRequest.setTutorial(tutorial);
	      return commentRepository.save(commentRequest);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

	    return new ResponseEntity<>(comment, HttpStatus.CREATED);
	  }

	  @PutMapping("/comments/{id}")
	  public ResponseEntity<Comments> updateComment(@PathVariable("id") long id, @RequestBody Comments commentRequest) {
	    Comments comment = commentRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

	    comment.setContent(commentRequest.getContent());

	    return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
	  }

	  @DeleteMapping("/comments/{id}")
	  public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
	    commentRepository.deleteById(id);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  @DeleteMapping("/tutorials/{tutorialId}/comments")
	  public ResponseEntity<List<Comments>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
	    if (!tutorialRepository.existsById(tutorialId)) {
	      throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
	    }

	    commentRepository.deleteByTutorialId(tutorialId);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
}
