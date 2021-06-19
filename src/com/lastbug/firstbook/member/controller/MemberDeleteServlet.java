package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = ((MemberDTO)request.getSession().getAttribute("loginMember")).getMemId();
		String memPwd = request.getParameter("memPwd");
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setMemId(memId);
		requestMember.setMemPwd(memPwd);
		
		MemberService memberService = new MemberService();
		int result = memberService.deleteMember(requestMember);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteMember");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "deleteMember");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

}
