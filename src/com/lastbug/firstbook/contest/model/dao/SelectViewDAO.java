package com.lastbug.firstbook.contest.model.dao;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.lastbug.firstbook.common.config.ConfigLocation;
import com.lastbug.firstbook.contest.model.dto.CompetContentDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;

public class SelectViewDAO {


	private final Properties prop;
	
	public SelectViewDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "contest/contestDetal-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 회차 조회 */
	public List<ContestDetalDTO> selectView(Connection con, int competNum, int competNum2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ContestDetalDTO> contestList = null;
		String query = prop.getProperty("selectView");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum2);
			pstmt.setInt(2, competNum);
			
			
			rset = pstmt.executeQuery();
			
			contestList = new ArrayList<>();
			
			while(rset.next()) {
				
				ContestDetalDTO contest = new ContestDetalDTO();

				contest.setChapterNum(rset.getInt("CHAPTER_NUM"));
				contest.setCompetNum(rset.getInt("COMPET_NUM"));				
				contest.setNovContent(rset.getString("NOV_CONTENT"));
				contest.setCompetDate(rset.getString("COMPET_DATE"));
				contest.setNumberOfPages(rset.getInt("NUMBER_OF_PAGES"));
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

	/* 소설 조회 */
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

	/* 회차내용 조회 */
	public List<CompetContentDTO> selectViewPage(Connection con, String numberOfPages, int first) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<CompetContentDTO> viewList = null;
		String query = prop.getProperty("selectViewPages");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(numberOfPages));
			pstmt.setInt(2, first);
			
			rset = pstmt.executeQuery();
			
			viewList = new ArrayList<>();
			
			while(rset.next()) {
				
				CompetContentDTO contest = new CompetContentDTO();

				contest.setNumberOfPages(rset.getInt("NUMBER_OF_PAGES"));
				contest.setPages(rset.getInt("PAGES"));
				contest.setCompetContent(rset.getString("COMPET_CONTENT"));
			
				viewList.add(contest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		
		return viewList;
	}

	public List<CompetContentDTO> selectViewPageTwo(Connection con, String numberOfPages, int second) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<CompetContentDTO> viewList = null;
		String query = prop.getProperty("selectViewPages");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(numberOfPages));
			pstmt.setInt(2, second);
			
			rset = pstmt.executeQuery();
			
			viewList = new ArrayList<>();
			
			while(rset.next()) {
				
				CompetContentDTO contest = new CompetContentDTO();

				contest.setNumberOfPages(rset.getInt("NUMBER_OF_PAGES"));
				contest.setPages(rset.getInt("PAGES"));
				contest.setCompetContent(rset.getString("COMPET_CONTENT"));
				
				viewList.add(contest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return viewList;
	}

	public List<CompetContentDTO> pageNubmer(Connection con, String numberOfPages) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<CompetContentDTO> pageNubmer = null;
		String query = prop.getProperty("pageNubmer");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(numberOfPages));
			
			rset = pstmt.executeQuery();
			
			pageNubmer = new ArrayList<>();
			
			while(rset.next()) {
				
				CompetContentDTO contest = new CompetContentDTO();

				contest.setNumberOfPages(rset.getInt("NUMBER_OF_PAGES"));
				contest.setPages(rset.getInt("PAGES"));
				contest.setCompetContent(rset.getString("COMPET_CONTENT"));
				
				pageNubmer.add(contest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pageNubmer;
	}


	
	
	
	



}

