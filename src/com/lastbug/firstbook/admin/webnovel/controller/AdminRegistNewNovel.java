package com.lastbug.firstbook.admin.webnovel.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lastbug.firstbook.admin.webnovel.model.service.AdminWebNovelService;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

import net.coobird.thumbnailator.Thumbnails;


@WebServlet("/admin/novel/regist")
public class AdminRegistNewNovel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String total = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/admin/webnovel/AdminRegistNewNovel.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String title = request.getParameter("title");
//		String author = request.getParameter("author");
//		String publisher = request.getParameter("publisher");
//		String YN = request.getParameter("YN");
//		String categoryList = request.getParameter("category");
//		String dayOfWeekList = request.getParameter("dayOfWeek");
//		String webNovelIntro = request.getParameter("webNovelIntro");
//		String price = request.getParameter("webNovlePrice");
		
//		System.out.println("title" + title);
//		System.out.println("author" + author);
//		System.out.println(publisher);
//		System.out.println(YN);
//		System.out.println(categoryList);
//		System.out.println(dayOfWeekList);
//		System.out.println(webNovelIntro);
//		System.out.println(price);

		if(ServletFileUpload.isMultipartContent(request)) {
			System.out.println("요왔니?");
			
			String rootLocation = request.getServletContext().getRealPath("/");
			String fileUploadDirectory = rootLocation + "/resources/upload/original/";
			String thumbnailDirectory = rootLocation + "/resources/upload/thumbnail/";

			int maxFileSize = 1024 * 1024 * 10;
			String encodingType = "UTF-8";

			File directory = new File(fileUploadDirectory);
			File directory2 = new File(thumbnailDirectory);

			/* 파일 경로가 존재하지 않는 경우 디렉토리 생성 */
			if(!directory.exists() || !directory2.exists()) {
				System.out.println("폴더 생성 : " + directory.mkdirs());
				System.out.println("폴더 생성 : " + directory2.mkdirs());
			}

			/* 파일에 대한 정보는 리스트에, 다른 파라미터의 정보는 맵에 담자 */
			Map<String, String> parameter = new HashMap<>();
			List<Map<String, String>> fileList = new ArrayList<>();

			/* commons 패키지에 있는 클래스를 임포트 해야 함 */
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(directory);
			fileItemFactory.setSizeThreshold(maxFileSize);

			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

			try {
				List<FileItem> fileItems = fileUpload.parseRequest(request);

				/* 모든 FileItem들을 다 처리해 보자 */
				for(int i = 0 ; i < fileItems.size(); i++) {
					FileItem item = fileItems.get(i);

					/* isFormField가 true이면 일반 input태그의 값,
					 * isFormField가 false이면 파일 input태그의 값
					 */
					if(!item.isFormField()) {

						/* 파일인데 그 중에서도 사이즈가 있어야 함
						 * 그렇지 않으면 빈 공간으로 들어가게됨
						 */
						if(item.getSize() > 0) {

							String fieldName = item.getFieldName();
							String originFileName = item.getName();

							int dot = originFileName.lastIndexOf(".");
							String ext = originFileName.substring(dot);

							String randomFileName = UUID.randomUUID().toString().replace("-","") + ext;

							File storeFile = new File(fileUploadDirectory + randomFileName);

							/* 파일을 지정된 경로에 rename한 이름의 파일로 저장한다. */
							item.write(storeFile);

							/* 필요한 정보를 Map에 담자 */
							Map<String, String> fileMap = new HashMap<>();
							fileMap.put("fieldName", fieldName);
							fileMap.put("originFileName", originFileName);
							fileMap.put("savedFileName", randomFileName);
							fileMap.put("savePath", fileUploadDirectory);

							/* 대표 사진과 나머지 사진을 구분하고 썸네일(이미지 리사이징)도 생성하자. */
							int width = 0;
							int height = 0;
							if("thumbnailImg1".equals(fieldName)) {
								fileMap.put("fileType", "TITLE");

								width = 350;
								height = 200;

							} 

							/* 썸네일로 변환 후 저장한다. */
							Thumbnails.of(fileUploadDirectory + randomFileName)
							.size(width,height)
							.toFile(thumbnailDirectory + "thumbnail_" + randomFileName);

							fileMap.put("thumbnailPath", "/resources/upload/thumbnail/thumbnail_" + randomFileName);

							fileList.add(fileMap);

							 total = "/firstbook/resources/upload/original/" +  randomFileName;
						}

					} else {

						parameter.put(item.getFieldName(), 
								new String(item.getString().getBytes("ISO-8859-1"), encodingType));

					}
				}

				//				System.out.println("parameter : " + parameter);
				//				System.out.println("fileList : " + fileList);

				String dayOfWeekList = parameter.get("dayOfWeek");
				System.out.println("선택한 요일은" + dayOfWeekList);
				String dayofWeek = "";
				if(dayOfWeekList.equals("Mon")){
					dayofWeek = "월";
				}else if(dayOfWeekList.equals("Tues")) {
					dayofWeek = "화";	
				}else if(dayOfWeekList.equals("Wed")) {
					dayofWeek = "수";	
				}else if(dayOfWeekList.equals("Thurs")) {
					dayofWeek = "목";	
				}else if(dayOfWeekList.equals("Fri")) {
					dayofWeek = "금";	
				}else if(dayOfWeekList.equals("Sat")) {
					dayofWeek = "토";	
				}else if(dayOfWeekList.equals("Sun")) {
					dayofWeek = "일";	
				}
				String YN = parameter.get("YN");
				String ynresult = "";
				if(YN.equals("Y")){
					ynresult = "Y";
				}else if(YN.equals("N")) {
					ynresult = "N";	
				}
				
				/* 값이 잘 전달되었는지 확인 */
//		System.out.println("title" + title);
//		System.out.println("author" + author);
//		System.out.println(publisher);
//		System.out.println(YN);
//		System.out.println(categoryList);
//		System.out.println(dayOfWeekList);
//		System.out.println(webNovelIntro);
//		System.out.println(price);
				System.out.println("total" + total);
				
				/* 서비스를 요청 할 수 있도록 하나의 사진 게시판 내용을 하나의 BoardDTO에 담는다. */
				WebNovelInfoDTO thumbnail = new WebNovelInfoDTO();
				thumbnail.setCategoryCode(parameter.get("category"));
				thumbnail.setWebNovTitle(parameter.get("title"));
				thumbnail.setWebNovAuthor(parameter.get("author"));
				thumbnail.setWebNovPublisher(parameter.get("publisher"));
				thumbnail.setWebNovInform(parameter.get("webNovelIntro"));
				thumbnail.setWebNovImgLocation(total);
				thumbnail.setWebNovOpenOrClose(ynresult);
				thumbnail.setDayOfWeek(dayofWeek);
				
				/* 서비스 메소드를 요청하며 비지니스 로직을 처리한다. */
				int result = new AdminWebNovelService().insertNewWebnovel(thumbnail);
				
				/* 성공 실패 여부를 파악 후 처리 */
				String path = "";
				if(result > 0) {
					path = "/WEB-INF/views/common/success.jsp";
					request.setAttribute("successCode", "insertWebnovel");
				} else {
					path = "/WEB-INF/views/common/failed.jsp";
					request.setAttribute("failedCode", "adminInsertNewWebNovel");
				}
				
				request.getRequestDispatcher(path).forward(request, response);

			}  catch (Exception e) {
				e.printStackTrace();
				
				int cnt = 0;
				/* 어떤 종류의 Exception이 발생 하더라도 실패 시 올라간 파일은 삭제해야 한다. */
				for(int i = 0; i < fileList.size(); i++) {
					Map<String, String> file = fileList.get(i);

					File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFiledName"));
					boolean isDeleted = deleteFile.delete();

					if(isDeleted) {
						cnt++;

					}
				}

				if(cnt == fileList.size()) {
					System.out.println("업로드에 실패한 모든 사진 삭제 완료!");
				}else {
					System.out.println("업로드에 실패한 모든 사진 삭제 실패!");

			}
		}

	} else {

	}

}

}
			

