package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.contest.model.service.ContestDetailService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;


@WebServlet("/admin/contestDetail")
public class AdminContestDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int competNum = Integer.valueOf(request.getParameter("competNum"));
		
		List<ContestDetalDTO> contestDetailList = new ContestDetailService().selectContestDetal(competNum);	//
		List<ContestDTO> contestlList = new ContestDetailService().selectContestList(competNum);				// 웹소설 정보 검색
		List<MemberDTO> memberList = new ContestDetailService().selectMemberList(competNum);					// 작가 정보 검색
		String category = new ContestDetailService().selectCategory(competNum);		
		
		String path = "";
		if(!contestDetailList.isEmpty()) {		
			path = "/WEB-INF/views/admin/adminContestDetail.jsp";
			request.setAttribute("contestDetailList", contestDetailList);
			request.setAttribute("contestlList", contestlList);
			request.setAttribute("memberList", memberList);
			request.setAttribute("category", category);
		} else {						  		
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("contestFailed", "작품 보러가기 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}

}
