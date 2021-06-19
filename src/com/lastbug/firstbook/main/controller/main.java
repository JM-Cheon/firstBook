package com.lastbug.firstbook.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestSsnDTO;
import com.lastbug.firstbook.contest.model.service.ContestService;
import com.lastbug.firstbook.novel.model.dto.NovelDTO;
import com.lastbug.firstbook.novel.model.service.NovelService;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;

@WebServlet("/main/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<ContestDTO> contestList = new ContestService().mainTopContest();
	List<NovelDTO> NovelList = new NovelService().selectAllNovel();
	List<WebNovelInfoDTO> webNovelList = new WebNovelService().selectTopNovel();

	
	String path = "";
	if(!contestList.isEmpty()) {		// 공모전 작품이 검색되면
		path = "/WEB-INF/views/main/main.jsp";
		request.setAttribute("contestList", contestList);
		request.setAttribute("NovelList", NovelList);
		request.setAttribute("webNovelList", webNovelList);
		
	} else {						// 공모전 실패 하면
	
		path = "/WEB-INF/views/common/failed.jsp";
		request.setAttribute("message", "main");
	}
	
	request.getRequestDispatcher(path).forward(request, response);
	
	}

}
