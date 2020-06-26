package com.example.form;

import javax.validation.constraints.*;

/**
 * 記事投稿情報を受け取るフォーム.
 * 
 * @author yumi takahashi
 *
 */

public class ArticleForm {

	/**
	 * 投稿者名
	 */
	@NotBlank(message = "投稿者名を入力してください")
	@Size(min = 0, max = 50, message = "投稿者名は50字以内で入力してください")
	private String name;

	/**
	 * 投稿内容
	 */
	@NotBlank(message = "投稿内容を入力してください")
	@Size(min = 0, max = 140, message = "投稿内容は140字以内で入力してください")
	private String content;

	// getter setter
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
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

}
