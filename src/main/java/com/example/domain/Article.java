package com.example.domain;

import java.util.List;

/**
 * 記事情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class Article {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 投稿者名
	 */
	private String name;

	/**
	 * 投稿内容
	 */
	private String content;

	/**
	 * コメントリスト
	 */
	private List<Comment> commentList;

	private Comment comment;

	// constructor
	public Article() {
	}

	public Article(Integer id, String name, String content, List<Comment> commentList) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.commentList = commentList;
	}

	// getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	// toString commentiretene
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList
				+ ", comment=" + comment + "]";
	}

}
