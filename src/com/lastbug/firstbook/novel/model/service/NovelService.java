package com.lastbug.firstbook.novel.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.lastbug.firstbook.novel.model.dao.NovelDAO;
import com.lastbug.firstbook.novel.model.dto.NovelDTO;

public class NovelService {
	
	private final NovelDAO novelDAO;
	public NovelService() {
		novelDAO = new NovelDAO();
	}

	
	
	public List<NovelDTO> selectAllNovel() {
		
		Connection con = getConnection();


		List<NovelDTO> NovelList = novelDAO.selectAllNovel(con);

		close(con);


		return NovelList;
			

	}



	public NovelDTO selectNovelDetail(int no) {
		
		Connection con = getConnection();
		NovelDTO NovDetail = null;
		
		NovDetail = novelDAO.selectNovelDetail(con, no);
			
			if(NovDetail != null) {
				commit(con);
			} else {
				rollback(con); 
				
			} 	close(con);
		
		return NovDetail;
		
				
	}
		
}		
	


