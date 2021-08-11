package spms.vo;

import java.util.Date;

/* VO(Value Object) => 값을 묶어서 저장하는 객체
 * DTO(Data Transfer Object) => 값을 각 역할을 맡은 객체에 전달하는 객체
 * 
 * 테이블/뷰와 1:1 관계
 * or
 * 페이지에 보여줘야 될 정보 1:1 관계
 * */

/*
 * 1) setter의 리턴형이 void이면 아래처럼 호출한다
 * 	member.setNo(10);
 *  member.setName("hong");
 *  member.setEmail("hong@naver.com");
 *  
 * 2) setter의 리턴형을 Member로 하면 아래처럼 chain식으로 호출한다
 *  member.setNo(10)
 *  	  .setName("hong")
 *  	  .setEmail("hong@naver.com");
 * */

public class Member {
	protected int no;
	protected String name;
	protected String email;
	protected String password;
	protected Date createdDate;
	protected Date modifiedDate;
	
	
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









