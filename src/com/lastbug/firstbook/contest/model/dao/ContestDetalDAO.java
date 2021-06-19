package com.lastbug.firstbook.contest.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.lastbug.firstbook.common.config.ConfigLocation;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;

public class ContestDetalDAO {
	
	private final Properties prop;
	
	public ContestDetalDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "contest/contestDetal-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* 공모전 웹소설 상세페이지 가져오기 */
	public List<ContestDetalDTO> selectContestDetal(Connection con, int competNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<ContestDetalDTO> contestList = null;
		String query = prop.getProperty("selectContestDetal");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum);
			
			
			rset = pstmt.executeQuery();
			
			contestList = new ArrayList<>();
			
			while(rset.next()) {
				
				ContestDetalDTO contest = new ContestDetalDTO();

				contest.setChapterNum(rset.getInt("CHAPTER_NUM"));
				contest.setCompetNum(rset.getInt("COMPET_NUM"));
				contest.setNovContent(rset.getString("NOV_CONTENT"));
				contest.setCompetDate(rset.getString("COMPET_DATE"));

				contestList.add(contest);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return contestList;
	}
	
	/* 공모전 정보 가져오기 */
	public List<ContestDTO> selectContestList(Connection con, int competNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<ContestDTO> contestList = null;
		String query = prop.getProperty("selectContestList");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum);
			
			
			rset = pstmt.executeQuery();
			
			contestList = new ArrayList<>();
			
			while(rset.next()) {
				
				ContestDTO contest = new ContestDTO();

				contest.setCompetNum(rset.getInt("COMPET_NUM"));
				contest.setMemNum(rset.getInt("MEM_NUM"));
				contest.setCompetPaperYn(rset.getString("COMPET_PAPER_YN"));
				contest.setNovTitle(rset.getString("NOV_TITLE"));
				contest.setNovInfo(rset.getString("NOV_INFO"));
				contest.setCompetSsn(rset.getString("COMPET_SSN"));
				contest.setCompetNovImgLocation(rset.getString("COMPET_NOV_IMG_LOCATION"));
				contest.setScore(rset.getInt("SCORE"));
				contest.setCompetActYn(rset.getString("COMPET_ACT_YN"));
				contest.setAgeLimit(Integer.valueOf(rset.getString("AGE_LIMIT")));
				
				contestList.add(contest);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return contestList;
	}
	
	/* 작가정보 검색 */
	public List<MemberDTO> selectMemberList(Connection con, int competNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MemberDTO> memberList = null;
		String query = prop.getProperty("selectMemberList");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum);
			
			
			rset = pstmt.executeQuery();
			
			memberList = new ArrayList<>();
			
			while(rset.next()) {
				
				MemberDTO member = new MemberDTO();

				member.setMemNum(rset.getInt("MEM_NUM"));
				member.setMemName(rset.getString("MEM_NAME"));
				member.setMemId(rset.getString("MEM_ID"));
				member.setMemPwd(rset.getString("MEM_PWD"));
				member.setMemEmail(rset.getString("MEM_EMAIL"));
				member.setMemAddress(rset.getString("MEM_ADDRESS"));
				member.setMemBirthDate(rset.getDate("MEM_BIRTHDATE"));
				member.setMemLoginCount(rset.getInt("MEM_LOGIN_COUNT"));
				member.setMemCoin(rset.getInt("MEM_COIN"));
				member.setMemCanVoteYn(rset.getString("MEM_CAN_VOTE_YN"));
				member.setMemWithdrawYn(rset.getString("MEM_WITHDRAW_YN"));
				member.setMemWithdrawDate(rset.getDate("MEM_WITHDRAW_DATE"));
				member.setMemClass(rset.getString("MEM_CLASS"));
				member.setMemBlockYn(rset.getString("MEM_BLOCK_YN"));
				member.setMemBlockDate(rset.getDate("MEM_BLOCK_DATE"));
				member.setMemEnrollDate(rset.getDate("MEM_ENROLL_DATE"));
				

				memberList.add(member);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return memberList;
	}
	
	/* 카테고리 검색 */
	public String selectCategory(Connection con, int competNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String category = null;
		String query = prop.getProperty("selectCategory");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum);
			
			
			rset = pstmt.executeQuery();
			
			category = null;
			while(rset.next()) {
				
				
				category = rset.getString("CATEGORY_NAME");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return category;
	}
	
}
