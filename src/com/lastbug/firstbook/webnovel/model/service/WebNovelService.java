package com.lastbug.firstbook.webnovel.model.service;


import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.commit;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.UseCoinDTO;
import com.lastbug.firstbook.webnovel.model.dao.WebNovelDAO;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapSearchDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovContentDetailDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebnovelReplyDTO;

public class WebNovelService {

	static boolean isUpdate = false;
	private final WebNovelDAO webNovelDAO;
	public WebNovelService() {
		
		
		webNovelDAO = new WebNovelDAO();
	}

	/* 웹소설 전체 조회용 메소드 */
	public List<WebNovelInfoDTO> selectAllNovel() {

		Connection con = getConnection();

		//		System.out.println("커넥션에 왓니?");

		List<WebNovelInfoDTO> webNovelList = webNovelDAO.selectAllNovel(con);

		close(con);

//				System.out.println("service의 목록" + webNovelList);

		return webNovelList;
	}

	/* 전체 웹소설 중에서 선택한 웹소설 정보 조회용 */
	public WebNovelInfoDTO selectWebNovelDetail(int no) {

		Connection con = getConnection();
		WebNovelInfoDTO novelDetail = null;

		/* 조회수 증가 */
		int result = webNovelDAO.incrementWebnovelCount(con, no);

//		System.out.println("조회수 : " + result);

		if(result > 0) {

			novelDetail = webNovelDAO.selectWebNovelDetail(con, no);
			if(novelDetail != null) {

				commit(con);
			} else {
				rollback(con);
			}

		}else {
			rollback(con);
		}

		close(con);

		return novelDetail;
	}

	/* 선택한 웹소설에 대한 챕터 조회용 메소드 */
	public List<WebNovChapSearchDTO> selectWebNovelallChapter(WebNovelInfoDTO webDetail) {

		Connection con = getConnection();

		List<WebNovChapSearchDTO> webNovelChap = null;

		webNovelChap = webNovelDAO.selectWebNovelallChapter(con, webDetail);

		//		System.out.println("web회차 정보 service" + webNovelChap);


		if(webNovelChap != null) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		return webNovelChap;
	}

	/* 선택한 웹소설의 챕터안에 있는 웹소설 내용 조회용 메소드 */
	public List<WebNovContentDetailDTO> selectPerChap(PageInfoDTO pageInfo, int currentWebNovNum, int currentChapNum) {

		Connection con = getConnection();
		List<WebNovContentDetailDTO> perChap = null;
		perChap = webNovelDAO.selectPerChap(con, pageInfo, currentWebNovNum, currentChapNum);

		if(perChap != null) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);

		return perChap;
	}

	/* 선택한 웹소설의 챕터의 쪽수 계산 */
	public int searchWebNovelCount(int currentWebNovNum, int currentChapNum) {
		Connection con = getConnection();

		int totalCount = webNovelDAO.searchWebNovelCount(con, currentWebNovNum, currentChapNum);

		if(totalCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return totalCount;
	}

	/* 1~5화 제목 조회용 메소드 */
	public WebNovelInfoDTO searchTitle(int currentWebNovNum) {

		Connection con = getConnection();

		WebNovelInfoDTO title = webNovelDAO.searchTitle(con, currentWebNovNum);

		if(title != null) {
			commit(con);
		}else {
			rollback(con);
		}


		close(con);

		return title;
	}

	/* 전체 웹소설안에 챕터 개수 조회용 메소드 */
	public int selectTotalCount() {

		Connection con = getConnection();
		
		int result = webNovelDAO.selectTotalCount(con);
		
//		System.out.println("select total Count" + result);
		
		close(con);
		
		return result;

	}

	/* 선택한 웹소설 안에 있는 전체 챕터조회용 (유료) 메소드 */
	public List<WebNovChapSearchDTO> selectWebNovelallChapterNotFree(WebNovelInfoDTO webDetail) {

		Connection con = getConnection();

		List<WebNovChapSearchDTO> webNovelChap2 = null;

		webNovelChap2 = webNovelDAO.selectWebNovelallChapterNotFree(con, webDetail);

		//		System.out.println("web회차 정보 service" + webNovelChap);


		if(webNovelChap2 != null) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		return webNovelChap2;

	}

	/* 웹소설 안에 있는 모든 챕터 조회용 메소드 */
	public int selectTotalChapter(int currentWebNov) {

		Connection con = getConnection();
		
		int result = webNovelDAO.selectTotalChapter(con, currentWebNov);
		
//		System.out.println("select total Count(ajax)" + result);
		
		close(con);
		
		return result;

	}
	
	/* 6화부터 뿌려지는 웹소설 내용 조회용 메소드 */
	public int searchWebNovelCountPaid(int webNovNum, int webNovChapNum) {
		Connection con = getConnection();

		int totalCount = webNovelDAO.searchWebNovelCountPaid(con, webNovNum, webNovChapNum);

		if(totalCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return totalCount;
	}

	/* 6화부터 뿌려지는 제목 조회용 메소드 */
	public WebNovelInfoDTO searchTitlePaid(int webNovNum) {
		Connection con = getConnection();

		WebNovelInfoDTO title = webNovelDAO.searchTitlePaid(con, webNovNum);

		if(title != null) {
			commit(con);
		}else {
			rollback(con);
		}


		close(con);

		return title;
	}

	/* 전체 댓글 리스트 조회용 메소드 */
	public List<WebnovelReplyDTO> selectAllReply() {
		
		Connection con = getConnection();

		System.out.println("커넥션에 왓니?");

		List<WebnovelReplyDTO> webnovAllReply = webNovelDAO.selectAllReply(con);

		close(con);

		//		System.out.println("service의 목록" + webNovelList);

		return webnovAllReply;
	}

	/* 해당 웹소설 댓글 리스트 조회 */
	public List<WebnovelReplyDTO> selectWebnovReply(int no) {

		Connection con = getConnection();

		System.out.println("커넥션에 왓니?");

		List<WebnovelReplyDTO> selectWebnovReply = webNovelDAO.selectWebnovReply(con, no);

		close(con);

		if(selectWebnovReply != null) {
			commit(con);
		}else {
			rollback(con);
		}


		return selectWebnovReply;
	}

	/* 입력한 댓글 저장하는 메소드 */
	public int insertReply(String replytext, int webNovNum, int memNum) {

		Connection con = getConnection();
		
		int insertReply = webNovelDAO.insertReply(con, replytext, webNovNum, memNum);
		
		
		if(insertReply >  0) {
			
			commit(con);
		} else {
			
			rollback(con);
		}
		close(con);
		
		
		return insertReply;
	}

	/* 댓글 조회용 메소드 (ajax) */
	public List<WebnovelReplyDTO> replydata(String replytext, int webNovNum, int memNum) {

		Connection con = getConnection();
		
		List<WebnovelReplyDTO> replydata = webNovelDAO.replydata(con, replytext, webNovNum, memNum);
		
		
		if(replydata != null) {
			
			commit(con);
		} else {
			
			rollback(con);
		}
		close(con);
		
		
		return replydata;

	}

	/* 완작 웹소설 조회용 메소드 */
	public List<WebNovelInfoDTO> selectFinishedAllNovel() {
		
		Connection con = getConnection();

		//		System.out.println("커넥션에 왓니?");

		List<WebNovelInfoDTO> webNovelFinishedList = webNovelDAO.selectFinishedAllNovel(con);

		close(con);

		//		System.out.println("service의 목록" + webNovelList);

		return webNovelFinishedList;
	}

	public int deleteReply(int replyNum) {

		Connection con = getConnection();
		
		int result = webNovelDAO.deleteReply(con, replyNum);
		
		
		if(result >  0) {
			
			commit(con);
		} else {
			
			rollback(con);
		}
		close(con);
		
		
		return result;
	}

	public List<WebnovelReplyDTO> replydata(int webNovNum, int memNum) {

		Connection con = getConnection();
		
		List<WebnovelReplyDTO> replydata = webNovelDAO.replydata(con, webNovNum, memNum);
		
		
		if(replydata != null) {
			
			commit(con);
		} else {
			
			rollback(con);
		}
		close(con);
		
		
		return replydata;

	}

	public List<WebNovelInfoDTO> selectTopNovel() {
		Connection con = getConnection();

		List<WebNovelInfoDTO> webNovelList = webNovelDAO.selectTopNovel(con);

		close(con);

		return webNovelList;
	}
	
	/* 코인 차감용 메소드 */
	public MemberDTO chargeCoin(int webNovNum, int webNovChapNum, String memId, int memNum) {

		Connection con = getConnection();
		
//		UseCoinDTO webNov = webNovelDAO.selectBuyed(con, webNovNum, webNovChapNum, memNum);
		
//		if(!(webNov.getWebNov().getWebNovNum() == webNovNum && webNov.getChapterNum() == webNovChapNum)) {
//			
//			// 결제 안되었을 때
//			
//		}

		/* 현재 적립금 조회 */
		MemberDTO result = webNovelDAO.selectMemPoint(con, memId);

		int currentCoin = result.getMemCoin();
		System.out.println("현재 적립급" + currentCoin);


		/* 웹소설 편당 코인 가져오기 */
		WebNovelDAO webnovelDAO = new WebNovelDAO();
		WebNovChapSearchDTO webchap = webnovelDAO.selectPerChapCoin(con, webNovNum, webNovChapNum);
		int perChapCoin = webchap.getChapPerPrice();
		System.out.println("편당 코인 " + perChapCoin);
		
		/* 현재 보유코인 - 편당 코인 */
		int restCoin = currentCoin - perChapCoin;
		System.out.println("보유한 금액 - 편당코인 : " + restCoin);

		/* 편당 코인 차감 후 다시 넣기 */
		int updateCoin = webnovelDAO.updateCoin(con, memId, restCoin);
		System.out.println("업데이트 결과 : " + updateCoin);
		
		/* 읽을 수 있도록 전환하기 */
		int updateChap = webnovelDAO.updateChap(con, webNovNum, webNovChapNum);
		MemberDTO result2 = webNovelDAO.selectMemPoint(con, memId);
		System.out.println("업데이트 후 남은 코인 : " + result2.getMemCoin());
		
		if(updateCoin > 0 ) {
			
			/* 결제 내역에 추가 */
			 int historyResult = webnovelDAO.insertPaidHistory(con, webNovNum, webNovChapNum, memNum, perChapCoin);

			 if(historyResult > 0) {
				 
				 commit(con);
			 }else {
				 rollback(con);
			 }
		}else {
			rollback(con);
		}

		close(con);

		return result2;
	}
	
	
	
	public int updateWishList(int weblistNum, int memNum2) {

		Connection con = getConnection();

		int result = 0;

		//		System.out.println("boolean값(초기값) " + isUpdate);
		//
		if(!isUpdate) {	// 등록할 때
			if(result == 0) {	
				result = webNovelDAO.updateWishList(con, weblistNum, memNum2);
//				System.out.println("result" + result);
				if(result > 0) {	//업데이트 성공시
//					System.out.println("등록 됨?");
					isUpdate = true;
					commit(con);
					System.out.println("boolean값(등록 후) " + isUpdate);

				}else {
					rollback(con);
				}

			} 

		} else {	// 등록 삭제 할 때
			System.out.println("등록 실패??");
			if(result == 0) {
				result = webNovelDAO.deleteWishList(con, weblistNum, memNum2);

				if(result > 0) {
					commit(con);
					isUpdate = false;
				}else {
					rollback(con);
				}

			}

		}

		System.out.println("boolean값(리턴 전) " + isUpdate);

		return result;


	}

	public MemberDTO finishedchargeCoin(int webNovNum, int webNovChapNum, String memId, int memNum) {
		Connection con = getConnection();

		/* 현재 적립금 조회 */
		MemberDTO result = webNovelDAO.finishedselectMemPoint(con, memId);

		int currentCoin = result.getMemCoin();
		System.out.println("현재 적립급" + currentCoin);


		/* 웹소설 편당 코인 가져오기 */
		WebNovelDAO webnovelDAO = new WebNovelDAO();
		WebNovChapSearchDTO webchap = webnovelDAO.finishedselectPerChapCoin(con, webNovNum, webNovChapNum);
		int perChapCoin = webchap.getChapPerPrice();
		System.out.println("편당 코인 " + perChapCoin);
		
		/* 현재 보유코인 - 편당 코인 */
		int restCoin = currentCoin - perChapCoin;
		System.out.println("보유한 금액 - 편당코인 : " + restCoin);

		/* 편당 코인 차감 후 다시 넣기 */
		int updateCoin = webnovelDAO.updateCoin(con, memId, restCoin);
		System.out.println("업데이트 결과 : " + updateCoin);
		
		/* 읽을 수 있도록 전환하기 */
//		int updateChap = webnovelDAO.updateChap(con, webNovNum, webNovChapNum);
		MemberDTO result2 = webNovelDAO.finishedselectMemPoint(con, memId);
		System.out.println("업데이트 후 남은 코인 : " + result2.getMemCoin());
		
		if(updateCoin > 0) {
			
			/* 결제 내역에 추가 */
			 int historyResult = webnovelDAO.finishedinsertPaidHistory(con, webNovNum, webNovChapNum, memNum, perChapCoin);

			 if(historyResult > 0) {
				 
				 commit(con);
			 }else {
				 rollback(con);
			 }
		}else {
			rollback(con);
		}

		close(con);

		return result2;
	}
}
















