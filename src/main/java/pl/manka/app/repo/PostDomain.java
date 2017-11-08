package pl.manka.app.repo;

import org.springframework.stereotype.Repository;

@Repository
public class PostDomain {
	String content;
	String author;

	public PostDomain(String content, String author, String id) {
		super();
		this.content = content;
		this.author = author;
		this.id = id;
	}

	public PostDomain() {
	}

	String id;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return content+" "+author;
	}

}
