package spms.vo;

import java.util.Date;

/* 데이터베이스의 테이블에 상응하는 클래스로 만들어진 객체를
 * VO(Value Object)라 한다.
 * MVC아키텍처에서 이 VO로 값을 주고 받을 때는 이것을
 * DTO(Data Transfer Object)라고도 부른다.
 * 
 * VO는 값을 나타내는 필드와
 * Getters/Setters로 이루어진다.
 * 
 * 
 * */

public class Member {
	private int no;
	private String name;
	private String email;
	private String password;
	private Date createdDate;
	private Date modifiedDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}






