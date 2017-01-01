package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;

public class JdbcTemplate {

	/*public <T> T select(String sql, RowMapper<T> rm, PreparedStatementSetter pss) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pss.setValue(pstmt);

			rs = pstmt.executeQuery();
			
			

			return rm.mapRow(rs);
			
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}*/
	public <T> T select(String sql, RowMapper<T> rm, Object... values) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			//pss.setValue(pstmt);
			for(int i = 0; i<values.length; i++){
				pstmt.setObject(i+1, values[i]);
			}

			rs = pstmt.executeQuery();
			return rm.mapRow(rs);
			
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
/*	public void update(String sql, PreparedStatementSetter pss) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pss.setValue(pstmt);
			
		}
		finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		
		}
		
	}*/
	public void update(String sql, Object...values) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			for(int i = 0; i < values.length; i++){
				pstmt.setObject(i+1, values[i]);
			}
			pstmt.executeUpdate();
			
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

	
}
