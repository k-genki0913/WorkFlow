package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//formIDを元にDocumentTableの名前を取得するためのメソッド
public class DocumentCheck {
	//データベース接続へ必要な情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public String check(int formID) {
		//ドライバの取得
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		//データベースへ接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT DOCUMENTTABLE FROM RINGISHO WHERE FORMID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setInt(1, formID);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String documentTable = rs.getString(1);
				return documentTable;
			} 
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
