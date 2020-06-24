package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事情報を取得するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class ArticleRepository {

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {

		Article article = new Article();

		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事情報を全件取得する.
	 * 
	 * @return 記事情報一覧
	 */
	public List<Article> findAll() {

		String sql = "SELECT * FROM articles ORDER BY id DESC";

		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 記事情報を挿入する.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {

		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",
				article.getContent());

		template.update(sql, param);

	}

}
