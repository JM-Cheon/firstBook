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
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;

public class VotingYNDAO {


	private final Properties prop;
	
	public VotingYNDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "contest/contestDetal-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int VotingN(Connection con, String name) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("votingN");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int count(Connection con, int competNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("count");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, competNum);
			pstmt.setInt(2, competNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	
	
	
	



}

