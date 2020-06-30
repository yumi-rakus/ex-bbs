package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.form.ParentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import com.example.service.ArticleService;

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

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ServletContext application;

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

		List<Article> articleList = articleService.findAll();

		if (articleList.isEmpty()) {
			model.addAttribute("articleNotExist", "記事が1件も存在しません");
		} else {

			List<CommentForm> articleInfoList = new ArrayList<>();

			for (Article article : articleList) {
				CommentForm commentForm = new CommentForm();

				commentForm.setArticle(article);

				articleInfoList.add(commentForm);
			}

			ParentForm parentForm = (ParentForm)application.getAttribute("parentForm");
			
			if(Objects.isNull(parentForm)) {
				parentForm = new ParentForm();
			}
			
			parentForm.setArticleInfoList(articleInfoList);
			
			application.setAttribute("parentForm", parentForm);
		}

		

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
	public String insertArticle(@Validated ArticleForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "bbs";
		}

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
	public String insertComment(@Validated CommentForm cform, BindingResult result, 
			Integer index, Model model) {

		if (result.hasErrors()) {
			return "bbs";
		}
		
		

		Comment comment = new Comment();

		comment.setName(cform.getName());
		comment.setContent(cform.getContent());
		comment.setArticleId(cform.getArticleId());

		commentRepository.insert(comment);

		cform.setName("");
		cform.setContent("");

		return index(model);
	}

	/////////////////////////////////////////////////////
	// ユースケース：記事とコメントを削除する
	/////////////////////////////////////////////////////
	/**
	 * 記事とコメントを削除する.
	 * 
	 * @param id    (記事)ID
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("/delete")
	public String deleteArticle(String id, Model model) {

		articleRepository.deleteById(Integer.parseInt(id));

		return index(model);
	}

}
