package com.isaachome.payload;

/**
 *
 * @author IsaacHome
 */


public class FileResponse {

	
	private static final long serialVersionUID = 1L;
	private String message;
	private String filename;
	
	public FileResponse(String message, String filename) {
		super();
		this.message = message;
		this.filename = filename;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
