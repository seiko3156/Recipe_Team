package com.ezenb1.recipe.dto;

import java.sql.Timestamp;

public class MembersVO {

	private String id; // 아이디
	private String pwd; // 비밀번호
	private String name; // 이름
	private String phone; // 전화번호
	private String email; // 이메일
	private String nick; // 닉네임
	private String address1; // 주소1
	private String address2; // 주소2
	private String zip_num; // 집우편주소?
	private Timestamp indate; // 가입날짜?
	private String img; // 프로필사진?
	private String useyn; // 휴면회원 여부
	
	
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getZip_num() {
		return zip_num;
	}
	public void setZip_num(String zip_num) {
		this.zip_num = zip_num;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
