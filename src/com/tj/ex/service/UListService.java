package com.tj.ex.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
import com.tj.ex.dto.UpgradeDto;
public class UListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String upageNum = request.getParameter("upageNum");
		String requestPageNum = (String)request.getAttribute("upageNum");
		if(upageNum==null) {
			if(requestPageNum==null)
				upageNum = "1";
			else
				upageNum = requestPageNum;
		}
		int currentPage = Integer.parseInt(upageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		UpgradeDao upgradeDao = new UpgradeDao();
		ArrayList<UpgradeDto> ulist = upgradeDao.ulist(startRow, endRow);
		request.setAttribute("ulist", ulist);
		int totCnt = upgradeDao.getUpgradeTotCnt(); // 글갯수
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
		request.setAttribute("upageNum", currentPage);// pageNum 없으면 param.pageNum
	}
}