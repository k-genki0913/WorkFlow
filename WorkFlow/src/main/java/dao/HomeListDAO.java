package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Ringisho;

public class HomeListDAO {
	//データベースへ接続するための情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/WorkFlow";
	private final String JDBC_USER = "sa";
	private final String JDBC_PASS = "";

	public List<Ringisho> requestList(Account account) {
		//検索に必要な情報
		int departmentID = account.getDepartment();
		int positionID = account.getPosition();
		String sqlPosition = ""; 
		if(positionID == 1) {
			sqlPosition = "GMANAGER";
		} else if(positionID == 2) {
			sqlPosition = "MANAGER";
		}
		
		//戻り値のList
		List<Ringisho> list = new ArrayList<>();
		
		try {
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読み込めませんでした");
		}
		
		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)){
			//SELECT文の準備
			String sql = "SELECT * FROM RINGISHO WHERE DEPARTMENTID = ? AND ? = 0 ORDER BY FORMID";
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setInt(1, positionID);
			pStmt.setString(2, sqlPosition);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int formNo = rs.getInt(1);
				String applicantName = rs.getString(2);
				int department = rs.getInt(3);
				String contents = rs.getString(4);
				int manager = rs.getInt(5);
				int gManager = rs.getInt(6);
				Ringisho ringisho = new Ringisho(formNo, applicantName, department, contents, manager, gManager);
				list.add(ringisho);
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
		
	} 
}
