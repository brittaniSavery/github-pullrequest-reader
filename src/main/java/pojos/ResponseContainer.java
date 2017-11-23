package pojos;

import java.util.List;

import com.google.gson.annotations.Expose;

public class ResponseContainer {
	
	@Expose
	private ResponseData data;
	@Expose
	private List<ResponseError> errors;

	public ResponseData getData() {
		return data;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}

	public List<ResponseError> getErrors() {
		return errors;
	}

	public void setErrors(List<ResponseError> errors) {
		this.errors = errors;
	}
}
