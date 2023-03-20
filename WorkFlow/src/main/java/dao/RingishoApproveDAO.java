package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Ringisho;

public class RingishoApproveDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public boolean approveRingisho(int approveResult, Ringisho ringisho) {
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//INSERT文の準備
			String sql = "";
			PreparedStatement pStmt = null;
			if(approveResult == 1) {
				if(ringisho.getSituation() == 1) {
					sql = "UPDATE RINGISHO SET SITUATION = 1, MAPPROVER = ? WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setString(1, ringisho.getMApprover());
					pStmt.setInt(2, ringisho.getFormID());
				} else if (ringisho.getSituation() == 0) {
					sql = "UPDATE RINGISHO SET SITUATION = 0, GMAPPROVER = ? WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setString(1, ringisho.getGMApprover());
					pStmt.setInt(2, ringisho.getFormID());
				}
			} else if(approveResult == -1) {
				if(ringisho.getSituation() == 2) {
					sql = "UPDATE RINGISHO SET SITUATION = 2, MAPPROVER = null WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setInt(1, ringisho.getFormID());
				} else if(ringisho.getSituation() == 3) {
					sql = "UPDATE RINGISHO SET SITUATION = 3 WHERE FORMID = ?";
					pStmt = con.prepareStatement(sql);
					pStmt.setInt(1, ringisho.getFormID());
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

