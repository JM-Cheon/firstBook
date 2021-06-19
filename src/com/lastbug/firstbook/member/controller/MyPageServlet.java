package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.CoinChargeDTO;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.UseCoinDTO;
import com.lastbug.firstbook.member.model.dto.WishlistDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/mypage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		int memNum = loginMember.getMemNum();
			
		MemberService memberService = new MemberService();
			
		List<UseCoinDTO> useCoinList = memberService.selectUseCoin(memNum);
		List<WishlistDTO> wishlist = memberService.selectWishlist(memNum);
		List<CoinChargeDTO> coinChargelist = memberService.selectCoinCharge(memNum);
		
			
		String path = "/WEB-INF/views/member/myPage.jsp";
		request.setAttribute("useCoinList", useCoinList);
		request.setAttribute("wishlist", wishlist);
		request.setAttribute("coinChargelist", coinChargelist);
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
