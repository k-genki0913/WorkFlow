package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HomeDocument;

public class RemandListDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";
	
	public List<HomeDocument> getRemandList(String userID){
		List<HomeDocument> list = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバの取得に失敗しました");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM RINGISHO WHERE APPLICANTNAME = ? AND SITUATION = 99 UNION ALL SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM USERREGIST WHERE APPLICANTNAME = ? AND SITUATION = 99";
			PreparedStatement pStmt = con.prepareStatement(sql);
	
			pStmt.setString(1, userID);
			pStmt.setString(2, userID);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int formID = rs.getInt(1);
				String document = rs.getString(2);
				String documentTable = rs.getString(3);
				String applicantName = rs.getString(4);
				HomeDocument homeDocument = new HomeDocument(formID, document, documentTable, applicantName);
				list.add(homeDocument);
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
