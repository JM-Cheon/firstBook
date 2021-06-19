package com.lastbug.firstbook.admin.webnovel.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.commit;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.lastbug.firstbook.admin.webnovel.model.dao.AdminWebNovelDAO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class AdminWebNovelService {
	
	private final AdminWebNovelDAO adminWebNovelDAO;
	
	public AdminWebNovelService() {
		adminWebNovelDAO = new AdminWebNovelDAO();
	}

	/* 관리자 페이지에서 모든 웹소설에 대한 정보 조회 */
	public List<WebNovelInfoDTO> AdminSelectAllNovel() {

		Connection con = getConnection();
		
		List<WebNovelInfoDTO> adminWebNovelList = adminWebNovelDAO.selectAllNovel(con);

		close(con);
		
		
		return adminWebNovelList;
	}

	public int insertNewWebnovel(WebNovelInfoDTO thumbnail) {

		Connection con = getConnection();
		
		int result = 0;
		
		int webNovelResult = adminWebNovelDAO.insertNewWebnovel(con, thumbnail);
		
		if(webNovelResult > 0) {
			commit(con);
			result = 1;
		} else {
			rollback(con);			
		}
		
		close(con);
		
		return result;
	}

	public WebNovelInfoDTO adminSelectWebNovelDetail(int no) {

		Connection con = getConnection();
		
		WebNovelInfoDTO webNovelUpdate = null;
		
		webNovelUpdate = adminWebNovelDAO.adminSelectWebNovelDetail(con, no);
		
		
		
		return webNovelUpdate;
	}

	public int WebNovelCloseOrOpen(int no, String closeOrOpen, WebNovelInfoDTO adminWebNovelList) {
	
		Connection con = getConnection();
		
		int result = adminWebNovelDAO.WebNovelCloseOrOpen(con, closeOrOpen, adminWebNovelList);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	public int WebNovelModify(int no, int currentChap, int pg0, int pg1, String body0, String body1) {
				
		Connection con = getConnection();

		System.out.println("service body0 :" + body0);
		

		
		int result = adminWebNovelDAO.WebNovelModify(con, no, currentChap, pg0, pg1, body0, body1);
		
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	

	}

}












