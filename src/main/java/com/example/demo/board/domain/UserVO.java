package com.example.demo.board.domain;

import java.util.Date;

public class UserVO {

	private int uno;
	private String uid;
	private String upassword;
	private String uname;
	private String email;
	private int auth; // 0 : 일반회원     1 : 정회원        99 : 어드민 
	private int money;
	private Date update_log;
	private String thumbnailname;
	private String jsession;
	
	
	public String getJsession() {
		return jsession;
	}
	public void setJsession(String jsession) {
		this.jsession = jsession;
	}
	
	public String getThumbnailname() {
		return thumbnailname;
	}
	public void setThumbnailname(String thumbnailname) {
		this.thumbnailname = thumbnailname;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getUpdate_log() {
		return update_log;
	}
	public void setUpdate_log(Date update_log) {
		this.update_log = update_log;
	}
}
