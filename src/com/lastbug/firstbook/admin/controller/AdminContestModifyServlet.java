package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminContestService;
import com.lastbug.firstbook.contest.model.dto.CompetContentDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.contest.model.service.SelectViewService;



@WebServlet("/admin/contestModify")
public class AdminContestModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String body0 = request.getParameter("body0");
		String body1 = request.getParameter("body1");
		int nop = Integer.valueOf(request.getParameter("nop"));		
		int pg0 = Integer.valueOf(request.getParameter("pg0"));
		int pg1 = Integer.valueOf(request.getParameter("pg1"));
		
		AdminContestService acService = new AdminContestService();
		
		
		int result = acService.ContestModify(nop, pg0, body0, pg1, body1 );
		
		int competNum = Integer.valueOf(request.getParameter("competNum"));	// 웹소설 
		int chapterNum = Integer.valueOf(request.getParameter("chapterNum"));	//	웹 회차
		int first = Integer.valueOf(request.getParameter("first"));	//	웹 회차
		int second = first + 1;
		
		
		List<ContestDetalDTO> selectView = new SelectViewService().selectView(competNum, chapterNum);
		List<ContestDTO> contestList = new SelectViewService().selectContestList(competNum);
		String NumberOfPages = String.valueOf(nop);
		List<CompetContentDTO> PageNubmer = new SelectViewService().pageNubmer(NumberOfPages);
		List<CompetContentDTO> viewPage = new SelectViewService().selectViewPage(NumberOfPages, first);
		List<CompetContentDTO> viewPageTwo = new SelectViewService().selectViewPageTwo(NumberOfPages, second);
		
		int lastPage = PageNubmer.size();
		
		
		
	
		String path = "";
		if(result!=0) {		// 공모전 작품이 검색되면
			path = "/WEB-INF/views/admin/adminContestEditor.jsp";
			request.setAttribute("selectView", selectView);	
			request.setAttribute("contestList", contestList);	
			request.setAttribute("viewPage", viewPage);	
			request.setAttribute("viewPageTwo", viewPageTwo);	
			request.setAttribute("competNum", competNum);	
			request.setAttribute("chapterNum", chapterNum);	
			request.setAttribute("first", first);	
			request.setAttribute("lastPage", lastPage);	
			request.setAttribute("NumberOfPages", NumberOfPages);	
			
		} else {						// 공모전 실패 하면
		
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "다음페이지가 없습니다");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}

}
