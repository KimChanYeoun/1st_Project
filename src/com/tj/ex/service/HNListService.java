package com.tj.ex.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.NoticeDto;
public class HNListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String npageNum = request.getParameter("npageNum");
		String requestPageNum = (String)request.getAttribute("npageNum");
		if(npageNum==null) {
			if(requestPageNum==null)
				npageNum = "1";
			else
				npageNum = requestPageNum;
		}
		int startRow = 1;
		int endRow   = 5;
		NoticeDao noticeDao = new NoticeDao();
		ArrayList<NoticeDto> nlist = noticeDao.nlist(startRow, endRow);
		request.setAttribute("nlist", nlist);

	}
}