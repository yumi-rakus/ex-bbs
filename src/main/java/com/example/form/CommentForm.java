package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * コメント投稿情報を受け取るフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class CommentForm {

	/**
	 * 記事ID
	 */
	private String articleId;

	/**
	 * 名前
	 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50字以内で入力してください")
	private String name;

	/**
	 * コメント内容
	 */
	@NotBlank(message = "コメント内容を入力してください")
	@Size(max = 140, message = "コメント内容は140字以内で入力してください")
	private String content;

	// getter setter
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	// toString
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
