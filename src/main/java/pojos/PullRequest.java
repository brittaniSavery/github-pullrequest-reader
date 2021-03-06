package pojos;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class PullRequest {

	@Expose
	private User author;
	@Expose
	private String title;
	@Expose
	private Integer number;
	@Expose
	private Date createdAt;
	@Expose
	private Date mergedAt;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getMergedAt() {
		return mergedAt;
	}

	public void setMergedAt(Date mergedAt) {
		this.mergedAt = mergedAt;
	}
}