package pojos;

import com.google.gson.annotations.Expose;

public class RepositoryNode {

	@Expose
	private PullRequests pullRequests;
	@Expose
	private String name;
	@Expose
	private User owner;

	public PullRequests getPullRequests() {
		return pullRequests;
	}

	public void setPullRequests(PullRequests pullRequestContainer) {
		this.pullRequests = pullRequestContainer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}