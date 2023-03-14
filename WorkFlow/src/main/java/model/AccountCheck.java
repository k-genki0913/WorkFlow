package model;

public class AccountCheck {
	public boolean[] execute(Account account) {
		String id = account.getId();
		String password = account.getPassword();
		String name = account.getName();
		
		boolean checkId = checkId(id);
		boolean checkPassword = checkPassword(password);
		boolean checkName = checkName(name);
		
		boolean[] accountCheck = {checkId, checkPassword, checkName};
		return accountCheck;
	}
	
	//userIdのチェックメソッド
	//a〜z、-のみ許可。文字数制限なし
	public boolean checkId(String id) {
		boolean check;
		if(id != null && id.length() != 0) {
			check = id.matches("[a-z\\-]*");
		} else {
			check = false;
		}
		return check;
	}
	
	//passwordチェックメソッド
	//a〜z、数字のみ許可。文字数は6文字以上
	public boolean checkPassword(String password) {
		boolean check;
		if(password != null && password.length() != 0) {
			check = password.matches("[a-zA-Z0-9]{6,}");
		} else {
			check = false;
		}
		return check;
	}
	
	//!!!!!!nameチェックメソッド!!!!!!!
	//なにか入力されていればOK(3/13時点)、理想はひらがな、カタカナ、漢字のみ
	public boolean checkName(String name) {
		boolean check;
		if(name != null && name.length() != 0) {
			check = true;
		} else {
			check = false;
		}
		
		return check;
	}
}
