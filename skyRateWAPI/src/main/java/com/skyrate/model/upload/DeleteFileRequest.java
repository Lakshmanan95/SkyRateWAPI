package com.skyrate.model.upload;

import com.skyrate.clib.model.BaseRequest;

public class DeleteFileRequest extends BaseRequest{
	String filePath;
	long id;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
