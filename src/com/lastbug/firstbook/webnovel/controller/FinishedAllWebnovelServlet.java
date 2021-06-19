package com.lastbug.firstbook.webnovel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;


@WebServlet("/webnovel/finished")
public class FinishedAllWebnovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		/* 완작 웹소설 전체 조회 */
		List<WebNovelInfoDTO> webNovelFinishedList = new WebNovelService().selectFinishedAllNovel();
	
//		for(WebNovelInfoDTO a : webNovelList) {
//			System.out.println(a);
//		}
		
		String path = "";
		
		if(!webNovelFinishedList.isEmpty()) {		// 웹소설이 조회 되었다면
//			System.out.println("조회됨?");
			path = "/WEB-INF/views/webnovel/finishedWebMain.jsp";
			request.setAttribute("webNovelFinishedList", webNovelFinishedList);
//			request.setAttribute("pageInfo", pageInfo);
		}else {							// 웹소설이 조회 되지 않았다면
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectFinishedAllNovel");
			
			
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	
	}



}
