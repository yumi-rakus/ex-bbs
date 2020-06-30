package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.domain.Article;

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
	private Integer articleId;

	/**
	 * commentFormのindex番号
	 */
	private Integer commentFormListindex;

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

	/**
	 * 記事情報
	 */
	private Article article;

	// getter setter
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getCommentFormListindex() {
		return commentFormListindex;
	}

	public void setCommentFormListindex(Integer commentFormListindex) {
		this.commentFormListindex = commentFormListindex;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	// toString
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", commentFormListindex=" + commentFormListindex + ", name="
				+ name + ", content=" + content + ", article=" + article + "]";
	}

}
