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
import com.lastbug.firstbook.webnovel.model.dto.WebNovContentDetailDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;


@WebServlet("/webnovel/chapList")
public class ChapViewerSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		/* 읽고 싶어하는 웹소설 번호와 해당 챕터 번호 가져오기 */
		int currentWebNovNum = Integer.valueOf(request.getParameter("currentWebNov"));
		int currentChapNum = Integer.valueOf(request.getParameter("currentChap"));
		
//		System.out.println("현재 웹소설 번호" + currentWebNovNum);
//		System.out.println("현재 웹소설의 챕터 번호" + currentChapNum);
		
		/* 1. 현재 페이지 관련 설정 */
		String currentPage = request.getParameter("currentPage");
		
		WebNovelService webService = new WebNovelService();
		
		/* 선택한 웹소설 챕터에 대한 모든 정보를 불러옴 */
		int totalCount = webService.searchWebNovelCount(currentWebNovNum, currentChapNum);
		WebNovelInfoDTO title = webService.searchTitle(currentWebNovNum);
		
//		System.out.println("1화 1챕터 " + totalCount);
		
		/* 페이지 넘버 초기화 */
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		/* 한 페이지에 보여줄 컬럼 수 */
		int limit = 2;
		
		/* 한 페이지에 보여줄 페이지 수 이지만 본 프로젝트에서는 크게 의의룰 두지 않아도 되서 10으로 세팅 */
		int buttonAmount = 10;
		
		/* 페이징 처리를 위한 논리를 다른 클래스에서 진행 */
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		/* 각 페이지 (웹뷰어에서 2페이지씩 보여지도록)에 보여줄 웹소설 챕터별 내용 뽑아오기 */
		List<WebNovContentDetailDTO> perChap = webService.selectPerChap(pageInfo, currentWebNovNum, currentChapNum);
	
//		for(WebNovContentDetailDTO wnd : perChap) {
//			System.out.println("db에서 각 챕터별 몇개 잇는지" + wnd);
//		}
		
//		System.out.println("pageInfo" + pageInfo);
		String path = "";
		if(!perChap.isEmpty()) {
			path = "/WEB-INF/views/webnovel/webviewer.jsp";
			request.setAttribute("perChap", perChap);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("currentWebNov", currentWebNovNum);
			request.setAttribute("currentChap", currentChapNum);
			request.setAttribute("title", title);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "perChapContext");
		}
		
		request.getRequestDispatcher(path).forward(request, response);



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
}
