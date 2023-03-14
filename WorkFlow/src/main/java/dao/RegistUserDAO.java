package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Account;

public class RegistUserDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//ユーザー登録メソッド
	public boolean registUser(Account account) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読み込めませんでした");
		}
		
		//データベースに接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//INSERT文を準備(UserID, Password, Name, DepartmentID, PositionID の順番)
			String sql = "INSERT INTO ACCOUNT VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			//INSERT文中の「?」に使用する値を設定し、SQL文を完成させる
			pStmt.setString(1, account.getId());
			pStmt.setString(2, account.getPassword());
			pStmt.setString(3, account.getName());
			pStmt.setInt(4, account.getDepartment());
			pStmt.setInt(5, account.getPosition());
			
			//INSERT文を実行し、結果を確認
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
