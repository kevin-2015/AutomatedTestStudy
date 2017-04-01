package cn.slimsmart.fitnesse.test.demo;

public class LoginDialogDriver {
	// 四个成员变量
	private String userName;
	private String password;
	private String message;
	private int loginAttempts;

	// 构造函数
	public LoginDialogDriver(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	// 使用用户名和密码登录方法
	public boolean loginWithUsernameAndPassword(String userName, String password) {
		loginAttempts++;
		boolean result = this.userName.equals(userName)
				&& this.password.equals(password);
		if (result)
			message = String.format("%s logged in.", this.userName);
		else
			message = String.format("%s not logged in.", this.userName);
		return result;
	}

	// 返回登录信息
	public String loginMessage() {
		return message;
	}

	// 返回登录次数
	public int numberOfLoginAttempts() {
		return loginAttempts;
	}
}
