package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRingisho {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public Ringisho getRingisho(int formID) {
		//ドライバを取得する
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT * FROM RINGISHO WHERE FORMID = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setInt(1, formID);
			
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				String document = rs.getString(2);
				String applicantName = rs.getString(4);
				int departmentID = rs.getInt(5);
				int situation = rs.getInt(6);
				String mApprover = rs.getString(7);
				String gmApprover = rs.getString(8);
				String contents = rs.getString(9);
				Ringisho ringisho = new Ringisho(formID, document, applicantName, departmentID, situation, mApprover, gmApprover, contents);
				return ringisho;
			}
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
