package pojos;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Repositories {

	@Expose
	private List<RepositoryNode> nodes = null;

	public List<RepositoryNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<RepositoryNode> nodes) {
		this.nodes = nodes;
	}

}
