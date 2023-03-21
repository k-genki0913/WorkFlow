package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIDConvert {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public String getName(String userID) {
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT NAME FROM ACCOUNT WHERE USERID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, userID);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String userName = rs.getString(1);
				return userName;
			}
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
