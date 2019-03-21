package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dto.GalleryDto;
public class GReplyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int gNum = Integer.parseInt(request.getParameter("gNum"));
		GalleryDao galleryDao = new GalleryDao();
		GalleryDto dto = galleryDao.gModifyView_replyView(gNum);
		request.setAttribute("greply_view", dto);
	}
}
