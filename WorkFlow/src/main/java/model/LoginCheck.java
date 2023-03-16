package model;

public class LoginCheck {
	
	public String loginCheck(Account account, String password) {
		String errMsg = "";
		
		//dbUserInfoの情報と、入力されたPasswordが一致するか確認
		//userIDが間違っている場合、loginUserInfoでnullが返ってきているためここでは判定しない
		if(account != null) {
			String dbPassword = account.getPassword();
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
