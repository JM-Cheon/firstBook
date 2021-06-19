package com.lastbug.firstbook.webnovel.model.dao;

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
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.UseCoinDTO;
import com.lastbug.firstbook.webnovel.model.dto.GenreCategoryDTO;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapNumDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapSearchDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovContentDetailDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovPageNumDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebnovelReplyDTO;



public class WebNovelDAO {

	private final Properties prop;

	public WebNovelDAO() {
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "webnovel/webnovel-mapper.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 웹소설 전체 조회용 메소드 */
	public List<WebNovelInfoDTO> selectAllNovel(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;

		List<WebNovelInfoDTO> webNovelList = null;
		String query = prop.getProperty("selectAllNovel");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			webNovelList = new ArrayList<>();

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
				

				webNovelList.add(webNovel);
			}

			System.out.println(webNovelList);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return webNovelList;
	}

	public WebNovelInfoDTO selectWebNovelDetail(Connection con, int no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		WebNovelInfoDTO webNovelDetail = null;

		String query = prop.getProperty("selectWebNovelDetail");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				webNovelDetail = new WebNovelInfoDTO();
				webNovelDetail.setCategoryName(new GenreCategoryDTO());

				webNovelDetail.setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovelDetail.setWebNovTitle(rset.getString("WEB_NOV_TITLE"));
				webNovelDetail.setWebNovAuthor(rset.getString("WEB_NOV_AUTHOR"));
				webNovelDetail.setDayOfWeek(rset.getString("DAY_OF_WEEK"));
				webNovelDetail.setWebNovInform(rset.getString("WEB_NOV_INFORM"));
				webNovelDetail.setWebNovImgLocation(rset.getString("WEB_NOV_IMG_LOCATION"));
				webNovelDetail.getCategoryName().setCategoryName(rset.getString("CATEGORY_NAME"));
				//			webNovelDetail.setCategoryName(rset.getString("CATEGORY_NAME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return webNovelDetail;
	}

	public List<WebNovChapSearchDTO> selectWebNovelallChapter(Connection con, WebNovelInfoDTO webDetail) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<WebNovChapSearchDTO> webNovelChap = null;

		String query = prop.getProperty("selectWebNovelallChapter");
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, webDetail.getWebNovNum());


			rset = pstmt.executeQuery();

			webNovelChap = new ArrayList<WebNovChapSearchDTO>();

			while(rset.next()) {
				WebNovChapSearchDTO wbdDTO = new WebNovChapSearchDTO();
				wbdDTO.setWebNovNum(new WebNovelInfoDTO());
				wbdDTO.setWebNovChapNum(new WebNovChapNumDTO());

				wbdDTO.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				wbdDTO.getWebNovChapNum().setWebNovChapNum(rset.getInt("CHAP_NUM"));
				wbdDTO.setWebChapNumDate(rset.getDate("CHAP_WRITTEN_DATE"));
				wbdDTO.setChapReadOrNot(rset.getString("CHAP_READABLE"));
				wbdDTO.setChapPerPrice(rset.getInt("CHAP_PER_PRICE"));
				wbdDTO.setChapPerIsUsed(rset.getString("CHAP_PER_IS_USED"));
				wbdDTO.setChapPerTitle(rset.getString("CHAP_PER_TITLE"));


				webNovelChap.add(wbdDTO);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return webNovelChap;


	}

	public List<WebNovContentDetailDTO> selectPerChap(Connection con, PageInfoDTO pageInfo, int currentWebNovNum, int currentChapNum) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;	

		List<WebNovContentDetailDTO> perChap = null;

		String query = prop.getProperty("selectPerChap");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, currentWebNovNum);
			pstmt.setInt(2, currentChapNum);
			pstmt.setInt(3, pageInfo.getStartRow());
			pstmt.setInt(4, pageInfo.getEndRow());

			rset = pstmt.executeQuery();

			perChap = new ArrayList<WebNovContentDetailDTO>();

			while(rset.next()) {

				WebNovContentDetailDTO webnovel = new WebNovContentDetailDTO();

				webnovel.setWebNovNum(new WebNovelInfoDTO());
				webnovel.setWebNovChapNum(new WebNovChapNumDTO());
				webnovel.setWebNovPageNum(new WebNovPageNumDTO());
				
				webnovel.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webnovel.getWebNovChapNum().setWebNovChapNum(rset.getInt("CHAP_NUM"));
				webnovel.getWebNovPageNum().setWebNovPageNum(rset.getInt("WEB_NOV_PAGE_NUM"));
				webnovel.setWebNovPageContent(rset.getString("WEB_NOV_PAGE_CONTENT"));
				
				perChap.add(webnovel);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return perChap;

	}

	/* 웹소설 내용이 총 몇개인지 세는 메소드 */
	public int searchWebNovelCount(Connection con, int currentWebNovNum, int currentChapNum) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("searchWebNovelCount");
		
		int webNovCount = 0;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, currentWebNovNum);
			pstmt.setInt(2, currentChapNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				webNovCount = rset.getInt("COUNT(*)");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return webNovCount;
	}

	/* 선택한 웹소설 웹뷰어 페이지에 제목을 조회하는 메소드 */
	public WebNovelInfoDTO searchTitle(Connection con, int currentWebNovNum) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		WebNovelInfoDTO webNovelDetail = null;

		String query = prop.getProperty("searchTitle");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, currentWebNovNum);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				webNovelDetail = new WebNovelInfoDTO();
				webNovelDetail.setCategoryName(new GenreCategoryDTO());

				webNovelDetail.setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovelDetail.setWebNovTitle(rset.getString("WEB_NOV_TITLE"));
				webNovelDetail.setWebNovAuthor(rset.getString("WEB_NOV_AUTHOR"));
				webNovelDetail.setDayOfWeek(rset.getString("DAY_OF_WEEK"));
				webNovelDetail.setWebNovInform(rset.getString("WEB_NOV_INFORM"));
				webNovelDetail.getCategoryName().setCategoryName(rset.getString("CATEGORY_NAME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return webNovelDetail;
	}

	public int incrementWebnovelCount(Connection con, int no) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("incrementWebnovelCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int selectTotalCount(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectTotalCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				totalCount = rset.getInt("COUNT(*)");
				
				System.out.println("db" + totalCount);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return totalCount;
	}

	public List<WebNovChapSearchDTO> selectWebNovelallChapterNotFree(Connection con, WebNovelInfoDTO webDetail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<WebNovChapSearchDTO> webNovelChap2 = null;

		String query = prop.getProperty("selectWebNovelallChapterNotFree");
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, webDetail.getWebNovNum());

			rset = pstmt.executeQuery();

			webNovelChap2 = new ArrayList<WebNovChapSearchDTO>();

			while(rset.next()) {
				WebNovChapSearchDTO wbdDTO = new WebNovChapSearchDTO();
				wbdDTO.setWebNovNum(new WebNovelInfoDTO());
				wbdDTO.setWebNovChapNum(new WebNovChapNumDTO());

				wbdDTO.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				wbdDTO.getWebNovChapNum().setWebNovChapNum(rset.getInt("CHAP_NUM"));
				wbdDTO.setWebChapNumDate(rset.getDate("CHAP_WRITTEN_DATE"));
				wbdDTO.setChapReadOrNot(rset.getString("CHAP_READABLE"));
				wbdDTO.setChapPerPrice(rset.getInt("CHAP_PER_PRICE"));
				wbdDTO.setChapPerIsUsed(rset.getString("CHAP_PER_IS_USED"));
				wbdDTO.setChapPerTitle(rset.getString("CHAP_PER_TITLE"));

				webNovelChap2.add(wbdDTO);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return webNovelChap2;

	}

	public int selectTotalChapter(Connection con, int currentWebNov) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectTotalChapter");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, currentWebNov);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalCount = rset.getInt("COUNT(*)");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalCount;

	}

	public WebNovChapSearchDTO selectPerChapCoin(Connection con, int webNovNum, int webNovChapNum) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		WebNovChapSearchDTO PerChapCoin = null;
		
		String query = prop.getProperty("selectPerChapCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, webNovChapNum);
	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PerChapCoin = new WebNovChapSearchDTO();
				
				PerChapCoin.setChapPerPrice(rset.getInt("CHAP_PER_PRICE"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return PerChapCoin;
	}

	public int updateChap(Connection con, int webNovNum, int webNovChapNum) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("updateChap");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, webNovChapNum);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int searchWebNovelCountPaid(Connection con, int webNovNum, int webNovChapNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("searchWebNovelCount");
		
		int webNovCount = 0;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, webNovChapNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				webNovCount = rset.getInt("COUNT(*)");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return webNovCount;
	}

	public WebNovelInfoDTO searchTitlePaid(Connection con, int webNovNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		WebNovelInfoDTO webNovelDetail = null;

		String query = prop.getProperty("searchTitle");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				webNovelDetail = new WebNovelInfoDTO();
				webNovelDetail.setCategoryName(new GenreCategoryDTO());

				webNovelDetail.setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovelDetail.setWebNovTitle(rset.getString("WEB_NOV_TITLE"));
				webNovelDetail.setWebNovAuthor(rset.getString("WEB_NOV_AUTHOR"));
				webNovelDetail.setDayOfWeek(rset.getString("DAY_OF_WEEK"));
				webNovelDetail.setWebNovInform(rset.getString("WEB_NOV_INFORM"));
				webNovelDetail.getCategoryName().setCategoryName(rset.getString("CATEGORY_NAME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return webNovelDetail;
	}

	public List<WebnovelReplyDTO> selectAllReply(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;

		List<WebnovelReplyDTO> webnovAllReply = null;
		System.out.println("DAO는?");
		String query = prop.getProperty("selectAllReply");
//		System.out.println("쿼리" + query);

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			webnovAllReply = new ArrayList<>();

			while(rset.next()) {
				WebnovelReplyDTO webNovReply = new WebnovelReplyDTO();
				webNovReply.setMemNum(new MemberDTO());
				webNovReply.setWebNovNum(new WebNovelInfoDTO());
				
				webNovReply.setReplyNum(rset.getInt("REPLY_NUM"));
				webNovReply.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				webNovReply.setReplyDate(rset.getDate("REPLY_DATE"));
				webNovReply.getMemNum().setMemNum(rset.getInt("MEM_NUM"));
			
				webNovReply.setReplyContent(rset.getString("REPLY_CONTENT"));
				
				webnovAllReply.add(webNovReply);
			}

			System.out.println(webnovAllReply);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return webnovAllReply;
	}

	public List<WebnovelReplyDTO> selectWebnovReply(Connection con, int no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<WebnovelReplyDTO> selectWebnovReply = null;
		String query = prop.getProperty("selectWebnovReply");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			selectWebnovReply = new ArrayList<>();
			while(rset.next()) {
				WebnovelReplyDTO reply = new WebnovelReplyDTO();
				reply.setWebNovNum(new WebNovelInfoDTO());
				reply.setMemNum(new MemberDTO());
				reply.setMemId(new MemberDTO());
				
				reply.setReplyNum(rset.getInt("REPLY_NUM"));
				reply.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				reply.setReplyDate(rset.getDate("REPLY_DATE"));
				reply.getMemNum().setMemNum(rset.getInt("MEM_NUM"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.getMemId().setMemId(rset.getString("MEM_ID"));
				
				selectWebnovReply.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return selectWebnovReply;
	}

	public int insertReply(Connection con, String replytext, int webNovNum, int memNum) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, memNum);
			pstmt.setString(3, replytext);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<WebnovelReplyDTO> replydata(Connection con, String replytext, int webNovNum, int memNum) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<WebnovelReplyDTO> replydata = null;
		String query = prop.getProperty("replydata");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, memNum);
			
			rset = pstmt.executeQuery();
			
			replydata = new ArrayList<>();
			while(rset.next()) {
				WebnovelReplyDTO reply = new WebnovelReplyDTO();
				reply.setWebNovNum(new WebNovelInfoDTO());
				reply.setMemId(new MemberDTO());
				
				reply.setReplyNum(rset.getInt("REPLY_NUM"));
				reply.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				reply.setReplyDate(rset.getDate("REPLY_DATE"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.getMemId().setMemId(rset.getString("MEM_ID"));
				
				replydata.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return replydata;
	}

	public List<WebNovelInfoDTO> selectFinishedAllNovel(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;

		List<WebNovelInfoDTO> webNovelFinishedList = null;
		String query = prop.getProperty("selectFinishedAllNovel");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			webNovelFinishedList = new ArrayList<>();

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
				

				webNovelFinishedList.add(webNovel);
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return webNovelFinishedList;
	}

	public int deleteReply(Connection con, int replyNum) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("deleteReply");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, replyNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}

	public List<WebnovelReplyDTO> replydata(Connection con, int webNovNum, int memNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<WebnovelReplyDTO> replydata = null;
		String query = prop.getProperty("replydata_delete");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, memNum);
			
			rset = pstmt.executeQuery();
			
			replydata = new ArrayList<>();
			while(rset.next()) {
				WebnovelReplyDTO reply = new WebnovelReplyDTO();
				reply.setWebNovNum(new WebNovelInfoDTO());
				reply.setMemId(new MemberDTO());
				
				reply.setReplyNum(rset.getInt("REPLY_NUM"));
				reply.getWebNovNum().setWebNovNum(rset.getInt("WEB_NOV_NUM"));
				reply.setReplyDate(rset.getDate("REPLY_DATE"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.getMemId().setMemId(rset.getString("MEM_ID"));
				
				replydata.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return replydata;
	}

	public List<WebNovelInfoDTO> selectTopNovel(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;

		List<WebNovelInfoDTO> webNovelList = null;
		String query = prop.getProperty("TopNovel");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			webNovelList = new ArrayList<>();

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
				

				webNovelList.add(webNovel);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return webNovelList;
		
	}
	
	public MemberDTO selectMemPoint(Connection con, String memId) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO result = null;
		
		String query = prop.getProperty("selectMemPoint");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				result = new MemberDTO();
				result.setMemCoin(rset.getInt("MEM_COIN"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateCoin(Connection con, String memId, int restCoin) {

		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("updateCoin");
		
//		System.out.println("db저장 전 금액" + restCoin);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, restCoin);
			pstmt.setString(2, memId);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertPaidHistory(Connection con, int webNovNum, int webNovChapNum, int memNum, int perChapCoin) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPaidHistory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, memNum);
			pstmt.setInt(3, perChapCoin);
			pstmt.setInt(4, webNovChapNum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}
	
	public int updateWishList(Connection con, int weblistNum, int memNum2) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateWishList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, weblistNum);
			pstmt.setInt(2, memNum2);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteWishList(Connection con, int weblistNum, int memNum2) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteWishList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, weblistNum);
			pstmt.setInt(2, memNum2);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public MemberDTO finishedselectMemPoint(Connection con, String memId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO result = null;
		
		String query = prop.getProperty("finishedselectMemPoint");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				result = new MemberDTO();
				result.setMemCoin(rset.getInt("MEM_COIN"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public WebNovChapSearchDTO finishedselectPerChapCoin(Connection con, int webNovNum, int webNovChapNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		WebNovChapSearchDTO PerChapCoin = null;
		
		String query = prop.getProperty("finishedselectPerChapCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, webNovChapNum);
	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PerChapCoin = new WebNovChapSearchDTO();
				
				PerChapCoin.setChapPerPrice(rset.getInt("CHAP_PER_PRICE"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return PerChapCoin;	
	}

	public int finishedinsertPaidHistory(Connection con, int webNovNum, int webNovChapNum, int memNum,
			int perChapCoin) {
		
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("finishedinsertPaidHistory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, webNovNum);
			pstmt.setInt(2, memNum);
			pstmt.setInt(3, perChapCoin);
			pstmt.setInt(4, webNovChapNum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}

	public UseCoinDTO selectBuyed(Connection con, int webNovNum, int webNovChapNum, int memNum) {
		// TODO Auto-generated method stub
		return null;
	}


}
















