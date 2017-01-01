package next.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		
		// 익명클래스
		JdbcTemplate template = new JdbcTemplate();
		String InsertSql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		template.update(InsertSql, user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}

	public void update(User user) throws SQLException {

		String updateSql = "UPDATE USERS SET name = ?, email=? where userId=?";
		// 익명클래스
		JdbcTemplate template = new JdbcTemplate();
		template.update(updateSql, user.getName(),user.getEmail(),user.getUserId());
	}

	public List<User> findAll() throws SQLException {
		
		RowMapper<List<User>> rm = new RowMapper<List<User>>() {

			@Override
			public List<User> mapRow(ResultSet rs) throws SQLException {
				List<User> users = new ArrayList<>();
				while (rs.next()) {
					users.add(new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
							rs.getString("email")));
				}
				return users;
			}

		};
		String findAllSql = "SELECT userId, password, name, email FROM USERS";
		JdbcTemplate template = new JdbcTemplate();
		return template.select(findAllSql, rm);
	}

	public User findByUserId(String userId) throws SQLException {
		
		RowMapper<User> rm = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}

				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

		};

		JdbcTemplate template = new JdbcTemplate();
		String findOneSql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		return template.select(findOneSql, rm, userId);
	}

}
