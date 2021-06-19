package com.lastbug.firstbook.admin.webnovel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.webnovel.model.service.AdminWebNovelService;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

@WebServlet("/admin/webNovel")
public class ManageSelectAllWebnovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		System.out.println("웹소설 관리자 페이지에 왔니?");

		List<WebNovelInfoDTO> adminWebNovelList = new AdminWebNovelService().AdminSelectAllNovel();

		/* 전체 목록 조회 성공! */
		//		for(WebNovelInfoDTO b : webNovelList) {
		//			System.out.println(b);
		//		}


		String path = "";

		if(!adminWebNovelList.isEmpty()) {		// 관리자 페이지에서 웹소설이 조회 되었다면
			//			System.out.println("조회됨?");
			path = "/WEB-INF/views/admin/webnovel/adminWebnovel.jsp";
			request.setAttribute("adminWebNovelList", adminWebNovelList);

		}else {							// 관리자 페이지에서 웹소설이 조회 되지 않았다면
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "adminSelectAllWebNovel");


		}
		request.getRequestDispatcher(path).forward(request, response);

	}
}












