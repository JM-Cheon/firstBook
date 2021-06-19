package com.lastbug.firstbook.webnovel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapSearchDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;

@WebServlet("/firstbook/ajax/test")
public class WebnovelAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentPage = request.getParameter("currentPage");
		int currentWebNov = Integer.parseInt(request.getParameter("currentWebNov")) ;
		
		Gson gson = new Gson();

		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			/* 0보다 작은 숫자가 넘어오면 1페이지를 보여준다. */
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		int limit = 5;
		
		int buttonAmount = 5;
		
		
		WebNovelService webService = new WebNovelService();
		int totalCount = webService.selectTotalChapter(currentWebNov);
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		WebNovelInfoDTO webDetail = webService.selectWebNovelDetail(currentWebNov);

		List<WebNovChapSearchDTO> webNovelChap2 = null;
		
			if(webDetail != null) {
			
			webNovelChap2 = webService.selectWebNovelallChapterNotFree(webDetail);
			
			}
			
		String jsonString= gson.toJson(webNovelChap2);
		
		
		if(pageNo == 1) {
			pageInfo.setStartRow(1); 
			pageInfo.setEndRow(10);
		} else {
			pageInfo.setStartRow(pageInfo.getPageNo() + (9 * (pageInfo.getPageNo() -1)));
			pageInfo.setEndRow(pageInfo.getPageNo() * 10); 
		}
		 
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();

	}

}
