package com.lastbug.firstbook.webnovel.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovChapSearchDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebnovelReplyDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;


@WebServlet("/webnovel/detail/finished")
public class FinishedShowDetailWebNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		System.out.println("웹소설 디테일 서블릿 왔니?");

		/* 페이징 처리를 위한 페이지 조회 */
		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			/* 0보다 작은 숫자가 넘어오면 1페이지를 보여준다. */
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		/* 페이징 처리 */
		WebNovelService webService = new WebNovelService();
		int totalCount = webService.selectTotalCount();
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		int no = Integer.valueOf(request.getParameter("webNovNum"));
		
		
		
		WebNovelService webNovelService = new WebNovelService();
		
		/* 앞에서 전체 웹소설 목록 중에서 선택한 웹소설 번호로 웹소설 정보 조회 */
		WebNovelInfoDTO webDetail = webNovelService.selectWebNovelDetail(no);
		

		/* 1~5화까지 조회 (무료보기) */
		List<WebNovChapSearchDTO> webNovelChap = null;
		/* 6화부터 조회 (유료보기) */
		List<WebNovChapSearchDTO> webNovelChap2 = null;
		
		
		/* 전체 댓글 불러오기 */
		List<WebnovelReplyDTO> webnovAllReply = webNovelService.selectAllReply();
		
		/* 해당 웹소설 댓글 불러오기 */
		List<WebnovelReplyDTO> selectWebnovReply = webNovelService.selectWebnovReply(no);
		
		
//		System.out.println("내가 선택한 회차는 " + webDetail);
		
		String path = "";
		
		/* 선택한 웹소설 조회가 null이 아닌 경우 */
		if(webDetail != null) {
			System.out.println("왔어?");
			webNovelChap = webNovelService.selectWebNovelallChapter(webDetail);
			webNovelChap2 = webNovelService.selectWebNovelallChapterNotFree(webDetail);
	
			path = "/WEB-INF/views/webnovel/finishedWebDetail.jsp";
			request.setAttribute("webnovel", webDetail);
			request.setAttribute("webnoveldetail", webNovelChap);
			request.setAttribute("webnoveldetail2", webNovelChap2);
			request.setAttribute("webnovAllReply", webnovAllReply);
			request.setAttribute("selectWebnovReply", selectWebnovReply);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "webnovelPerChap");
		}
	
		request.getRequestDispatcher(path).forward(request, response);
		
	}


}