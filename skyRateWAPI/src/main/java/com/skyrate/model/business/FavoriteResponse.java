package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Bookmark;

public class FavoriteResponse extends BaseResponse{

	List<BookmarkDetails> bookmark;

	public List<BookmarkDetails> getBookmark() {
		return bookmark;
	}

	public void setBookmark(List<BookmarkDetails> bookmark) {
		this.bookmark = bookmark;
	}
	
	
}
