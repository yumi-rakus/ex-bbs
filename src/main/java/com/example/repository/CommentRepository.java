package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント情報を取得するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class CommentRepository {

	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {

		Comment comment = new Comment();

		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));

		return comment;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事IDからコメント情報一覧を取得する.
	 * 
	 * @param articleId 記事ID
	 * @return 指定した記事IDのコメント情報一覧
	 */
	public List<Comment> findByArticleId(Integer articleId) {

		String sql = "SELECT * FROM comments WHERE article_id = :articleId ORDER BY id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);

		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);

		return commentList;
	}

	/**
	 * コメント情報を挿入する.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {

		String sql = "INSERT INTO comments (name, content, article_id) VALUES (:name, :content, :articleId)";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName())
				.addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());

		template.update(sql, param);

	}
}
