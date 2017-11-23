package pojos;

import com.google.gson.annotations.Expose;

public class ResponseData {

	@Expose
	private Organization organization;
	@Expose
	private RepositoryNode repository;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public RepositoryNode getRepository() {
		return repository;
	}

	public void setRepository(RepositoryNode repository) {
		this.repository = repository;
	}
}
