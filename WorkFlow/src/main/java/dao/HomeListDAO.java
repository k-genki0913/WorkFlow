package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HomeDocument;

public class HomeListDAO {
	private String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private String JDBC_USER = "sa";
	private String JDBC_PASS = "";
	
	public List<HomeDocument> documentList(int departmentID, int positionID){
		List<HomeDocument> list = new ArrayList<>();
		
		//ドライバの取得
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読み込めませんでした");
		}
		
		//データベースへの接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			//データベースが増えると記述を変更する必要がある
			String sql = "SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM RINGISHO WHERE DEPARTMENTID = ? AND SITUATION = ? UNION ALL SELECT FORMID, DOCUMENT, DOCUMENTTABLE, APPLICANTNAME FROM USERREGIST WHERE DEPARTMENTID = ? AND SITUATION = ?";
			
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, departmentID);
			pStmt.setInt(2, positionID);
			pStmt.setInt(3, departmentID);
			pStmt.setInt(4, positionID);
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			//HomeDocumentインスタンスを作成し、リストへ追加
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
