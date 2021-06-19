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
import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;

public class AdminNoticeDAO {

private final Properties prop;
	
	public AdminNoticeDAO() {
		
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "admin/admin-notice-mapper.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int NoticeActDeactive(Connection con, int noticeNum, String noticeStatus) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("NoticeActDeactive");
		
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, noticeStatus);
			pstmt.setInt(2, noticeNum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}

	public int NoticeModify(Connection con, int noticeNum, String noticeName, String noticeContent) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("NoticeModify");
		
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, noticeName);
			pstmt.setString(2, noticeContent);
			pstmt.setInt(3, noticeNum);

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<NoticeDTO> selectNoticeList(Connection con, PageInfoDTO pageInfo) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<NoticeDTO> noticeList = null;
		
		String query = prop.getProperty("selectNoticeList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartRow());
			pstmt.setInt(2, pageInfo.getEndRow());
			
			rs = pstmt.executeQuery();
			
			noticeList = new ArrayList<>();
			
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				
				notice.setNoticeNum(rs.getInt("NOTICE_NUM"));
				notice.setNoticeDate(rs.getDate("NOTICE_DATE"));
				notice.setNoticeName(rs.getString("NOTICE_NAME"));
				notice.setNoticeViewCount(rs.getInt("NOTICE_VIEW_COUNT"));
				notice.setNoticeContent(rs.getString("NOTICE_CONTENT"));
				notice.setNoticeStatus(rs.getString("NOTICE_STATUS"));
				
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return noticeList;
	}
	
	
}
