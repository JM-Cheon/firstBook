package com.lastbug.firstbook.contest.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.lastbug.firstbook.contest.model.dao.ContestDetalDAO;
import com.lastbug.firstbook.contest.model.dao.SelectViewDAO;
import com.lastbug.firstbook.contest.model.dao.VotingYNDAO;
import com.lastbug.firstbook.contest.model.dto.CompetContentDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;

public class SelectViewService {
	
	private final SelectViewDAO selectViewDAO;
	
	/* 회차 내용 조회 */
	public SelectViewService() {
		selectViewDAO = new SelectViewDAO();
	}

	public List<ContestDetalDTO> selectView(int competNum, int competNum2) {
		Connection con = getConnection();

		List<ContestDetalDTO> selectView = selectViewDAO.selectView(con, competNum, competNum2);
		
		close(con);
		
		return selectView;
	}
	
	
	/* 웹 소설 내용 조회 */
	public List<ContestDTO> selectContestList(int competNum) {
		
		Connection con = getConnection();
		
		List<ContestDTO> selectContestList = selectViewDAO.selectContestList(con, competNum);
		
		close(con);
		
		return selectContestList;
	}

	public List<CompetContentDTO> selectViewPage(String numberOfPages, int first) {
		
		Connection con = getConnection();
		
		List<CompetContentDTO> selectViewPage = selectViewDAO.selectViewPage(con, numberOfPages, first);
		
		close(con);
		
		return selectViewPage;
	}

	public List<CompetContentDTO> selectViewPageTwo(String numberOfPages, int second) {
		
		Connection con = getConnection();
		
		List<CompetContentDTO> selectViewPage = selectViewDAO.selectViewPageTwo(con, numberOfPages, second);
		
		close(con);
		
		return selectViewPage;
	}

	public List<CompetContentDTO> pageNubmer(String numberOfPages) {
        Connection con = getConnection();
		
		List<CompetContentDTO> pageNubmer = selectViewDAO.pageNubmer(con, numberOfPages);
		
		close(con);
		return pageNubmer;
	}


	
	
	
	
	

	
	
	
}
