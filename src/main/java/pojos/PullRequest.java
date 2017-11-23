package pojos;

import com.google.gson.annotations.Expose;

public class PullRequest {

	@Expose
	private User author;
	@Expose
	private String title;
	@Expose
	private Integer number;
	@Expose
	private String createdAt;
	@Expose
	private Object mergedAt;

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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Object getMergedAt() {
		return mergedAt;
	}

	public void setMergedAt(Object mergedAt) {
		this.mergedAt = mergedAt;
	}
}