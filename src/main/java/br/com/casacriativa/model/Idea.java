package br.com.casacriativa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Idea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String linkImage;
	private String description;
	private String linkIdea;
	
	@ManyToOne
	private Category category;
	
	public Idea(){
	}
	
	public Idea(String title, Category category, String description, String linkImage, String linkIdea) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.linkImage = linkImage;
		this.linkIdea = linkIdea;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Idea(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	public String getLinkIdea() {
		return linkIdea;
	}

	public void setLinkIdea(String linkIdea) {
		this.linkIdea = linkIdea;
	}

	@Override
	public String toString() {
		return "Idea [id=" + id + ", title=" + title + ", category=" + category.getName() + ", linkImage=" + linkImage
				+ ", description=" + description + ", linkIdea=" + linkIdea + "]";
	}

}
