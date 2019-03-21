package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dto.BoardDto;
public class BContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		BoardDao boardDao = new BoardDao();
		BoardDto dto = boardDao.bcontentView(bNum);
		request.setAttribute("bcontent_view", dto);
	}

}
