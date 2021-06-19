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


@WebServlet("/webnovel/list")
public class ShowAllWebNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("여기왓니?");
		
		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			/* 0보다 작은 숫자가 넘어오면 1페이지를 보여준다. */
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		WebNovelService webService = new WebNovelService();
		int totalCount = webService.selectTotalCount();
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		List<WebNovelInfoDTO> webNovelList = new WebNovelService().selectAllNovel();
	
		for(WebNovelInfoDTO a : webNovelList) {
			System.out.println(a);
		}
		
		String path = "";
		
		if(!webNovelList.isEmpty()) {		// 웹소설이 조회 되었다면
//			System.out.println("조회됨?");
			path = "/WEB-INF/views/webnovel/webMain.jsp";
			request.setAttribute("webNovelList", webNovelList);
			request.setAttribute("pageInfo", pageInfo);
		}else {							// 웹소설이 조회 되지 않았다면
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectAllWebNovel");
			
			
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}