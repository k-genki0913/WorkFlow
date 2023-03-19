package model;

public class NewRingishoCheck {
	public boolean check(String contents) {
		if(contents != null && contents.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
