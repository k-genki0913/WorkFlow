package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DAOをうまく使えていないことに気づく・・・
//今後の課題
public class DepartmentConvert {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public String departmentName(int departmentId) {
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースに接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文を準備
			String sql = "SELECT DEPARTMENTNAME FROM DEPARTMENT WHERE DEPARTMENTID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			//検索条件にdepartmentIdを指定
			pStmt.setInt(1, departmentId);
			
			//結果を取得する。
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String departmentName = rs.getString(1);
				return departmentName;
			}
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
