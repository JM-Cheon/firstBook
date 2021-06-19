package com.lastbug.firstbook.member.model.dao;

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
import com.lastbug.firstbook.member.model.dto.CoinChargeDTO;
import com.lastbug.firstbook.member.model.dto.FaqDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.member.model.dto.QnaDTO;
import com.lastbug.firstbook.member.model.dto.QnaReplyDTO;
import com.lastbug.firstbook.member.model.dto.UseCoinDTO;
import com.lastbug.firstbook.member.model.dto.WishlistDTO;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class MemberDAO {

	private final Properties prop;
	
	public MemberDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "member/member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* MEM_INFO TABLE 신규 회원 insert용 메소드 */
	public int insertMember(Connection con, MemberDTO newMember) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMember.getMemName());
			pstmt.setString(2, newMember.getMemId());
			pstmt.setString(3, newMember.getMemPwd());
			pstmt.setString(4, newMember.getMemEmail());
			pstmt.setString(5, newMember.getMemAddress());
			pstmt.setDate(6, newMember.getMemBirthDate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 아이디 조회용 메소드 (아이디 중복검사) */
	public String idCheck(Connection con, String memId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String result = "";
		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("MEM_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
				
		return result;
	}

	/* 암호화 처리 된 비밀번호 조회용 메소드(로그인 확인용) */
	public String selectEncryptedPwd(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String encPwd = null;
		
		String query = prop.getProperty("selectEncryptedPwd");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getMemId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				encPwd = rs.getString("MEM_PWD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return encPwd;
	}

	/* 회원의 전체 정보 조회용 메소드 (로그인용) */
	public MemberDTO selectLoginMember(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDTO loginMember = null;
		
		String query = prop.getProperty("selectLoginMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getMemId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new MemberDTO();
				loginMember.setMemNum(rs.getInt("MEM_NUM"));
				loginMember.setMemName(rs.getString("MEM_NAME"));
				loginMember.setMemId(rs.getString("MEM_ID"));
				loginMember.setMemPwd(rs.getString("MEM_PWD"));
				loginMember.setMemEmail(rs.getString("MEM_EMAIL"));
				loginMember.setMemAddress(rs.getString("MEM_ADDRESS"));
				loginMember.setMemBirthDate(rs.getDate("MEM_BIRTHDATE"));
				loginMember.setMemLoginCount(rs.getInt("MEM_LOGIN_COUNT"));
				loginMember.setMemCoin(rs.getInt("MEM_COIN"));
				loginMember.setMemCanVoteYn(rs.getString("MEM_CAN_VOTE_YN"));
				loginMember.setMemWithdrawYn(rs.getString("MEM_WITHDRAW_YN"));
				loginMember.setMemWithdrawDate(rs.getDate("MEM_WITHDRAW_DATE"));
				loginMember.setMemClass(rs.getString("MEM_CLASS"));
				loginMember.setMemBlockYn(rs.getString("MEM_BLOCK_YN"));
				loginMember.setMemBlockDate(rs.getDate("MEM_BLOCK_DATE"));
				loginMember.setMemEnrollDate(rs.getDate("MEM_ENROLL_DATE"));
				loginMember.setMemWeeklyCoinYn(rs.getString("MEM_WEEKLY_COIN_YN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}

	/* 회원 코인 사용 내역 전체 조회용 메소드 */
	public List<UseCoinDTO> selectUseCoin(Connection con, int memNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UseCoinDTO> useCoinList = null;
		
		String query = prop.getProperty("selectUseCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			rs = pstmt.executeQuery();
			
			useCoinList = new ArrayList<>();
			
			while(rs.next()) {
				UseCoinDTO useCoin = new UseCoinDTO();
				WebNovelInfoDTO webNov = new WebNovelInfoDTO();
				
				useCoin.setCoinUseNum(rs.getInt("COIN_USE_NUM"));
				useCoin.setCoinUseDate(rs.getDate("COIN_USE_DATE"));
				useCoin.setCoinUsage(rs.getInt("COIN_USAGE"));
				useCoin.setChapterNum(rs.getInt("CHAPTER_NUM"));
				useCoin.setMemNum(rs.getInt("MEM_NUM"));
				
				webNov.setWebNovNum(rs.getInt("WEB_NOV_NUM"));
				webNov.setCategoryCode(rs.getString("CATEGORY_CODE"));
				webNov.setWebNovTitle(rs.getString("WEB_NOV_TITLE"));
				webNov.setWebNovAuthor(rs.getString("WEB_NOV_AUTHOR"));
				webNov.setWebNovPublisher(rs.getString("WEB_NOV_PUBLISHER"));
				webNov.setWebNovInform(rs.getString("WEB_NOV_INFORM"));
				webNov.setWebNovImgLocation(rs.getString("WEB_NOV_IMG_LOCATION"));
				webNov.setDayOfWeek(rs.getString("DAY_OF_WEEK"));
				webNov.setWebNovOpenOrClose(rs.getString("WEB_NOV_IS_OPEN"));
				webNov.setFinishedOrNot(rs.getString("FINISHED_OR_NOT"));
				
				useCoin.setWebNov(webNov);
				useCoinList.add(useCoin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return useCoinList;
	}

	/* 회원 정보 수정용 메소드 */
	public int updateMember(Connection con, MemberDTO requestMember) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getMemName());
			pstmt.setString(2, requestMember.getMemPwd());
			pstmt.setString(3, requestMember.getMemEmail());
			pstmt.setString(4, requestMember.getMemAddress());
			pstmt.setDate(5, requestMember.getMemBirthDate());
			pstmt.setString(6, requestMember.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 회원 탈퇴용 메소드 */
	public int deleteMember(Connection con, MemberDTO requestMember) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 위시리스트 조회용 메소드 */
	public List<WishlistDTO> selectWishlist(Connection con, int memNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<WishlistDTO> wishlist = null;
		
		String query = prop.getProperty("selectWishlist");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			rs = pstmt.executeQuery();
			
			wishlist = new ArrayList<>();
			
			while(rs.next()) {
				WishlistDTO wish = new WishlistDTO();
				WebNovelInfoDTO webNov = new WebNovelInfoDTO();
				
				wish.setMemNum(rs.getInt("MEM_NUM"));
				wish.setWishlistDate(rs.getDate("WISHLIST_DATE"));
				
				webNov.setWebNovNum(rs.getInt("WEB_NOV_NUM"));
				webNov.setCategoryCode(rs.getString("CATEGORY_CODE"));
				webNov.setWebNovTitle(rs.getString("WEB_NOV_TITLE"));
				webNov.setWebNovAuthor(rs.getString("WEB_NOV_AUTHOR"));
				webNov.setWebNovPublisher(rs.getString("WEB_NOV_PUBLISHER"));
				webNov.setWebNovInform(rs.getString("WEB_NOV_INFORM"));
				webNov.setWebNovImgLocation(rs.getString("WEB_NOV_IMG_LOCATION"));
				webNov.setDayOfWeek(rs.getString("DAY_OF_WEEK"));
				webNov.setWebNovOpenOrClose(rs.getString("WEB_NOV_IS_OPEN"));
				webNov.setFinishedOrNot(rs.getString("FINISHED_OR_NOT"));
				
				wish.setWebNov(webNov);
				wishlist.add(wish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return wishlist;
	}

	/* 로그인 횟수 증가용 메소드 */
	public int incrementLoginCount(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("incrementLoginCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 무료 코인 지급용 메소드 */
	public int incrementFreeCoin(Connection con, int memNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("incrementFreeCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 회원 정보 조회용 메소드 */
	public MemberDTO selectMember(Connection con, int memNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDTO requestMember = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				requestMember = new MemberDTO();
				requestMember.setMemNum(rs.getInt("MEM_NUM"));
				requestMember.setMemName(rs.getString("MEM_NAME"));
				requestMember.setMemId(rs.getString("MEM_ID"));
				requestMember.setMemPwd(rs.getString("MEM_PWD"));
				requestMember.setMemEmail(rs.getString("MEM_EMAIL"));
				requestMember.setMemAddress(rs.getString("MEM_ADDRESS"));
				requestMember.setMemBirthDate(rs.getDate("MEM_BIRTHDATE"));
				requestMember.setMemLoginCount(rs.getInt("MEM_LOGIN_COUNT"));
				requestMember.setMemCoin(rs.getInt("MEM_COIN"));
				requestMember.setMemCanVoteYn(rs.getString("MEM_CAN_VOTE_YN"));
				requestMember.setMemWithdrawYn(rs.getString("MEM_WITHDRAW_YN"));
				requestMember.setMemWithdrawDate(rs.getDate("MEM_WITHDRAW_DATE"));
				requestMember.setMemClass(rs.getString("MEM_CLASS"));
				requestMember.setMemBlockYn(rs.getString("MEM_BLOCK_YN"));
				requestMember.setMemBlockDate(rs.getDate("MEM_BLOCK_DATE"));
				requestMember.setMemEnrollDate(rs.getDate("MEM_ENROLL_DATE"));
				requestMember.setMemWeeklyCoinYn(rs.getString("MEM_WEEKLY_COIN_YN"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return requestMember;
	}

	/* 공지 사항 페이지네이션 구현을 위한 공지 사항 총 갯수 조회용 메소드 */
	public int selectTotalCount(Connection con) {

		Statement stmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectTotalCount");
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 공지 사항 리스트 조회용 메소드 */
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

	/* 공지 사항 상세 정보 출력용 메소드 */
	public NoticeDTO selectNoticeDetail(Connection con, int noticeNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeDTO noticeDetail = null;
		
		String query = prop.getProperty("selectNoticeDetail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeDetail = new NoticeDTO();
				noticeDetail.setNoticeNum(rs.getInt("NOTICE_NUM"));
				noticeDetail.setNoticeDate(rs.getDate("NOTICE_DATE"));
				noticeDetail.setNoticeName(rs.getString("NOTICE_NAME"));
				noticeDetail.setNoticeViewCount(rs.getInt("NOTICE_VIEW_COUNT"));
				noticeDetail.setNoticeContent(rs.getString("NOTICE_CONTENT"));
				noticeDetail.setNoticeStatus(rs.getString("NOTICE_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return noticeDetail;
	}

	/* 공지 사항 조회 수 증가용 메소드 */
	public int incrementNoticeCount(Connection con, int noticeNum) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("incrementNoticeCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticeNum);
			pstmt.setInt(2, noticeNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 자주 묻는 질문 페이지네이션 구현을 위한 자주 묻는 질문 총 갯수 조회용 메소드 */
	public int selectFAQTotalCount(Connection con) {

		Statement stmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectFAQTotalCount");
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 자주 묻는 질문 리스트 조회용 메소드 */
	public List<FaqDTO> selectFAQList(Connection con, PageInfoDTO pageInfo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<FaqDTO> faqList = null;
		
		String query = prop.getProperty("selectFAQList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartRow());
			pstmt.setInt(2, pageInfo.getEndRow());
			
			rs = pstmt.executeQuery();
			
			faqList = new ArrayList<>();
			
			while(rs.next()) {
				FaqDTO faq = new FaqDTO();
				
				faq.setPostNum(rs.getInt("POST_NUM"));
				faq.setFaqPostTitle(rs.getString("FAQ_POST_TITLE"));
				faq.setFaqPostContent(rs.getString("FAQ_POST_CONTENT"));
				
				faqList.add(faq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return faqList;
	}

	/* 자주 묻는 질문 상세 정보 출력용 메소드 */
	public FaqDTO selectFaqDetail(Connection con, int faqNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		FaqDTO faqDetail = null;
		
		String query = prop.getProperty("selectFaqDetail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, faqNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				faqDetail = new FaqDTO();
				
				faqDetail.setPostNum(rs.getInt("POST_NUM"));
				faqDetail.setFaqPostTitle(rs.getString("FAQ_POST_TITLE"));
				faqDetail.setFaqPostContent(rs.getString("FAQ_POST_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return faqDetail;
	}

	/* 1:1 질문 페이지네이션 구현을 위한 자주 묻는 질문 총 갯수 조회용 메소드 */
	public int selectQNATotalCount(Connection con, int memNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		String query = prop.getProperty("selectQNATotalCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 1:1 질문 리스트 조회용 메소드 */
	public List<QnaDTO> selectQnaList(Connection con, PageInfoDTO pageInfo, int memNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QnaDTO> qnaList = null;
		
		String query = prop.getProperty("selectQNAList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, memNum);
			pstmt.setInt(2, pageInfo.getStartRow());
			pstmt.setInt(3, pageInfo.getEndRow());
			
			rs = pstmt.executeQuery();
			
			qnaList = new ArrayList<>();
			
			while(rs.next()) {
				QnaDTO qna = new QnaDTO();
				MemberDTO member = new MemberDTO();
				member.setMemName(rs.getString("MEM_NAME"));
				
				qna.setPostNum(rs.getInt("POST_NUM"));
				qna.setMemNum(member);
				qna.setQnaTitle(rs.getString("QNA_TITLE"));
				qna.setQnaOpenYn(rs.getString("QNA_OPEN_YN"));
				qna.setQnaAnswerYn(rs.getString("QNA_ANSWER_YN"));
				qna.setQnaDate(rs.getDate("QNA_DATE"));
				qna.setQnaContent(rs.getString("QNA_CONTENT"));
				
				qnaList.add(qna);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaList;
	}

	/* 1:1 질문 상세 정보 출력용 메소드 */
	public QnaDTO selectQnaDetail(Connection con, int qnaNum, int memNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		QnaDTO qnaDetail = null;
		
		String query = prop.getProperty("selectQnaDetail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			pstmt.setInt(2, qnaNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaDetail = new QnaDTO();
				MemberDTO member = new MemberDTO();
				member.setMemName(rs.getString("MEM_NAME"));
				
				qnaDetail.setPostNum(rs.getInt("POST_NUM"));
				qnaDetail.setMemNum(member);
				qnaDetail.setQnaTitle(rs.getString("QNA_TITLE"));
				qnaDetail.setQnaOpenYn(rs.getString("QNA_OPEN_YN"));
				qnaDetail.setQnaAnswerYn(rs.getString("QNA_ANSWER_YN"));
				qnaDetail.setQnaDate(rs.getDate("QNA_DATE"));
				qnaDetail.setQnaContent(rs.getString("QNA_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaDetail;
	}

	/* 1:1 질문 댓글 출력용 메소드 */
	public List<QnaReplyDTO> selectQnaReplyList(Connection con, int postNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QnaReplyDTO> replyList = null;
		
		String query = prop.getProperty("selectQnaReplyList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, postNum);
			
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<>();
			
			while(rs.next()) {
				QnaReplyDTO reply = new QnaReplyDTO();
				MemberDTO member = new MemberDTO();
				member.setMemName(rs.getString("MEM_NAME"));
				
				reply.setReplyNum(rs.getInt("REPLY_NUM"));
				reply.setPostNum(rs.getInt("POST_NUM"));
				reply.setReplyDate(rs.getDate("REPLY_DATE"));
				reply.setMember(member);
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replyList;
	}

	/* 1:1 질문 댓글 insert용 메소드 */
	public int insertQnaReply(Connection con, QnaReplyDTO insertReply) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQnaReply");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, insertReply.getPostNum());
			pstmt.setInt(2, insertReply.getMember().getMemNum());
			pstmt.setString(3, insertReply.getReplyContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 1:1 질문 댓글 insert용 메소드 */
	public int insertQNA(Connection con, QnaDTO qna) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQNA");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qna.getMemNum().getMemNum());
			pstmt.setString(2, qna.getQnaTitle());
			pstmt.setString(3, qna.getQnaContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 코인 결제 후 코인 insert용 메소드 */
	public int insertCoin(Connection con, int memNum, int amount) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			pstmt.setInt(2, amount);
			pstmt.setInt(3, amount * 100);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 코인 결제 후 코인 충전 내역 insert용 메소드 */
	public int updateCoinCharge(Connection con, int memNum, int amount) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCoinCharge");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, memNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 코인 충전 내역 조회용 메소드 */
	public List<CoinChargeDTO> selectCoinCharge(Connection con, int memNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CoinChargeDTO> coinChargeList = null;
		
		String query = prop.getProperty("selectCoinCharge");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			coinChargeList = new ArrayList<>();
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CoinChargeDTO coinCharge = new CoinChargeDTO();
				
				coinCharge.setCoinChargeNum(rs.getInt("COIN_CHARGE_NUM"));
				coinCharge.setMemNum(rs.getInt("MEM_NUM"));
				coinCharge.setCoinChargeDate(rs.getDate("COIN_CHARGE_DATE"));
				coinCharge.setCoinChargeCount(rs.getInt("COIN_CHARGE_COUNT"));
				coinCharge.setCoinChargeTotal(rs.getInt("COIN_CHARGE_TOTAL"));
				coinCharge.setWeeklyCoinYn(rs.getString("WEEKLY_COIN_YN"));
				
				coinChargeList.add(coinCharge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return coinChargeList;
	}

	/* 무료 코인 내역 insert용 메소드 */
	public int insertFreeCoin(Connection con, int memNum) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFreeCoin");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 아이디 찾기용 메소드 */
	public MemberDTO searchId(Connection con, MemberDTO member) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDTO searchMember = null;
		
		String query = prop.getProperty("searchId");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemEmail());
			
			rs = pstmt.executeQuery();
			
			searchMember = new MemberDTO();
			
			if(rs.next()) {
				searchMember.setMemId(rs.getString("MEM_ID"));
				searchMember.setMemEmail(rs.getString("MEM_EMAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return searchMember;
	}

	/* 비밀번호 찾기용 메소드 */
	public MemberDTO searchPwd(Connection con, MemberDTO member) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDTO searchMember = null;
		
		String query = prop.getProperty("searchPwd");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemId());
			pstmt.setString(3, member.getMemEmail());
			
			rs = pstmt.executeQuery();
			
			searchMember = new MemberDTO();
			
			if(rs.next()) {
				searchMember.setMemName(rs.getString("MEM_NUM"));
				searchMember.setMemName(rs.getString("MEM_NAME"));
				searchMember.setMemId(rs.getString("MEM_ID"));
				searchMember.setMemPwd(rs.getString("MEM_PWD"));
				searchMember.setMemEmail(rs.getString("MEM_EMAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao : " + searchMember);
		return searchMember;
	}

	/* 비밀번호 찾기 후 비밀번호 변경용 메소드 */
	public int updatePassword(Connection con, String memId, String memPwd) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memPwd);
			pstmt.setString(2, memId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

	
}

















