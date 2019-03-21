package com.tj.ex.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dto.GalleryDto;
public class HGListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String gpageNum = request.getParameter("gpageNum");
		String requestPageNum = (String)request.getAttribute("gpageNum");
		if(gpageNum==null) {
			if(requestPageNum==null)
				gpageNum = "1";
			else
				gpageNum = requestPageNum;
		}
		int startRow = 1;
		int endRow   = 5;
		GalleryDao galleryDao = new GalleryDao();
		ArrayList<GalleryDto> Hglist = galleryDao.Hglist(startRow, endRow);
		request.setAttribute("glist", Hglist);
		
	}
}