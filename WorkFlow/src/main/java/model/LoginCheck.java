package model;

import java.util.List;

import dao.LoginDAO;

public class LoginCheck {
	
	public String loginCheck(String userID, String password) {
		//データベースから入力されたUserIDと一致する情報があるか検索をかける
		LoginDAO loginDAO = new LoginDAO();
		List<String> dbUserInfo = loginDAO.dbUserInfo(userID);
		
		String errMsg = "";
		
		//dbUserInfoの情報と、入力されたPasswordが一致するか確認
		//userIDが間違っている場合、loginUserInfoでnullが返ってきているためここでは判定しない
		if(dbUserInfo != null) {
			String dbPassword = dbUserInfo.get(1);
			if(password.equals(dbPassword)) {
				return errMsg;
			} else {
				errMsg += "パスワードが間違っております";
				return errMsg;
			}
		} else {
			errMsg += "ログインIDが間違っております";
			return errMsg;
		}
		
	}
}
