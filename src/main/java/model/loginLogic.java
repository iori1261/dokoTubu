package model;

public class loginLogic {
	public boolean execute(User user) {
		if(user.getPass().equals("1234")) { return true; }
		return false;
	}
}
