package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dao.GalleryDao;
public class GDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int gNum = Integer.parseInt(request.getParameter("gNum"));
		GalleryDao galleryDao = new GalleryDao();
		int result = galleryDao.gDelete(gNum);
		if(result == BoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}
