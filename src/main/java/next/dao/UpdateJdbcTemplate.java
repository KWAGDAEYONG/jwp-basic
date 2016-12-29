package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class UpdateJdbcTemplate{
	public void update(User user) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(createQueryForUpdate());
			setValueForUpdate(pstmt, user);

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public abstract void setValueForUpdate(PreparedStatement pstmt, User user) throws SQLException;

	public abstract String createQueryForUpdate();
	
}
