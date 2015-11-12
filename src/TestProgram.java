import java.sql.SQLException;
import java.util.List;

import kr.talenton.webprj.dao.MemberDao;
import kr.talenton.webprj.vo.Member;

public class TestProgram {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MemberDao dao = new MemberDao();
		List<Member> list = dao.getMembers(); //페이지 1인부분 출력
		//List<Member> list = dao.getMembers(2); // 페이지 2인 부분 출력 
		
		for(Member m : list)
		{
			System.out.printf("mid : %s, name : %s\n", m.getMid(), m.getName());
		}

	}
	

}


