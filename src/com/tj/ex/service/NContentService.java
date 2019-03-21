package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.NoticeDto;
public class NContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String npageNum = request.getParameter("npageNum");
		if(npageNum==null || npageNum.equals("")){
			npageNum = "1";
		}
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao noticeDao = new NoticeDao();
		NoticeDto dto = noticeDao.nContentView(nNum);
		request.setAttribute("ncontent_view", dto);
		request.setAttribute("npageNum", npageNum);
	}

}
