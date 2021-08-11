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
 * 1) setter의 리턴형이 void이면 아래처럼 호출한다.
 * member.setNo(10);
 * member.setName("hong");
 * member.setEmail("hong@gmail.com");
 * 
 * 2) setter의 리턴형이 Member이면 아래처럼 호출한다.
 * member.setNo(10);
 * 		 .setName("hong");
 * 		 .setEmail("hong@gmail.com"); 
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
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	
}






