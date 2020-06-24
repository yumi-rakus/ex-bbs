package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事情報を操作するコントローラ.
 * 
 * @author yumi takahashi
 *
 */
@Controller
@Transactional
@RequestMapping("")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：記事一覧を表示する, コメントを表示する
	/////////////////////////////////////////////////////
	/**
	 * 掲示板画面を表示する.
	 * 
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();

		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}

		model.addAttribute("articleList", articleList);

		return "bbs";
	}

	/////////////////////////////////////////////////////
	// ユースケース：記事を投稿する
	/////////////////////////////////////////////////////
	/**
	 * 記事を投稿する.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("/post-article")
	public String insertArticle(ArticleForm form, Model model) {

		Article article = new Article();

		article.setName(form.getName());
		article.setContent(form.getContent());

		articleRepository.insert(article);

		form.setName("");
		form.setContent("");

		return index(model);
	}

	/////////////////////////////////////////////////////
	// ユースケース：コメントを投稿する
	/////////////////////////////////////////////////////
	/**
	 * コメントを投稿する.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("/post-comment")
	public String insertComment(CommentForm form, Model model) {

		Comment comment = new Comment();

		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(Integer.parseInt(form.getArticleId()));

		commentRepository.insert(comment);

		form.setName("");
		form.setContent("");

		return index(model);
	}

}
