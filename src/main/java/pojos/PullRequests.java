package pojos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullRequests {
	@SerializedName("edges")
	@Expose
	private List<PullRequestContainer> containers = null;
	@Expose
	private PageInfo pageInfo;

	public List<PullRequestContainer> getEdges() {
		return containers;
	}

	public void setEdges(List<PullRequestContainer> edges) {
		this.containers = edges;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
