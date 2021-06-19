package com.lastbug.firstbook.contest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.contest.model.dto.CompetContentDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.contest.model.service.SelectViewService;
import com.lastbug.firstbook.contest.model.service.VotingYNService;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovContentDetailDTO;

@WebServlet("/contest/SelectView")
public class SelectView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int competNum = Integer.valueOf(request.getParameter("competNum"));	// 웹소설 
		int chapterNum = Integer.valueOf(request.getParameter("chapterNum"));	//	웹 회차
		int first = Integer.valueOf(request.getParameter("first"));	//	웹 회차
		int second = first + 1;
		
		
		List<ContestDetalDTO> selectView = new SelectViewService().selectView(competNum, chapterNum);
		List<ContestDTO> contestList = new SelectViewService().selectContestList(competNum);
		String NumberOfPages = String.valueOf(selectView.get(0).getNumberOfPages());
		List<CompetContentDTO> PageNubmer = new SelectViewService().pageNubmer(NumberOfPages);
		List<CompetContentDTO> viewPage = new SelectViewService().selectViewPage(NumberOfPages, first);
		List<CompetContentDTO> viewPageTwo = new SelectViewService().selectViewPageTwo(NumberOfPages, second);
		
		int lastPage = PageNubmer.size();
	
		String path = "";
		if(!contestList.isEmpty()) {		// 공모전 작품이 검색되면
			path = "/WEB-INF/views/contest/constestViewer.jsp";
			request.setAttribute("selectView", selectView);	
			request.setAttribute("contestList", contestList);	
			request.setAttribute("viewPage", viewPage);	
			request.setAttribute("viewPageTwo", viewPageTwo);	
			request.setAttribute("competNum", competNum);	
			request.setAttribute("chapterNum", chapterNum);	
			request.setAttribute("first", first);	
			request.setAttribute("lastPage", lastPage);	
		} else {						// 공모전 실패 하면
		
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "viewFailed");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
