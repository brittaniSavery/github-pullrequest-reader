package pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageInfo {
	
	@Expose
	private String endCursor;
	@SerializedName("hasNextPage")
	@Expose
	private Boolean hasNextPage;

	public String getEndCursor() {
		return endCursor;
	}

	public void setEndCursor(String endCursor) {
		this.endCursor = endCursor;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
