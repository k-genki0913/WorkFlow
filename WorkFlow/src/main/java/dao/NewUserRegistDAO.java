package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewUserRegistDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public boolean regist(String applicantName, int applicantDepartment, String registName, int registDepartment, int registPosition) {
		//ドライバの取得
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//INSERT文の準備
			String sql = "INSERT INTO USERREGIST" +
						"(APPLICANTNAME, DEPARTMENTID, REGISTNAME, REGISTDEPARTMENT, REGISTPOSITION)" +
						"VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, applicantName);
			pStmt.setInt(2, applicantDepartment);
			pStmt.setString(3, registName);
			pStmt.setInt(4, registDepartment);
			pStmt.setInt(5, registPosition);
			
			int result = pStmt.executeUpdate();
			
			if(result > 0) {
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
