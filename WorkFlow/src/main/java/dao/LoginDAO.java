package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
	
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<String> dbUserInfo(String userID) {
		List<String> dbUserInfo = new ArrayList<>();
		
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
			String sql = "SELECT USERID, PASSWORD FROM ACCOUNT WHERE USERID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, userID);
			
			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			
			//UserIDはデータベースでPRIMARY KEYにしているため、検索結果はTrue(1件)かFalseしかあり得ない
			//SELECT分の結果をArrayListに格納
			if(rs.next()) {
				String getId = rs.getString(1);
				String getPassword = rs.getString(2);
				dbUserInfo.add(getId);
				dbUserInfo.add(getPassword);
			} else {
				return null;
			}
			
			return dbUserInfo;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
