package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dto.GalleryDto;
public class GContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String gpageNum = request.getParameter("npageNum");
		if(gpageNum==null || gpageNum.equals("")){
			gpageNum = "1";
		}
		int gNum = Integer.parseInt(request.getParameter("gNum"));
		GalleryDao galleryDao = new GalleryDao();
		GalleryDto dto = galleryDao.gContentView(gNum);
		request.setAttribute("gcontent_view", dto);
		request.setAttribute("gpageNum", gpageNum);
	}

}
