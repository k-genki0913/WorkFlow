package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RingishoReDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public boolean reapplicate(String contents, int formID) {
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//UPDATE文の準備
			String sql = "UPDATE RINGISHO SET CONTENTS = ?, SITUATION = 2 WHERE FORMID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, contents);
			pStmt.setInt(2, formID);
			
			int result = pStmt.executeUpdate();
			if(result != 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
