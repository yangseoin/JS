package kr.talenton.webprj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.talenton.webprj.vo.Member;

public class MemberDao {
	
	public List<Member> getMembers() throws SQLException{
		
		return getMembers(1);
	}
	
	
	public List<Member> getMembers(int page) throws SQLException{
		
		int start = 1+(page-1)*10  ;//1, 11, 21, 31, 41...an = a1+(n-1)d 
		int end = page*10;
		
	
		String sql = "SELECT * FROM("
		+ "SELECT ROW_NUMBER() OVER(ORDER BY REGDATE DESC) NUM, MEMBERS.*FROM MEMBERS) A "
        + "WHERE NUM BETWEEN "+start+" AND "+end;
		
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb;";
		//String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=talenton;";
		//jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks;integratedSecurity=true;
		
		Connection con = DriverManager.getConnection(url,"edu","class2d");
		//Connection con = DriverManager.getConnection(url,"talent","talenton3");
		
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		
		while (rs.next()) {
		
		  member = new Member(); 
		  
		  member.setMid(rs.getString("mid"));
		  member.setName(rs.getString("name"));
		  //member.setAddress(rs.getString("address")); 
		  //member.setPwd(rs.getString("pwd"));
		  
		  list.add(member);
		}
		rs.close();
		st.close();
		con.close();
		
		return list;
		
	}

}
