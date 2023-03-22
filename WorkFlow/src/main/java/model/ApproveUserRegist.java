package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApproveUserRegist {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public UserRegist getUserRegist(int formID) {
		//ドライバの取得
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e){
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT * FROM USERREGIST WHERE FORMID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			//formIDをSQL文へセット！
			pStmt.setInt(1, formID);
			
			//SQL文の実行、結果を取得
			ResultSet rs = pStmt.executeQuery();
			
			//formIDは各テーブルで重複がないため、if文で判定
			if(rs.next()) {
				String document = rs.getString(2);
				String documentTable = rs.getString(3);
				String applicantName = rs.getString(4);
				int departmentID = rs.getInt(5);
				int situation = rs.getInt(6);
				String mApprover = rs.getString(7);
				String gmApprover = rs.getString(8);
				String registName = rs.getString(9);
				int registDepartment = rs.getInt(10);
				int registPosition = rs.getInt(11);
				UserRegist userRegist = new UserRegist(formID, document, documentTable, applicantName, departmentID, situation, mApprover, gmApprover, registName, registDepartment, registPosition);
				return userRegist;
			} else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
