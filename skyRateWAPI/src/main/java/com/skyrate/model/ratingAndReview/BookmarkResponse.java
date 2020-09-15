package com.skyrate.model.ratingAndReview;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Bookmark;

public class BookmarkResponse extends BaseResponse{

	Bookmark bookmark;
	String deleted;

	public Bookmark getBookmark() {
		return bookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
}
