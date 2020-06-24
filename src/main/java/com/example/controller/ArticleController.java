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

	/////////////////////////////////////////////////////
	// ユースケース：記事一覧を表示する
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
	@RequestMapping("/post")
	public String post(ArticleForm form, Model model) {

		Article article = new Article();

		article.setName(form.getName());
		article.setContent(form.getContent());

		articleRepository.insert(article);

		form.setName("");
		form.setContent("");

		return index(model);
	}

}
