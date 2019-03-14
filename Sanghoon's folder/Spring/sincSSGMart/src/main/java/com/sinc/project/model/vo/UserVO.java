package com.sinc.project.model.vo;

public class UserVO {
	private String user_Id;
	private String user_Pwd;
	
	public UserVO() {}

	public UserVO(String user_Id, String user_Pwd) {
		super();
		this.user_Id = user_Id;
		this.user_Pwd = user_Pwd;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Pwd() {
		return user_Pwd;
	}

	public void setUser_Pwd(String user_Pwd) {
		this.user_Pwd = user_Pwd;
	}

	@Override
	public String toString() {
		return "UserVO [user_Id=" + user_Id + ", user_Pwd=" + user_Pwd + "]";
	}
	
	
	
	
}
