package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public abstract class InsertJdbcTemplate {
	public void insert(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(createQueryForInsert());
			setValuesForInsert(pstmt, user);
			
		}
		finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		
		}
		
	}
	public abstract String createQueryForInsert();
	
	public abstract void setValuesForInsert(PreparedStatement stmt, User user)throws SQLException;
	
}
