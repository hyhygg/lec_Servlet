package spms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public List<Member> selectList() throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = 
				"SELECT mno,mname,email,cre_date" + "\r\n" +
				"FROM members" + "\r\n" +
				"ORDER BY mno ASC";
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			ArrayList<Member> members = 
					new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("mno"))
							.setName(rs.getString("mname"))
							.setEmail(rs.getString("email"))
							.setCreatedDate(rs.getDate("cre_date")));
			}
			return members;
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(rs!=null) 
					rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null) 
					stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	// 회원등록
	public int insert(Member member) throws Exception{
		
	}
	
	// 회원삭제
	public int delete(int no) throws Exception{
		
		
	}
	
	// 회원 상세 정보 조회
	public Member selecteOne(int no) throws Exception{
		
	}
	
	// 회원 정보 변경
	public int update(Member member) throws Exception{
		
	}
	
	// 있으면 Member 객체 리턴, 없으면 null 리턴
	public Member exist(String email, String password) throws Exception{
		
	}
}






