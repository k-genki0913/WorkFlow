package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RingishoNewDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public boolean registRingisho(String applicantName, int departmentID, String contents) {
		//ドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースへの接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//INSERT文の作成
			String sql = "INSERT INTO RINGISHO(APPLICANTNAME, DEPARTMENTID, CONTENTS, SITUATION) VALUES(?, ?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, applicantName);
			pStmt.setInt(2, departmentID);
			pStmt.setString(3, contents);
			pStmt.setInt(4, 2);
			
			//INSERT文の実行
			int r = pStmt.executeUpdate();
			
			if(r > 0) {
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
