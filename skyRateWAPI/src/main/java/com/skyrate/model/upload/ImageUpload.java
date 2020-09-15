package com.skyrate.model.upload;

import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {

	MultipartFile uploader;

	public MultipartFile getUploader() {
		return uploader;
	}

	public void setUploader(MultipartFile uploader) {
		this.uploader = uploader;
	}
	
	
}
