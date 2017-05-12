package pl.kempa.mygarden.model;

public class ResponseStatus {
	boolean success;

	public ResponseStatus(boolean b) {
		this.success=true;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
