package com.live.demo.tutorial;

import jakarta.persistence.*;

@Entity
@Table(name="tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="tutorial_generator")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="published")
	private boolean published;
	
	
	public Tutorial() {
		
	}
	
	/*many comments mapped to one tutorial  many to one relationship */
	
	 public Tutorial(String title, String description, boolean published) {
		    this.title = title;
		    this.description = description;
		    this.published = published;
	 }
	
	
}
