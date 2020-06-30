package com.example.form;

import java.util.List;

import javax.validation.Valid;

public class ParentForm {

	@Valid
	private List<CommentForm> articleInfoList;

	// getter setter
	public List<CommentForm> getArticleInfoList() {
		return articleInfoList;
	}

	public void setArticleInfoList(List<CommentForm> articleInfoList) {
		this.articleInfoList = articleInfoList;
	}

}
