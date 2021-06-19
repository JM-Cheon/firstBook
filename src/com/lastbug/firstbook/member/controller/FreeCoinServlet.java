package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/freeCoin")
public class FreeCoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int memNum = Integer.valueOf(request.getParameter("memNum"));
		MemberService memberService = new MemberService();
		
		int result =  memberService.incrementFreeCoin(memNum);
		
		MemberDTO member = memberService.selectMember(memNum);
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", member);
			
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
		out.flush();
		out.close();
		
	}

}
