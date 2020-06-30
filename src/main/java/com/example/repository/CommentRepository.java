package com.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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

	private SimpleJdbcInsert insert;

	@PostConstruct // 自動採番準備
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());

		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("comments");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

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
	public Comment insert(Comment comment) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

		Number key = insert.executeAndReturnKey(param);

		comment.setId(key.intValue());

		return comment;
	}

	/**
	 * コメント情報を削除する.
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArticleId(Integer articleId) {

		String sql = "DELETE FROM comments WHERE article_id = :articleId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);

		template.update(sql, param);
	}
}
