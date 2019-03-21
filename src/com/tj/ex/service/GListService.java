package com.tj.ex.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dto.GalleryDto;
public class GListService implements Service {
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
		int currentPage = Integer.parseInt(gpageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		GalleryDao galleryDao = new GalleryDao();
		ArrayList<GalleryDto> glist = galleryDao.glist(startRow, endRow);
		request.setAttribute("glist", glist);
		int totCnt = galleryDao.getGalleryTotCnt(); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 list.size()대용
		request.setAttribute("gpageNum", currentPage);// pageNum 없으면 param.pageNum
	}
}