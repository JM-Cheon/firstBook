package com.lastbug.firstbook.member.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.commit;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lastbug.firstbook.member.model.dao.MemberDAO;
import com.lastbug.firstbook.member.model.dto.CoinChargeDTO;
import com.lastbug.firstbook.member.model.dto.FaqDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.member.model.dto.QnaDTO;
import com.lastbug.firstbook.member.model.dto.QnaReplyDTO;
import com.lastbug.firstbook.member.model.dto.UseCoinDTO;
import com.lastbug.firstbook.member.model.dto.WishlistDTO;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dao.WebNovelDAO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapSearchDTO;

public class MemberService {
	
	private final MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}



	/* 회원 가입용 메소드 */
	public int insertMember(MemberDTO newMember) {

		Connection con = getConnection();

		int result = memberDAO.insertMember(con, newMember);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	/* 아이디 중복검사용 메소드 */
	public String idCheck(String memId) {

		Connection con = getConnection();

		String result = memberDAO.idCheck(con, memId);

		close(con);

		return result;
	}

	/* 로그인 처리용 메소드 */
	public MemberDTO loginCheck(MemberDTO requestMember) {

		Connection con = getConnection();
		MemberDTO loginMember = null;

		String encPwd = memberDAO.selectEncryptedPwd(con, requestMember);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		//		System.out.println(requestMember.getMemPwd());
		if(passwordEncoder.matches(requestMember.getMemPwd(), encPwd)) {

			loginMember = memberDAO.selectLoginMember(con, requestMember);
		}

		return loginMember;
	}

	/* 코인 사용 내역 조회용 메소드 */
	public List<UseCoinDTO> selectUseCoin(int memNum) {

		Connection con = getConnection();

		List<UseCoinDTO> useCoinList = memberDAO.selectUseCoin(con, memNum);

		close(con);

		return useCoinList;
	}

	/* 회원 정보 수정용 메소드 */
	public int updateMember(MemberDTO requestMember) {

		Connection con = getConnection();

		int result = memberDAO.updateMember(con, requestMember);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	/* 회원 탈퇴용 메소드 */
	public int deleteMember(MemberDTO requestMember) {

		Connection con = getConnection();
		int result = 0;

		String encPwd = memberDAO.selectEncryptedPwd(con, requestMember);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if(passwordEncoder.matches(requestMember.getMemPwd(), encPwd)) {

			result = memberDAO.deleteMember(con, requestMember);

			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}

		close(con);

		return result;
	}

	/* 위시리스트 조회용 메소드 */
	public List<WishlistDTO> selectWishlist(int memNum) {

		Connection con = getConnection();

		List<WishlistDTO> wishlist = memberDAO.selectWishlist(con, memNum);

		close(con);

		return wishlist;
	}

	/* 로그인 시 로그인 카운트 증가 */
	public int incrementLoginCount(MemberDTO requestMember) {

		Connection con = getConnection();

		int result = memberDAO.incrementLoginCount(con, requestMember);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	/* 무료 코인 지급용 메소드 */
	public int incrementFreeCoin(int memNum) {

		Connection con = getConnection();

		int result = memberDAO.incrementFreeCoin(con, memNum);

		if(result > 0) {
			result = memberDAO.insertFreeCoin(con, memNum);
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 회원 정보 조회용 메소드 */
	public MemberDTO selectMember(int memNum) {

		Connection con = getConnection();

		MemberDTO requestMember = memberDAO.selectMember(con, memNum);

		close(con);

		return requestMember;
	}

	/* 공지 사항 페이지네이션 구현을 위한 공지 사항 총 갯수 조회용 메소드 */
	public int selectTotalCount() {

		Connection con = getConnection();
		
		int totalCount = memberDAO.selectTotalCount(con);
		
		close(con);
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 공지 사항 리스트 조회용 메소드 */
	public List<NoticeDTO> selectNoticeList(PageInfoDTO pageInfo) {

		Connection con = getConnection();
		
		List<NoticeDTO> noticeList = memberDAO.selectNoticeList(con, pageInfo);
		
		close(con);
		
		return noticeList;
	}

	/* 공지 사항 상세 정보 출력용 메소드 */
	public NoticeDTO selectNoticeDetail(int noticeNum) {

		Connection con = getConnection();
		
		NoticeDTO noticeDetail = null;
		
		int result = memberDAO.incrementNoticeCount(con, noticeNum);

		if(result > 0) {
			noticeDetail = memberDAO.selectNoticeDetail(con, noticeNum);
			
			if(noticeDetail != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return noticeDetail;

	}

	/* 자주 묻는 질문 페이지네이션 구현을 위한 자주 묻는 질문 총 갯수 조회용 메소드 */
	public int selectFAQTotalCount() {
		
		Connection con = getConnection();
		
		int totalCount = memberDAO.selectFAQTotalCount(con);
		
		close(con);
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 자주 묻는 질문 리스트 조회용 메소드 */
	public List<FaqDTO> selectFAQList(PageInfoDTO pageInfo) {
		
		Connection con = getConnection();
		
		List<FaqDTO> faqList = memberDAO.selectFAQList(con, pageInfo);
		
		close(con);
		
		return faqList;
	}

	/* 자주 묻는 질문 상세 정보 출력용 메소드 */
	public FaqDTO selectFaqDetail(int faqNum) {

		Connection con = getConnection();
		
		FaqDTO faqDetail = memberDAO.selectFaqDetail(con, faqNum);
			
		
		close(con);
		
		return faqDetail;
	}	
	



	/* 1:1 질문 페이지네이션 구현을 위한 자주 묻는 질문 총 갯수 조회용 메소드 */
	public int selectQNATotalCount(int memNum) {

		Connection con = getConnection();
		
		int totalCount = memberDAO.selectQNATotalCount(con, memNum);
		
		close(con);
		
		return totalCount;
	}

	/* 페이지네이션에의해 갯수 제한을 둔 1:1 질문 리스트 조회용 메소드 */
	public List<QnaDTO> selectQnaList(PageInfoDTO pageInfo, int memNum) {

		Connection con = getConnection();
		
		List<QnaDTO> qnaList = memberDAO.selectQnaList(con, pageInfo, memNum);
		
		close(con);
		
		return qnaList;
	}

	/* 1:1 질문 상세 정보 출력용 메소드 */
	public QnaDTO selectQnaDetail(int qnaNum, int memNum) {

		Connection con = getConnection();
		
		QnaDTO qnaDetail = memberDAO.selectQnaDetail(con, qnaNum, memNum);
		
		close(con);
		
		return qnaDetail;
	}

	/* 1:1 질문 댓글 출력용 메소드 */
	public List<QnaReplyDTO> selectQnaReplyList(int postNum) {

		Connection con = getConnection();
		
		List<QnaReplyDTO> replyList = memberDAO.selectQnaReplyList(con, postNum);
		
		close(con);
		
		return replyList;
	}

	/* 1:1 질문 댓글 insert용 메소드 */
	public int insertQnaReply(QnaReplyDTO insertReply) {
		
		Connection con = getConnection();
		
		int result = memberDAO.insertQnaReply(con, insertReply);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 1:1 질문 insert용 메소드 */
	public int insertQNA(QnaDTO qna) {

		Connection con = getConnection();
		
		int result = memberDAO.insertQNA(con, qna);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 코인 결제 후 코인 및 코인 충전 내역 insert용 메소드 */
	public int insertCoin(int memNum, int amount) {

		Connection con = getConnection();
		
		int result = memberDAO.insertCoin(con, memNum, amount);
		
		if(result > 0) {
			result = memberDAO.updateCoinCharge(con, memNum, amount);
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	/* 코인 충전 내역 조회용 메소드 */
	public List<CoinChargeDTO> selectCoinCharge(int memNum) {
		
		Connection con = getConnection();
		
		List<CoinChargeDTO> coinChargeList = memberDAO.selectCoinCharge(con, memNum);
		
		close(con);
		
		return coinChargeList;
	}

	/* 아이디 찾기용 메소드 */
	public MemberDTO searchId(MemberDTO member) {

		Connection con = getConnection();
		
		MemberDTO searchMember = memberDAO.searchId(con, member);
		
		close(con);
		
		return searchMember;
	}

	/* 비밀번호 찾기용 메소드 */
	public MemberDTO searchPwd(MemberDTO member) {

		Connection con = getConnection();
		
		MemberDTO searchMember = memberDAO.searchPwd(con, member);
		
		close(con);
		
		return searchMember;
	}

	/* 비밀번호 update용 메소드 */
	public int updatePassword(String memId, String memPwd) {
		
		Connection con = getConnection();

		int result = memberDAO.updatePassword(con, memId, memPwd);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}


	
}

















