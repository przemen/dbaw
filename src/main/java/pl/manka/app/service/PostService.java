package pl.manka.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.manka.app.repo.PostDomain;;

@Service
public class PostService {

	@Autowired
	DataSource dataSource;

	public PostService() {
	}

	public void addPost(PostDomain pd) {
		String sql = "INSERT INTO post (id, content, author) VALUES (?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pd.getId());
			ps.setString(2, pd.getContent());
			ps.setString(3, pd.getAuthor());
			 
			ps.executeUpdate();
			ps.close();
			 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public ArrayList<PostDomain> getPosts() {
		String sql = "SELECT * FROM post";
		ArrayList<PostDomain> posts = new ArrayList<PostDomain>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PostDomain postDomain = new PostDomain();
				postDomain.setAuthor(rs.getString("author"));
				postDomain.setContent(rs.getString("content"));
				postDomain.setId(rs.getString("id"));
				posts.add(postDomain);
			}
			rs.close();
			ps.close();
			return posts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}
