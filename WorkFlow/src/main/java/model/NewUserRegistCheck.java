package model;

public class NewUserRegistCheck {
	public boolean check(String registName) {
		if(registName.length() == 0 || registName == null) {
			return false;
		} else {
			return true;
		}
		
	}
}
