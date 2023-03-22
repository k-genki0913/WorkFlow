package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HomeDocument;

public class ApprovedListDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public List<HomeDocument> getApprovedList(String userID){
		List<HomeDocument> list = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			//テーブルが増えたらUNIONで追加していく
			String sql = "SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM RINGISHO WHERE APPLICANTNAME = ? AND SITUATION = ? UNION ALL SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM USERREGIST WHERE APPLICANTNAME = ? AND SITUATION = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			//表示する書類は、applicantNameが自分のIDのもの
			//Situationは0が承認済みのため、0のものとする
			pStmt.setString(1, userID);
			pStmt.setInt(2, 0);
			pStmt.setString(3, userID);
			pStmt.setInt(4, 0);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int formID = rs.getInt(1);
				String documentName = rs.getString(2);
				String documentTable = rs.getString(3);
				String applicantName = rs.getString(4);
				HomeDocument homeDocument = new HomeDocument(formID, documentName, documentTable, applicantName);
				list.add(homeDocument);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
