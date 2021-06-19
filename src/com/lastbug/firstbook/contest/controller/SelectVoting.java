package com.lastbug.firstbook.contest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestDetalDTO;
import com.lastbug.firstbook.contest.model.service.ContestDetailService;
import com.lastbug.firstbook.contest.model.service.VotingYNService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/contest/selectVoting")
public class SelectVoting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int competNum = Integer.valueOf(request.getParameter("competNum"));
		
		String name = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		
		
		int	YN = new VotingYNService().VotingN(competNum, name);
		String count = new VotingYNService().count(competNum);

		List<ContestDetalDTO> contestDetailList = new ContestDetailService().selectContestDetal(competNum);	
		List<ContestDTO> contestlList = new ContestDetailService().selectContestList(competNum);				// 웹소설 정보 검색
		List<MemberDTO> memberList = new ContestDetailService().selectMemberList(competNum);					// 작가 정보 검색
		String category = new ContestDetailService().selectCategory(competNum);		

		int memNum = ((MemberDTO)request.getSession().getAttribute("loginMember")).getMemNum();
		
		MemberService memberService = new MemberService();
		
		MemberDTO member = memberService.selectMember(memNum);
	
		String path = "";
		if(!contestDetailList.isEmpty()) {		
			path = "/WEB-INF/views/contest/contestDetail.jsp";
			request.setAttribute("contestDetailList", contestDetailList);
			request.setAttribute("contestlList", contestlList);
			request.setAttribute("memberList", memberList);
			request.setAttribute("category", category);
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
		} else {						  		
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "viewFailed");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
