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
}






