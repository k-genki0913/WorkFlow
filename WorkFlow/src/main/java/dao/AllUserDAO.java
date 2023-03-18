package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;

public class AllUserDAO {
	//データベースへの接続情報
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
		private final String JDBC_USER = "sa";
		private final String JDBC_PASS = "";
		
		//ユーザー一覧を取得するメソッド
		public List<Account> getAllUser(){
			//ドライバの取得
			try {
				Class.forName("org.h2.Driver");
			} catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			
			//データベースへ接続
			try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
				//格納するArrayList
				List<Account> allUserList = new ArrayList<>();
				
				//SELECT文を準備
				String sql = "SELECT * FROM ACCOUNT ORDER BY USERID";
				PreparedStatement pStmt = con.prepareStatement(sql);
				
				//SELECT文を実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				//結果表に格納されたレコード内容を
				//Accountインスタンスに設定し、ArrayListに追加
				while(rs.next()) {
					String userId = rs.getString(1);
					String password = rs.getString(2);
					String name = rs.getString(3);
					int departmentId = rs.getInt(4);
					int positionId = rs.getInt(5);
					Account account = new Account(userId, password, name, departmentId, positionId);
					allUserList.add(account);
				}
				return allUserList;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
}
