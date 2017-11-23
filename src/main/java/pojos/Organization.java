package pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {

	@SerializedName("repositories")
	@Expose
	private Repositories repositories;

	public Repositories getRepositories() {
		return repositories;
	}

	public void setRepositories(Repositories repositories) {
		this.repositories = repositories;
	}

}