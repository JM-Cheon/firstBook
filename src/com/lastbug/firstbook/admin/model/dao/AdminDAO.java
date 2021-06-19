package com.lastbug.firstbook.admin.model.dao;

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
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.*;

public class AdminDAO {
	
	private final Properties prop;
	
	
	public AdminDAO() {
		
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "admin/admin-mapper.xml"));
		
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	
	public List<MemberDTO> selectAllMemList(Connection con) {
		
		Statement stmt = null;
		ResultSet rset = null;
		
		List<MemberDTO> memList = null;
		
		String query = prop.getProperty("selectAllMemList");
		
		//System.out.println(query);
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			memList = new ArrayList<>();
		
			while(rset.next()) {
				
				MemberDTO mem = new MemberDTO();
				
				mem.setMemNum(rset.getInt("MEM_NUM"));
				mem.setMemName(rset.getString("MEM_NAME"));
				mem.setMemId(rset.getString("MEM_ID"));
				mem.setMemPwd(rset.getString("MEM_PWD"));
				mem.setMemEmail(rset.getString("MEM_EMAIL"));
				mem.setMemAddress(rset.getString("MEM_ADDRESS"));
				mem.setMemBirthDate(rset.getDate("MEM_BIRTHDATE"));
				mem.setMemLoginCount(rset.getInt("MEM_LOGIN_COUNT"));
				mem.setMemCoin(rset.getInt("MEM_COIN"));
				mem.setMemCanVoteYn(rset.getString("MEM_CAN_VOTE_YN"));
				mem.setMemWithdrawYn(rset.getString("MEM_WITHDRAW_YN"));
				mem.setMemWithdrawDate(rset.getDate("MEM_WITHDRAW_DATE"));
				mem.setMemClass(rset.getString("MEM_CLASS"));
				mem.setMemBlockYn(rset.getString("MEM_BLOCK_YN"));
				mem.setMemBlockDate(rset.getDate("MEM_BLOCK_DATE"));
				mem.setMemEnrollDate(rset.getDate("MEM_ENROLL_DATE"));
				
				memList.add(mem);
				
			}
			
			//System.out.println(memList);
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {
			
			close(rset);
			close(stmt);	
			
		}
				
		
		return memList;
	
	}


	public List<MemberDTO> searchMemList(Connection con, String condition, String value) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = null;
						
		List<MemberDTO> searchMemList = null;
		
		if(condition.equals("memNum")) {
			
			query = prop.getProperty("searchMemberNumber");
			
		} else if (condition.equals("name")) {
			
			query = prop.getProperty("searchMemberName");
			
			
		} else if (condition.equals("email")) {
			
			query = prop.getProperty("searchMemberEmail");
			
			
		} else if (condition.equals("birthDate")) {
			
			query = prop.getProperty("searchMemberBirthDate");
			
			
		} else if (condition.equals("enrollDate")) {
			
			query = prop.getProperty("searchMemberEnrollDate");
			
		}
		
		try {
			pstmt =con.prepareStatement(query);
			
			pstmt.setString(1, value);
			
			rset = pstmt.executeQuery();
			
			searchMemList = new ArrayList<>();
			
			while(rset.next()) {
				
				MemberDTO sMem = new MemberDTO();
				
				sMem.setMemNum(rset.getInt("MEM_NUM"));
				sMem.setMemName(rset.getString("MEM_NAME"));
				sMem.setMemId(rset.getString("MEM_ID"));
				sMem.setMemEmail(rset.getString("MEM_EMAIL"));
				sMem.setMemBirthDate(rset.getDate("MEM_BIRTHDATE"));
				sMem.setMemWithdrawYn(rset.getString("MEM_WITHDRAW_YN"));
				sMem.setMemWithdrawDate(rset.getDate("MEM_WITHDRAW_DATE"));
				sMem.setMemClass(rset.getString("MEM_CLASS"));
				sMem.setMemBlockYn(rset.getString("MEM_BLOCK_YN"));
				sMem.setMemBlockDate(rset.getDate("MEM_BLOCK_DATE"));
				sMem.setMemEnrollDate(rset.getDate("MEM_ENROLL_DATE"));
				
				searchMemList.add(sMem);
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return searchMemList;
	}


	public int selectMemberBlockUnblock(Connection con, MemberDTO mem) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("selectMemberBlockUnblock");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mem.getMemBlockYn());
			pstmt.setDate(2, mem.getMemBlockDate());
			pstmt.setInt(3, mem.getMemNum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}


	public int allMemberCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAllMemCount");
		
		int allMemberCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				allMemberCount = rset.getInt("COUNT(*)");
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return allMemberCount;
	}


	public int member10sCount(Connection con) {
		
		PreparedStatement pstmt = null;
				
		ResultSet rset = null;
		
		String query = prop.getProperty("member10sCount");
		
		int member10sCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member10sCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return member10sCount;
	}


	public int member20sCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("member20sCount");
		
		int member20sCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member20sCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return member20sCount;
	}


	public int member30sCount(Connection con) {

		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("member30sCount");
		
		int member30sCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member30sCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return member30sCount;
	}


	public int member40sCount(Connection con) {

		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("member40sCount");
		
		int member40sCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member40sCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return member40sCount;
	}


	public int member50sCount(Connection con) {

		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("member50sCount");
		
		int member50sCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member50sCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return member50sCount;
	}


	public int allWnCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("allWnCount");
		
		int allWnCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				allWnCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return allWnCount;

	}


	public int fantasyCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("fantasyCount");
		
		int fantasyCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				fantasyCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return fantasyCount;
	}


	public int modernFatasyCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("modernFatasyCount");
		
		int modernFatasyCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				modernFatasyCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return modernFatasyCount;
	}


	public int sportsCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("sportsCount");
		
		int sportsCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				sportsCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return sportsCount;
	}


	public int fantasyHistoryCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("fantasyHistoryCount");
		
		int fantasyHistoryCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				fantasyHistoryCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return fantasyHistoryCount;
	}


	public int romanceCount(Connection con) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("romanceCount");
		
		int romanceCount = 0;
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				romanceCount = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return romanceCount;
	}



	
}



