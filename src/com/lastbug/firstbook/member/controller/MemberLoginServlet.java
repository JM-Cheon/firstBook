package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/loginForm.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		
//		System.out.println("memId : " + memId);
//		System.out.println("memPwd : " + memPwd);
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setMemId(memId);
		requestMember.setMemPwd(memPwd);
		
		MemberService memberService = new MemberService();
		
		MemberDTO loginMember = memberService.loginCheck(requestMember);
		
//		System.out.println(loginMember);
		
		if(loginMember != null) {
			int result = memberService.incrementLoginCount(requestMember);
//			System.out.println(result);
			if(result > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("failedCode", "loginMember");
				request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("failedCode", "loginMember");
			request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(request, response);
		}
	}

}
