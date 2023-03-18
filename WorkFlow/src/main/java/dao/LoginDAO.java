package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class LoginDAO {
	
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Account getAccount(String userID) {
		
		//h2へのドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースへ接続する
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//引数のUSERIDと一致するデータを検索するSELECT文を作成
			//アカウントとパスワードの一致を確認するためだけなので、IDとPASSの情報のみ取得
			String sql = "SELECT * FROM ACCOUNT WHERE USERID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, userID);
			
			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			
			//UserIDはデータベースでPRIMARY KEYにしているため、検索結果はTrue(1件)かFalseしかあり得ない
			//SELECT分の結果をAccountインスタンスに格納
			if(rs.next()) {
				String getId = rs.getString(1);
				String getPassword = rs.getString(2);
				String getName = rs.getString(3);
				int getDepartmentID = rs.getInt(4);
				int getPositionID = rs.getInt(5);
				Account account = new Account(getId, getPassword, getName, getDepartmentID, getPositionID);
				return account;
			} else {
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
