package com.lastbug.firstbook.contest.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.lastbug.firstbook.contest.model.dao.ContestDetalDAO;
import com.lastbug.firstbook.contest.model.dao.VotingYNDAO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;

public class VotingYNService {
	
	private final VotingYNDAO votingYNDAO;
	
	public VotingYNService() {
		votingYNDAO = new VotingYNDAO();
	}

	
	public int VotingN(int competNum, String name) {
		Connection con = getConnection();
	
		int result = votingYNDAO.VotingN(con, name);
		
		close(con);
		
		return 0;
	}

	public String count(int competNum) {
		
		Connection con = getConnection();
		
		int result = votingYNDAO.count(con, competNum );
		
		close(con);
		String tt = "확인";
		return tt;
	}




	
	
}
