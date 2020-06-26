package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事情報を取得するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class ArticleRepository {

	private static final RowMapper<Article> ARTICLE_WITH_COMMENT_ROW_MAPPER = (rs, i) -> {

		Article article = new Article();

		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		Comment comment = new Comment();

		comment.setId(rs.getInt("com_id"));
		comment.setName(rs.getString("com_name"));
		comment.setContent(rs.getString("com_content"));
		comment.setArticleId(rs.getInt("article_id"));

		article.setComment(comment);

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

		String sql = "SELECT " + "art.id AS id, " + "art.name AS name, " + "art.content AS content, "
				+ "com.id AS com_id, " + "com.name AS com_name, " + "com.content AS com_content, "
				+ "com.article_id AS article_id " + "FROM articles AS art " + "FULL OUTER JOIN " + "comments AS com "
				+ "ON art.id = com.article_id " + "ORDER BY art.id desc;";

		List<Article> articleList = template.query(sql, ARTICLE_WITH_COMMENT_ROW_MAPPER);

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

	/**
	 * 記事情報とその記事に付随するコメント情報を削除する.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {

		String sql = "BEGIN; " + "DELETE FROM comments WHERE article_id = :articleId; "
				+ "DELETE FROM articles WHERE id = :id; " + "COMMIT;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("articleId", id);

		template.update(sql, param);
	}

}
