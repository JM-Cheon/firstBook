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


@WebServlet("/novel/detail")
public class ShowNovelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		int no = Integer.valueOf(request.getParameter("NovNum"));
		
		NovelService novelService = new NovelService();
		
		/* 앞에서 전체 소설 목록 중에서 선택한 웹소설 번호로 소설 정보 조회 */
		NovelDTO NovDetail = novelService.selectNovelDetail(no);
		
						
		String path = "";
		
		
		if(NovDetail !=null) {		// 소설이 조회 되었다면
			path = "/WEB-INF/views/novel/novelDetail.jsp";
			request.setAttribute("NovDetail", NovDetail);
			
		}else {							// 소설이 조회 되지 않았다면
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectNovelDetail");
			
			
		}
		request.getRequestDispatcher(path).forward(request, response);
	
			
	}



}
