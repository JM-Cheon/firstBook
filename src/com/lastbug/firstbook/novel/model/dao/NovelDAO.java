package com.lastbug.firstbook.novel.model.dao;

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
import com.lastbug.firstbook.novel.model.dto.NovelDTO;
import com.lastbug.firstbook.webnovel.model.dto.GenreCategoryDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class NovelDAO {

	private final Properties prop;

	public NovelDAO() {
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "novel/novel-mapper.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<NovelDTO> selectAllNovel(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;

		List<NovelDTO> NovelList = null;
				
		String query = prop.getProperty("selectAllNovel");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			NovelList = new ArrayList<>();

			while(rset.next()) {
				NovelDTO Novel = new NovelDTO();
				Novel.setCategoryName(new GenreCategoryDTO());

				Novel.setNovNum(rset.getInt("NOV_NUM"));
				Novel.setCategoryCode(rset.getString("CATEGORY_CODE"));
				Novel.setNovTitle(rset.getString("NOV_TITLE"));
				Novel.setNovAuthor(rset.getString("NOV_AUTHOR"));
				Novel.setPrice(rset.getInt("NOV_PRICE"));
				Novel.setPublisher(rset.getString("NOV_PUBLISHER"));
				Novel.setInform(rset.getString("NOV_INFORM"));
				Novel.setImgLocation(rset.getString("NOV_IMG_LOCATION"));
				Novel.setOrderableYn(rset.getString("ORDERABLE_YN"));
				Novel.setNovAmount(rset.getInt("NOV_AMOUNT"));
				Novel.getCategoryName().setCategoryName(rset.getString("CATEGORY_NAME"));
				

				NovelList.add(Novel);
			}

			System.out.println(NovelList);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return NovelList;
	}

	public NovelDTO selectNovelDetail(Connection con, int no) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		NovelDTO NovDetail = null;
		
		String query = prop.getProperty("selectNovelDetail");
		
		System.out.println("쿼리: " + query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
						
			if(rset.next()) {
				NovDetail = new NovelDTO();
				NovDetail.setCategoryName(new GenreCategoryDTO());

				NovDetail.setNovNum(rset.getInt("NOV_NUM"));
				NovDetail.setCategoryCode(rset.getString("CATEGORY_CODE"));
				NovDetail.setNovTitle(rset.getString("NOV_TITLE"));
				NovDetail.setNovAuthor(rset.getString("NOV_AUTHOR"));
				NovDetail.setPrice(rset.getInt("NOV_PRICE"));
				NovDetail.setPublisher(rset.getString("NOV_PUBLISHER"));
				NovDetail.setInform(rset.getString("NOV_INFORM"));
				NovDetail.setImgLocation(rset.getString("NOV_IMG_LOCATION"));
				NovDetail.setOrderableYn(rset.getString("ORDERABLE_YN"));
				NovDetail.setNovAmount(rset.getInt("NOV_AMOUNT"));
				NovDetail.getCategoryName().setCategoryName(rset.getString("CATEGORY_NAME"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return NovDetail;
	}
	
	
}
