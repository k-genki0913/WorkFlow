package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserRegist;

public class UserRegistApproveDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public boolean approve(int approveResult, UserRegist userRegist) {
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//UPDATE文の準備
			String sql = "";
			PreparedStatement pStmt = null;
			if(approveResult == 1) {
				if(userRegist.getSituation() == 1) {
					sql = "UPDATE USERREGIST SET SITUATION = 1, MAPPROVER = ? WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setString(1, userRegist.getMApprover());
					pStmt.setInt(2, userRegist.getFormID());
				} else if(userRegist.getSituation() == 0) {
					sql = "UPDATE USERREGIST SET SITUATION = 0, GMAPPROVER = ? WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setString(1, userRegist.getGMApprover());
					pStmt.setInt(2, userRegist.getFormID());
				}
			} else if(approveResult == -1) {
				if(userRegist.getSituation() == 2) {
					sql = "UPDATE USERREGIST SET SITUATION = 2, MAPPROVER = null WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setInt(1, userRegist.getFormID());
				} else if(userRegist.getSituation() == 99) {
					sql = "UPDATE USERREGIST SET SITUATION = 99 WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setInt(1, userRegist.getFormID());
				}
			}
			int result = pStmt.executeUpdate();
			
			if(result != 0) {
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
