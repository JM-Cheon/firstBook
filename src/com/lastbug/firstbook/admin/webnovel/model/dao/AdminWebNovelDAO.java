package com.lastbug.firstbook.admin.webnovel.model.dao;

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
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class AdminWebNovelDAO {
	
	private final Properties prop;
	
	public AdminWebNovelDAO() {
		
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "admin/adminWebnovel/admin-webnovel-mapper.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<WebNovelInfoDTO> selectAllNovel(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;

		List<WebNovelInfoDTO> adminWebNovelList = null;

		String query = prop.getProperty("adminSelectAllNovel");
//		System.out.println("쿼리" + query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			adminWebNovelList = new ArrayList<>();

			while(rset.next()) {
				WebNovelInfoDTO webNovel = new WebNovelInfoDTO();

				webNovel.setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovel.setWebNovTitle(rset.getString("WEB_NOV_TITLE"));
				webNovel.setWebNovAuthor(rset.getString("WEB_NOV_AUTHOR"));
				webNovel.setWebNovInform(rset.getString("WEB_NOV_INFORM"));
				webNovel.setCategoryCode(rset.getString("CATEGORY_CODE"));
				webNovel.setWebNovPublisher(rset.getString("WEB_NOV_PUBLISHER"));
				webNovel.setDayOfWeek(rset.getString("DAY_OF_WEEK"));
				webNovel.setWebNovImgLocation(rset.getString("WEB_NOV_IMG_LOCATION"));
				webNovel.setFinishedOrNot(rset.getString("FINISHED_OR_NOT"));
				webNovel.setWebNovOpenOrClose(rset.getString("WEB_NOV_IS_OPEN"));
				
				adminWebNovelList.add(webNovel);
				
			}
//			System.out.println(adminWebNovelList);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return adminWebNovelList;
	}

	public int insertNewWebnovel(Connection con, WebNovelInfoDTO thumbnail) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertNewWebnovel");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, thumbnail.getCategoryCode());
			pstmt.setString(2, thumbnail.getWebNovTitle());
			pstmt.setString(3, thumbnail.getWebNovAuthor());
			pstmt.setString(4, thumbnail.getWebNovPublisher());
			pstmt.setString(5, thumbnail.getWebNovInform());
			pstmt.setString(6, thumbnail.getWebNovImgLocation());
			pstmt.setString(7, thumbnail.getDayOfWeek());
			pstmt.setString(8, thumbnail.getWebNovOpenOrClose());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {


			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public WebNovelInfoDTO adminSelectWebNovelDetail(Connection con, int no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		WebNovelInfoDTO webNovelUpdate = null;
		
		String query = prop.getProperty("adminSelectWebNovelDetail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				webNovelUpdate = new WebNovelInfoDTO();
				
				webNovelUpdate.setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovelUpdate.setWebNovTitle(rset.getString("WEB_NOV_TITLE"));
				webNovelUpdate.setWebNovAuthor(rset.getString("WEB_NOV_AUTHOR"));
				webNovelUpdate.setWebNovInform(rset.getString("WEB_NOV_INFORM"));
				webNovelUpdate.setCategoryCode(rset.getString("CATEGORY_CODE"));
				webNovelUpdate.setWebNovPublisher(rset.getString("WEB_NOV_PUBLISHER"));
				webNovelUpdate.setDayOfWeek(rset.getString("DAY_OF_WEEK"));
				webNovelUpdate.setWebNovImgLocation(rset.getString("WEB_NOV_IMG_LOCATION"));
				webNovelUpdate.setFinishedOrNot(rset.getString("FINISHED_OR_NOT"));
				webNovelUpdate.setWebNovOpenOrClose(rset.getString("WEB_NOV_IS_OPEN"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return webNovelUpdate;
	}

	public int WebNovelCloseOrOpen(Connection con, String closeOrOpen, WebNovelInfoDTO adminWebNovelList) {
		
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("WebNovelCloseOrOpen");
		
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, closeOrOpen);
			pstmt.setInt(2, adminWebNovelList.getWebNovNum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int WebNovelModify(Connection con, int no, int currentChap, int pg0, int pg1, String body0, String body1) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		System.out.println("dao body0 :" + body0);
		
		String query = prop.getProperty("WebNovelModify");
				
		
		try {							
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pg0);
			pstmt.setString(2, body0);
			pstmt.setInt(3, pg1);
			pstmt.setString(4, body1);
			pstmt.setInt(5, no);
			pstmt.setInt(6, currentChap);
			pstmt.setInt(7, pg0);
			pstmt.setInt(8, pg1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		System.out.println("dao query after body0 :" + body0);
		
		return result;

	}



}













