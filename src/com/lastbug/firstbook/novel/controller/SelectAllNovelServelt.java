package com.lastbug.firstbook.novel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.novel.model.dto.NovelDTO;
import com.lastbug.firstbook.novel.model.service.NovelService;



@WebServlet("/novel/list")
public class SelectAllNovelServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<NovelDTO> NovelList = new NovelService().selectAllNovel();
		
		String path = "";
		
		
		
		if(!NovelList.isEmpty()) {		// 소설이 조회 되었다면
			path = "/WEB-INF/views/novel/novelMain.jsp";
			request.setAttribute("NovelList", NovelList);
			
		}else {							// 소설이 조회 되지 않았다면
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "소설 전체 조회 실패");
			
			
		}
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}

}
