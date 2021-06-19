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

@WebServlet("/member/insertCoin")
public class CoinInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int amount = Integer.valueOf(request.getParameter("amount"));
		int memNum = ((MemberDTO)request.getSession().getAttribute("loginMember")).getMemNum();
		
//		System.out.println(amount);
		
		MemberService memberService = new MemberService();
		int result = memberService.insertCoin(memNum, amount);
		
		String path = "";
		if(result > 0) {
			MemberDTO member = memberService.selectMember(memNum);
			HttpSession session = request.getSession(); 
			session.setAttribute("loginMember", member);
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertCoin");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertCoin");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
