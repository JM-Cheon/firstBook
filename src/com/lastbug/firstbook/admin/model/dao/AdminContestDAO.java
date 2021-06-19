package com.lastbug.firstbook.admin.model.dao;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;

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

public class AdminContestDAO {
	
	private final Properties prop;
	
	public AdminContestDAO() {
		
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "admin/admin-contest-mapper.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int ContestModify(Connection con, int nop, int pg0, String body0, int pg1, String body1) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("ContestModify");
		
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pg0);
			pstmt.setString(2, body0);
			pstmt.setInt(3, pg1);
			pstmt.setString(4, body1);
			pstmt.setInt(5, nop);
			pstmt.setInt(6, pg0);
			pstmt.setInt(7, pg1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
		
		

	}

	public int ContestActiveOrDeactive(Connection con, int competNum, String activeYn) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("ContestActiveOrDeactive");
		
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, activeYn);
			pstmt.setInt(2, competNum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

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

	public List<ContestDTO> selectAllContestList(Connection con, String month) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<ContestDTO> contestList = null;
		String query = prop.getProperty("selectAllContestList");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, month);
			
			
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
	
	

	
	
}
