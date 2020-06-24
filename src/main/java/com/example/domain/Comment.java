package com.example.domain;

/**
 * コメント情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class Comment {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 名前
	 */
	private String name;

	/**
	 * コメント内容
	 */
	private String content;

	/**
	 * 記事ID
	 */
	private Integer articleId;

	// constructor
	public Comment() {
	}

	// getter setter
	public Comment(Integer id, String name, String content, Integer articleId) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.articleId = articleId;
	}

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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	// toString
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
