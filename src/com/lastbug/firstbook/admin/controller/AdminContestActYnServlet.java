package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminContestService;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.contest.model.service.ContestDetailService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;


@WebServlet("/admin/contestActiveYn")
public class AdminContestActYnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String activeYn = request.getParameter("activeYn");
		int competNum = Integer.valueOf(request.getParameter("competNum"));
		
		AdminContestService acService = new AdminContestService();
		
		
		if(activeYn.equals("Y")){
			
			activeYn = "N";
			
		} else if(activeYn.equals("N")) {
			
			activeYn = "Y";
			
		}
		
		
		int result = acService.ContestActiveOrDeactive(competNum, activeYn);
		
		List<ContestDetalDTO> contestDetailList = new ContestDetailService().selectContestDetal(competNum);	//
		
		
		List<ContestDTO> contestlList = acService.selectContestList(competNum);				// 웹소설 정보 검색
		
		
		
		List<MemberDTO> memberList = new ContestDetailService().selectMemberList(competNum);					// 작가 정보 검색
		String category = new ContestDetailService().selectCategory(competNum);	
		
		String path = "";
		if(result > 0) {
			
			path = "/WEB-INF/views/admin/adminContestDetail.jsp";
			request.setAttribute("contestDetailList", contestDetailList);
			request.setAttribute("contestlList", contestlList);
			request.setAttribute("memberList", memberList);
			request.setAttribute("category", category);

			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공개 or 비공개처리 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
