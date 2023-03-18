package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionConvert {
	private final String JDBC_URL="jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public String positionName(int positionId) {
	//ドライバの取得
	try {
		Class.forName("org.h2.Driver");
	} catch(ClassNotFoundException e) {
		throw new IllegalStateException("ドライバを読み込めませんでした");
	}
	
	//データベースへ接続
	try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
		//SELECT文を準備
		String sql = "SELECT POSITION FROM POSITION WHERE POSITIONID = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setInt(1, positionId);
		
		//SQLを実行し、結果を取得
		ResultSet rs = pStmt.executeQuery();
		
		if(rs.next()) {
			String positionName = rs.getString(1);
			return positionName;
		}
		return null;
	} catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
	
	}
	
}
