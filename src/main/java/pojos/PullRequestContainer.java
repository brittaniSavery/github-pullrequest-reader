package pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullRequestContainer {

	@SerializedName("node")
	@Expose
	private PullRequest pullRequest;

	public PullRequest getNode() {
		return pullRequest;
	}

	public void setNode(PullRequest node) {
		this.pullRequest = node;
	}

}
