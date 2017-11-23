package pojos;

import com.google.gson.annotations.Expose;

public class RequestQuery {
	
	public RequestQuery(String query) {
		this.query = query;
	}

	@Expose
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
