package com.example.form;

import java.util.List;

import javax.validation.Valid;

public class ParentForm {

	@Valid
	private List<CommentForm> comments;

	// getter setter
	public List<CommentForm> getcomments() {
		return comments;
	}

	public void setReceiveFormList(List<CommentForm> comments) {
		this.comments = comments;
	}

}
