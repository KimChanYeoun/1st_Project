package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dto.BoardDto;

public class MyBListService implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String cId = (String)httpSession.getAttribute("cId");
		if(cId==null) {
			cId = request.getParameter("cId");
		}
		String bpageNum = request.getParameter("bpageNum");
		String requestPageNum = (String)request.getAttribute("bpageNum");
		if(bpageNum==null) {
			if(requestPageNum==null)
				bpageNum = "1";
			else
				bpageNum = requestPageNum;
		}
		int currentPage = Integer.parseInt(bpageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> myblist = boardDao.myblist(cId, startRow, endRow);
		request.setAttribute("blist", myblist);
		int totCnt = boardDao.getBoardMyTotCnt(cId); // 글갯수
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
		request.setAttribute("bpageNum", currentPage);// pageNum 없으면 param.pageNum
	}
}