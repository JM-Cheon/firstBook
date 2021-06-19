package com.lastbug.firstbook.admin.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.lastbug.firstbook.admin.model.dao.AdminDAO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class AdminService {

	private final AdminDAO adminDAO;
	
	public AdminService() {
		
		adminDAO = new AdminDAO();
		
	}
	
	public List<MemberDTO> selectAllMemList() {

		Connection con = getConnection();
		
		List<MemberDTO> memList = adminDAO.selectAllMemList(con);
		
		
		close(con);
		
		return memList;
	}

	public List<MemberDTO> searchMemList(String condition, String value) {
		
		Connection con = getConnection();
		
		List<MemberDTO> searchMemList = adminDAO.searchMemList(con, condition, value);
		
		close(con);
		
		
		return searchMemList;
	}

	

	public int selectMemberBlockUnblock(MemberDTO mem) {

		Connection con = getConnection();
		
		int result = adminDAO.selectMemberBlockUnblock(con, mem);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;

	}

	public int allMemberCount() {

		Connection con = getConnection();
		
		int allMemberCount = adminDAO.allMemberCount(con);
		
		
		close(con);
		
		return allMemberCount;
		

	}

	public int member10sCount() {
		
		Connection con = getConnection();
		
		int member10sCount = adminDAO.member10sCount(con);
		
		
		close(con);
		
		return member10sCount;
	}

	public int member20sCount() {
		
		Connection con = getConnection();
		
		int member20sCount = adminDAO.member20sCount(con);
		
		
		close(con);
		
		return member20sCount;
	}

	
	
	public int member30sCount() {
		
		Connection con = getConnection();
		
		int member30sCount = adminDAO.member30sCount(con);
		
		
		close(con);
		
		return member30sCount;
	}

	public int member40sCount() {
		
		Connection con = getConnection();
		
		int member40sCount = adminDAO.member40sCount(con);
		
		
		close(con);
		
		return member40sCount;
	}

	public int member50sCount() {
		
		Connection con = getConnection();
		
		int member50sCount = adminDAO.member50sCount(con);
		
		
		close(con);
		
		return member50sCount;
	}

	public int allWnCount() {
		
		Connection con = getConnection();
		
		int allWnCount = adminDAO.allWnCount(con);
		
		
		close(con);
		
		return allWnCount;
	}

	public int fantasyCount() {
		
		Connection con = getConnection();
		
		int fantasyCount = adminDAO.fantasyCount(con);
		
		
		close(con);
		
		return fantasyCount;
	}

	public int modernFatasyCount() {
		
		Connection con = getConnection();
		
		int modernFatasyCount = adminDAO.modernFatasyCount(con);
		
		
		close(con);
		
		return modernFatasyCount;
	}

	public int sportsCount() {

		Connection con = getConnection();
		
		int sportsCount = adminDAO.sportsCount(con);
		
		
		close(con);
		
		return sportsCount;
	}

	public int fantasyHistoryCount() {

		Connection con = getConnection();
		
		int fantasyHistoryCount = adminDAO.fantasyHistoryCount(con);
		
		
		close(con);
		
		return fantasyHistoryCount;
	}

	public int romanceCount() {

		Connection con = getConnection();
		
		int romanceCount = adminDAO.romanceCount(con);
		
		
		close(con);
		
		return romanceCount;
	}


	
}





















