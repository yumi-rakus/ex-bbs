package com.example.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> findAll() {

		List<Article> articleList = articleRepository.findAll();

		Map<Integer, List<Comment>> commentMap = new LinkedHashMap<>();
		Map<Integer, Article> articleMap = new LinkedHashMap<>();

		// commentMapにArrayListを格納
		for (Article article : articleList) {
			List<Comment> commentList = new ArrayList<>();
			commentMap.put(article.getComment().getArticleId(), commentList);
		}

		// commentMapにキー名articleIdでコメントリストにコメントを格納
		for (Article article : articleList) {

			List<Comment> commentList = commentMap.get(article.getComment().getArticleId());

			commentList.add(article.getComment());

		}

		// articleMapにArticleオブジェクトを格納
		for (Article article : articleList) {

			Article art = new Article();

			art.setId(article.getId());
			art.setName(article.getName());
			art.setContent(article.getContent());

			List<Comment> commentList = commentMap.get(article.getId());

			art.setCommentList(commentList);

			articleMap.put(article.getId(), art);
		}

		List<Article> distivctArticleList = new ArrayList<>(articleMap.values());

		return distivctArticleList;
	}

}
