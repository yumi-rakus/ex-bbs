package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
@Transactional
@RequestMapping("")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

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
