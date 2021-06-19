package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.service.MemberService;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;

/**
 * Servlet implementation class WishLishMemServlet
 */
@WebServlet("/member/wishlistupdate")
public class WishLishMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 위시리스트 저장 */
		int weblistNum = Integer.parseInt(request.getParameter("wishlistWebnovNum"));
		int memNum2 = Integer.parseInt(request.getParameter("loginMember"));

		
		WebNovelService WebNovelService = new WebNovelService();
		System.out.println("webListNum" + weblistNum);
		System.out.println("memNum2" + memNum2);
		
		int wishlistUpdate = WebNovelService.updateWishList(weblistNum, memNum2);
		
		response.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter out = response.getWriter(); 
//		
//		out.print(false);
		out.flush();
		out.close();
	}
	


}
